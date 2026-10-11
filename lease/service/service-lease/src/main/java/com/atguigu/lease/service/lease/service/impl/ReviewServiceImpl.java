package com.atguigu.lease.service.lease.service.impl;

import com.atguigu.lease.common.exception.LeaseException;
import com.atguigu.lease.model.entity.*;
import com.atguigu.lease.model.enums.LeaseStatus;
import com.atguigu.lease.model.vo.graph.GraphVo;
import com.atguigu.lease.model.vo.review.*;
import com.atguigu.lease.service.lease.mapper.*;
import com.atguigu.lease.service.lease.service.ReviewService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewInfoMapper reviewInfoMapper;
    @Autowired
    private ReviewGraphMapper reviewGraphMapper;
    @Autowired
    private ReviewLikeMapper reviewLikeMapper;
    @Autowired
    private ReviewCommentMapper reviewCommentMapper;
    @Autowired
    private ReviewCommentLikeMapper reviewCommentLikeMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private ApartmentInfoMapper apartmentInfoMapper;
    @Autowired
    private RoomInfoMapper roomInfoMapper;
    @Autowired
    private LeaseAgreementMapper leaseAgreementMapper;
    @Autowired
    private StringRedisTemplate redis;

    // ===== Redis Key 定义 =====
    private static final String LIKE_COUNT_KEY = "review:like:count";          // ZSet: member=reviewId, score=点赞数
    private static final String LIKED_KEY = "review:liked:";                   // Set:  + reviewId，成员为 userId
    private static final String COMMENT_LIKES_KEY = "review:comment:likes:";   // ZSet: + reviewId，member=commentId, score=点赞数
    private static final String COMMENT_LIKED_KEY = "review:comment:liked:";   // Set: + commentId，成员为 userId

    // ==================== 评价列表 ====================

    @Override
    public Page<ReviewItemVo> pageItem(long current, long size, ReviewQueryVo queryVo) {
        Long userId = queryVo.getUserId();
        boolean hot = "hot".equalsIgnoreCase(queryVo.getSortType());

        Page<ReviewItemVo> result = new Page<>(current, size);
        List<ReviewInfo> records;
        long total;

        if (hot) {
            // 最热：从 Redis ZSet 按 score 倒序取当前页区间
            long start = (current - 1) * size;
            long stop = current * size - 1;
            Set<String> ids = redis.opsForZSet().reverseRange(LIKE_COUNT_KEY, start, stop);
            Long zcard = redis.opsForZSet().zCard(LIKE_COUNT_KEY);
            total = zcard == null ? 0 : zcard;
            if (CollectionUtils.isEmpty(ids)) {
                records = Collections.emptyList();
            } else {
                List<Long> idList = ids.stream().map(Long::valueOf).collect(Collectors.toList());
                records = reviewInfoMapper.selectBatchIds(idList);
                // 保持 ZSet 的热度顺序
                Map<Long, ReviewInfo> infoMap = records.stream()
                        .collect(Collectors.toMap(BaseEntity::getId, r -> r));
                records = idList.stream().map(infoMap::get).filter(Objects::nonNull).collect(Collectors.toList());
            }
        } else {
            // 最新：查库按 create_time 倒序分页
            Page<ReviewInfo> page = new Page<>(current, size);
            LambdaQueryWrapper<ReviewInfo> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(queryVo.getApartmentId() != null, ReviewInfo::getApartmentId, queryVo.getApartmentId());
            wrapper.orderByDesc(ReviewInfo::getCreateTime);
            Page<ReviewInfo> p = reviewInfoMapper.selectPage(page, wrapper);
            records = p.getRecords();
            total = p.getTotal();
        }

        List<ReviewItemVo> voList = records.stream()
                .map(r -> assemble(r, userId))
                .collect(Collectors.toList());
        result.setRecords(voList);
        result.setTotal(total);
        return result;
    }

    @Override
    public List<ReviewItemVo> listMyItem(Long userId) {
        LambdaQueryWrapper<ReviewInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ReviewInfo::getUserId, userId);
        wrapper.orderByDesc(ReviewInfo::getCreateTime);
        List<ReviewInfo> list = reviewInfoMapper.selectList(wrapper);
        return list.stream().map(r -> assemble(r, userId)).collect(Collectors.toList());
    }

    @Override
    public ReviewItemVo getDetailById(Long id, Long userId) {
        ReviewInfo info = reviewInfoMapper.selectById(id);
        return info == null ? null : assemble(info, userId);
    }

    // ==================== 发布评价 ====================

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(ReviewSubmitVo vo, Long userId) {
        // 1. 校验租约归属与状态
        LeaseAgreement agreement = leaseAgreementMapper.selectById(vo.getAgreementId());
        if (agreement == null) {
            throw new LeaseException(400, "租约不存在");
        }
        UserInfo user = userInfoMapper.selectById(userId);
        if (user == null || !Objects.equals(agreement.getPhone(), user.getPhone())) {
            throw new LeaseException(400, "无权评价该租约");
        }
        if (agreement.getStatus() != LeaseStatus.SIGNED
                && agreement.getStatus() != LeaseStatus.EXPIRED
                && agreement.getStatus() != LeaseStatus.WITHDRAWN) {
            throw new LeaseException(400, "当前租约状态不可评价");
        }
        // 2. 同一租约不可重复评价
        Long exist = reviewInfoMapper.selectCount(
                new LambdaQueryWrapper<ReviewInfo>().eq(ReviewInfo::getAgreementId, vo.getAgreementId()));
        if (exist != null && exist > 0) {
            throw new LeaseException(400, "该租约已发布过评价");
        }

        // 3. 保存评价
        ReviewInfo info = new ReviewInfo();
        info.setUserId(userId);
        info.setApartmentId(agreement.getApartmentId());
        info.setRoomId(agreement.getRoomId());
        info.setAgreementId(vo.getAgreementId());
        info.setContent(vo.getContent());
        info.setRating(vo.getRating());
        info.setLikeCount(0);
        info.setCommentCount(0);
        reviewInfoMapper.insert(info);

        // 4. 保存图片
        if (!CollectionUtils.isEmpty(vo.getImageUrlList())) {
            for (String url : vo.getImageUrlList()) {
                ReviewGraph rg = new ReviewGraph();
                rg.setReviewId(info.getId());
                rg.setUrl(url);
                reviewGraphMapper.insert(rg);
            }
        }

        // 5. 加入热榜 ZSet（score=0），确保 0 赞评价也参与排序
        redis.opsForZSet().add(LIKE_COUNT_KEY, String.valueOf(info.getId()), 0);
    }

    // ==================== 点赞（Redis ZSet 原子计数）====================

    @Override
    public void toggleLike(Long reviewId, Long userId) {
        String likedKey = LIKED_KEY + reviewId;
        Boolean liked = redis.opsForSet().isMember(likedKey, String.valueOf(userId));
        if (liked == null || !liked) {
            // 点赞：原子 +1
            redis.opsForZSet().incrementScore(LIKE_COUNT_KEY, String.valueOf(reviewId), 1);
            redis.opsForSet().add(likedKey, String.valueOf(userId));
            // 异步落库记录归属
            ReviewLike rl = new ReviewLike();
            rl.setReviewId(reviewId);
            rl.setUserId(userId);
            try {
                reviewLikeMapper.insert(rl);
            } catch (Exception ignore) {
                // 唯一索引冲突说明已存在，忽略
            }
        } else {
            // 取消：原子 -1
            redis.opsForZSet().incrementScore(LIKE_COUNT_KEY, String.valueOf(reviewId), -1);
            redis.opsForSet().remove(likedKey, String.valueOf(userId));
            reviewLikeMapper.delete(new LambdaQueryWrapper<ReviewLike>()
                    .eq(ReviewLike::getReviewId, reviewId)
                    .eq(ReviewLike::getUserId, userId));
        }
    }

    // ==================== 评论 ====================

    @Override
    public List<CommentItemVo> listComment(Long reviewId, String sortType, Long userId) {
        List<ReviewComment> comments;
        boolean hot = "hot".equalsIgnoreCase(sortType);
        String key = COMMENT_LIKES_KEY + reviewId;

        if (hot) {
            // 最热：按评论点赞 ZSet 的 score 倒序
            Set<String> ids = redis.opsForZSet().reverseRange(key, 0, -1);
            if (CollectionUtils.isEmpty(ids)) {
                return Collections.emptyList();
            }
            List<Long> idList = ids.stream().map(Long::valueOf).collect(Collectors.toList());
            List<ReviewComment> list = reviewCommentMapper.selectBatchIds(idList);
            Map<Long, ReviewComment> map = list.stream()
                    .collect(Collectors.toMap(BaseEntity::getId, c -> c));
            comments = idList.stream().map(map::get).filter(Objects::nonNull).collect(Collectors.toList());
        } else {
            // 最新：按 create_time 倒序
            comments = reviewCommentMapper.selectList(new LambdaQueryWrapper<ReviewComment>()
                    .eq(ReviewComment::getReviewId, reviewId)
                    .orderByDesc(ReviewComment::getCreateTime));
        }

        return comments.stream().map(c -> assembleComment(c, userId)).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveComment(CommentSubmitVo vo, Long userId) {
        ReviewComment comment = new ReviewComment();
        comment.setReviewId(vo.getReviewId());
        comment.setUserId(userId);
        comment.setParentId(vo.getParentId());
        comment.setContent(vo.getContent());
        comment.setLikeCount(0);
        reviewCommentMapper.insert(comment);

        // 评论数 +1
        ReviewInfo info = reviewInfoMapper.selectById(vo.getReviewId());
        if (info != null) {
            info.setCommentCount((info.getCommentCount() == null ? 0 : info.getCommentCount()) + 1);
            reviewInfoMapper.updateById(info);
        }
        // 加入该评价的评论点赞 ZSet（score=0）
        redis.opsForZSet().add(COMMENT_LIKES_KEY + vo.getReviewId(), String.valueOf(comment.getId()), 0);
    }

    @Override
    public void toggleCommentLike(Long commentId, Long userId) {
        ReviewComment comment = reviewCommentMapper.selectById(commentId);
        if (comment == null) {
            return;
        }
        String likedKey = COMMENT_LIKED_KEY + commentId;
        Boolean liked = redis.opsForSet().isMember(likedKey, String.valueOf(userId));
        String zKey = COMMENT_LIKES_KEY + comment.getReviewId();
        if (liked == null || !liked) {
            redis.opsForZSet().incrementScore(zKey, String.valueOf(commentId), 1);
            redis.opsForSet().add(likedKey, String.valueOf(userId));
            ReviewCommentLike rcl = new ReviewCommentLike();
            rcl.setCommentId(commentId);
            rcl.setUserId(userId);
            try {
                reviewCommentLikeMapper.insert(rcl);
            } catch (Exception ignore) {
            }
        } else {
            redis.opsForZSet().incrementScore(zKey, String.valueOf(commentId), -1);
            redis.opsForSet().remove(likedKey, String.valueOf(userId));
            reviewCommentLikeMapper.delete(new LambdaQueryWrapper<ReviewCommentLike>()
                    .eq(ReviewCommentLike::getCommentId, commentId)
                    .eq(ReviewCommentLike::getUserId, userId));
        }
    }

    @Override
    public void removeCommentById(Long id, Long userId) {
        ReviewComment comment = reviewCommentMapper.selectById(id);
        if (comment == null) {
            return;
        }
        if (!Objects.equals(comment.getUserId(), userId)) {
            throw new LeaseException(400, "无权删除他人评论");
        }
        reviewCommentMapper.deleteById(id);
        redis.opsForZSet().remove(COMMENT_LIKES_KEY + comment.getReviewId(), String.valueOf(id));
        // 评论数 -1
        ReviewInfo info = reviewInfoMapper.selectById(comment.getReviewId());
        if (info != null && info.getCommentCount() != null && info.getCommentCount() > 0) {
            info.setCommentCount(info.getCommentCount() - 1);
            reviewInfoMapper.updateById(info);
        }
    }

    // ==================== 组装 VO ====================

    private ReviewItemVo assemble(ReviewInfo info, Long currentUserId) {
        ReviewItemVo vo = new ReviewItemVo();
        vo.setId(info.getId());
        vo.setUserId(info.getUserId());
        vo.setApartmentId(info.getApartmentId());
        vo.setRoomId(info.getRoomId());
        vo.setAgreementId(info.getAgreementId());
        vo.setContent(info.getContent());
        vo.setRating(info.getRating());
        vo.setCommentCount(info.getCommentCount() == null ? 0 : info.getCommentCount());
        vo.setCreateTime(info.getCreateTime());

        // 发布用户信息
        UserInfo user = userInfoMapper.selectById(info.getUserId());
        if (user != null) {
            vo.setNickname(user.getNickname());
            vo.setAvatarUrl(user.getAvatarUrl());
        }
        // 公寓名
        ApartmentInfo apartment = apartmentInfoMapper.selectById(info.getApartmentId());
        if (apartment != null) {
            vo.setApartmentName(apartment.getName());
        }
        // 房间号
        RoomInfo room = roomInfoMapper.selectById(info.getRoomId());
        if (room != null) {
            vo.setRoomNumber(room.getRoomNumber());
        }
        // 图片
        List<ReviewGraph> graphs = reviewGraphMapper.selectList(
                new LambdaQueryWrapper<ReviewGraph>().eq(ReviewGraph::getReviewId, info.getId()));
        vo.setGraphVoList(graphs.stream()
                .map(g -> new GraphVo(g.getName(), g.getUrl()))
                .collect(Collectors.toList()));
        // 点赞数（Redis 为准）
        Double score = redis.opsForZSet().score(LIKE_COUNT_KEY, String.valueOf(info.getId()));
        vo.setLikeCount(score == null ? (info.getLikeCount() == null ? 0 : info.getLikeCount()) : score.intValue());
        // 是否已点赞
        if (currentUserId != null) {
            Boolean m = redis.opsForSet().isMember(LIKED_KEY + info.getId(), String.valueOf(currentUserId));
            vo.setIsLiked(Boolean.TRUE.equals(m));
        } else {
            vo.setIsLiked(false);
        }
        return vo;
    }

    private CommentItemVo assembleComment(ReviewComment c, Long currentUserId) {
        CommentItemVo vo = new CommentItemVo();
        vo.setId(c.getId());
        vo.setReviewId(c.getReviewId());
        vo.setUserId(c.getUserId());
        vo.setContent(c.getContent());
        vo.setParentId(c.getParentId());
        vo.setCreateTime(c.getCreateTime());

        UserInfo user = userInfoMapper.selectById(c.getUserId());
        if (user != null) {
            vo.setNickname(user.getNickname());
            vo.setAvatarUrl(user.getAvatarUrl());
        }
        // 被回复用户昵称
        if (c.getParentId() != null) {
            ReviewComment parent = reviewCommentMapper.selectById(c.getParentId());
            if (parent != null) {
                UserInfo pu = userInfoMapper.selectById(parent.getUserId());
                vo.setReplyToNickname(pu == null ? null : pu.getNickname());
            }
        }
        // 点赞数（Redis 为准）
        Double score = redis.opsForZSet().score(COMMENT_LIKES_KEY + c.getReviewId(), String.valueOf(c.getId()));
        vo.setLikeCount(score == null ? (c.getLikeCount() == null ? 0 : c.getLikeCount()) : score.intValue());
        // 当前用户是否已点赞、是否本人评论
        if (currentUserId != null) {
            Boolean m = redis.opsForSet().isMember(COMMENT_LIKED_KEY + c.getId(), String.valueOf(currentUserId));
            vo.setIsLiked(Boolean.TRUE.equals(m));
            vo.setIsMine(Objects.equals(c.getUserId(), currentUserId));
        } else {
            vo.setIsLiked(false);
            vo.setIsMine(false);
        }
        return vo;
    }
}
