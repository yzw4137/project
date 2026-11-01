package com.atguigu.lease.service.lease.service.impl;

import com.atguigu.lease.common.exception.LeaseException;
import com.atguigu.lease.model.entity.MessageInfo;
import com.atguigu.lease.model.entity.UserInfo;
import com.atguigu.lease.model.vo.message.MessageItemVo;
import com.atguigu.lease.model.vo.message.MessageOverviewVo;
import com.atguigu.lease.service.lease.mapper.MessageInfoMapper;
import com.atguigu.lease.service.lease.mapper.UserInfoMapper;
import com.atguigu.lease.service.lease.service.MessageService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageInfoMapper messageInfoMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public MessageOverviewVo overview(Long userId) {
        MessageOverviewVo vo = new MessageOverviewVo();
        vo.setNoticeUnread(countUnread(userId, 1));
        vo.setCommentUnread(countUnread(userId, 2));
        vo.setLikeUnread(countUnread(userId, 3));
        return vo;
    }

    private Integer countUnread(Long userId, Integer type) {
        Long count = messageInfoMapper.selectCount(new LambdaQueryWrapper<MessageInfo>()
                .eq(MessageInfo::getUserId, userId)
                .eq(MessageInfo::getType, type)
                .eq(MessageInfo::getIsRead, false));
        return count == null ? 0 : count.intValue();
    }

    @Override
    public Page<MessageItemVo> pageItem(long current, long size, Integer type, Long userId) {
        Page<MessageInfo> page = new Page<>(current, size);
        LambdaQueryWrapper<MessageInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MessageInfo::getUserId, userId);
        wrapper.eq(type != null && type > 0, MessageInfo::getType, type);
        wrapper.orderByDesc(MessageInfo::getCreateTime);
        Page<MessageInfo> p = messageInfoMapper.selectPage(page, wrapper);

        // 批量查询发送方用户信息，避免逐条查库
        Set<Long> senderIds = p.getRecords().stream()
                .map(MessageInfo::getSenderId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        Map<Long, UserInfo> userMap = senderIds.isEmpty()
                ? java.util.Collections.emptyMap()
                : userInfoMapper.selectBatchIds(senderIds).stream()
                        .collect(Collectors.toMap(UserInfo::getId, u -> u));

        List<MessageItemVo> voList = p.getRecords().stream()
                .map(m -> assemble(m, userMap))
                .collect(Collectors.toList());

        Page<MessageItemVo> result = new Page<>(current, size);
        result.setRecords(voList);
        result.setTotal(p.getTotal());
        return result;
    }

    private MessageItemVo assemble(MessageInfo m, Map<Long, UserInfo> userMap) {
        MessageItemVo vo = new MessageItemVo();
        vo.setId(m.getId());
        vo.setType(m.getType());
        vo.setSenderId(m.getSenderId());
        vo.setContent(m.getContent());
        vo.setBizType(m.getBizType());
        vo.setBizId(m.getBizId());
        vo.setIsRead(m.getIsRead());
        vo.setCreateTime(m.getCreateTime());
        if (m.getSenderId() != null) {
            UserInfo sender = userMap.get(m.getSenderId());
            if (sender != null) {
                vo.setNickname(sender.getNickname());
                vo.setAvatarUrl(sender.getAvatarUrl());
            }
        } else {
            vo.setNickname("系统通知");
        }
        return vo;
    }

    @Override
    public void markRead(Long id, Integer type, Long userId) {
        if (id != null) {
            MessageInfo message = messageInfoMapper.selectById(id);
            if (message == null) {
                return;
            }
            if (!Objects.equals(message.getUserId(), userId)) {
                throw new LeaseException(400, "无权操作他人消息");
            }
            if (!Boolean.TRUE.equals(message.getIsRead())) {
                message.setIsRead(true);
                messageInfoMapper.updateById(message);
            }
        } else if (type != null) {
            messageInfoMapper.update(null, new LambdaUpdateWrapper<MessageInfo>()
                    .eq(MessageInfo::getUserId, userId)
                    .eq(MessageInfo::getType, type)
                    .eq(MessageInfo::getIsRead, false)
                    .set(MessageInfo::getIsRead, true));
        } else {
            throw new LeaseException(400, "id 与 type 至少传一个");
        }
    }

    @Override
    public void removeById(Long id, Long userId) {
        MessageInfo message = messageInfoMapper.selectById(id);
        if (message == null) {
            return;
        }
        if (!Objects.equals(message.getUserId(), userId)) {
            throw new LeaseException(400, "无权删除他人消息");
        }
        messageInfoMapper.deleteById(id);
    }

    @Override
    public void send(Long receiverId, Integer type, Long senderId, String content, String bizType, Long bizId) {
        // 自己对自己的操作不生成消息
        if (receiverId == null || Objects.equals(receiverId, senderId)) {
            return;
        }
        MessageInfo message = new MessageInfo();
        message.setUserId(receiverId);
        message.setType(type);
        message.setSenderId(senderId);
        message.setContent(content);
        message.setBizType(bizType);
        message.setBizId(bizId);
        message.setIsRead(false);
        messageInfoMapper.insert(message);
    }
}
