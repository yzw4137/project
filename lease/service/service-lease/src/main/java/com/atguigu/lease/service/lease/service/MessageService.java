package com.atguigu.lease.service.lease.service;

import com.atguigu.lease.model.vo.message.MessageItemVo;
import com.atguigu.lease.model.vo.message.MessageOverviewVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public interface MessageService {

    MessageOverviewVo overview(Long userId);

    Page<MessageItemVo> pageItem(long current, long size, Integer type, Long userId);

    void markRead(Long id, Integer type, Long userId);

    void removeById(Long id, Long userId);

    /**
     * 生成消息（内部调用）。接收者为自己或为空时不生成。
     */
    void send(Long receiverId, Integer type, Long senderId, String content, String bizType, Long bizId);
}
