package com.atguigu.lease.service.user.service.impl;

import com.atguigu.lease.model.entity.UserInfo;
import com.atguigu.lease.service.user.mapper.UserInfoMapper;
import com.atguigu.lease.service.user.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
        implements UserInfoService {
}
