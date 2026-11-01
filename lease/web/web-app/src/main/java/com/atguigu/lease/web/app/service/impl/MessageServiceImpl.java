package com.atguigu.lease.web.app.service.impl;

import com.atguigu.lease.api.lease.MessageClient;
import com.atguigu.lease.common.login.LoginUserHolder;
import com.atguigu.lease.model.vo.message.MessageItemVo;
import com.atguigu.lease.model.vo.message.MessageOverviewVo;
import com.atguigu.lease.model.vo.message.MessageReadVo;
import com.atguigu.lease.web.app.service.MessageService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageClient messageClient;

    private Long currentUserId() {
        return LoginUserHolder.getLoginUser().getUserId();
    }

    @Override
    public MessageOverviewVo overview() {
        return messageClient.overview(currentUserId()).getData();
    }

    @Override
    public Page<MessageItemVo> pageItem(long current, long size, Integer type) {
        return messageClient.pageItem(current, size, type, currentUserId()).getData();
    }

    @Override
    public void read(MessageReadVo vo) {
        messageClient.read(vo, currentUserId());
    }

    @Override
    public void removeById(Long id) {
        messageClient.removeById(id, currentUserId());
    }
}
