package com.atguigu.lease.service.lease.service;

import com.atguigu.lease.model.vo.review.CommentItemVo;
import com.atguigu.lease.model.vo.review.CommentSubmitVo;
import com.atguigu.lease.model.vo.review.ReviewItemVo;
import com.atguigu.lease.model.vo.review.ReviewQueryVo;
import com.atguigu.lease.model.vo.review.ReviewSubmitVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

public interface ReviewService {

    /**
     * 分页查询评价列表（圈子），支持最新/最热排序
     */
    Page<ReviewItemVo> pageItem(long current, long size, ReviewQueryVo queryVo);

    /**
     * 查询当前用户发布的评价列表
     */
    List<ReviewItemVo> listMyItem(Long userId);

    /**
     * 根据id查询评价详情
     */
    ReviewItemVo getDetailById(Long id, Long userId);

    /**
     * 发布评价
     */
    void save(ReviewSubmitVo vo, Long userId);

    /**
     * 点赞/取消点赞评价（Redis ZSet 原子计数）
     */
    void toggleLike(Long reviewId, Long userId);

    /**
     * 查询评价的评论列表，支持最新/最热排序
     */
    List<CommentItemVo> listComment(Long reviewId, String sortType, Long userId);

    /**
     * 发表评论
     */
    void saveComment(CommentSubmitVo vo, Long userId);

    /**
     * 点赞/取消点赞评论
     */
    void toggleCommentLike(Long commentId, Long userId);

    /**
     * 删除评论（仅本人）
     */
    void removeCommentById(Long id, Long userId);
}
