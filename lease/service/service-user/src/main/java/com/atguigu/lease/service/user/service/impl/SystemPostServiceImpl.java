package com.atguigu.lease.service.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.lease.model.entity.SystemPost;
import com.atguigu.lease.service.user.mapper.SystemPostMapper;
import com.atguigu.lease.service.user.service.SystemPostService;
import org.springframework.stereotype.Service;

@Service
public class SystemPostServiceImpl extends ServiceImpl<SystemPostMapper, SystemPost>
        implements SystemPostService {
}
