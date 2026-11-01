package com.atguigu.lease.web.app.service;

import com.atguigu.lease.model.vo.message.MessageItemVo;
import com.atguigu.lease.model.vo.message.MessageOverviewVo;
import com.atguigu.lease.model.vo.message.MessageReadVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public interface MessageService {

    MessageOverviewVo overview();

    Page<MessageItemVo> pageItem(long current, long size, Integer type);

    void read(MessageReadVo vo);

    void removeById(Long id);
}
