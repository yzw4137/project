package com.atguigu.lease.web.admin.service;

import com.atguigu.lease.model.entity.UserInfo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
* @author liubo
* @description 针对表【user_info(用户信息表)】的数据库操作Service
* @createDate 2023-07-24 15:48:00
*/
public interface UserInfoService {

    IPage<UserInfo> page(Page<UserInfo> page, LambdaQueryWrapper<UserInfo> queryWrapper);

    boolean update(LambdaUpdateWrapper<UserInfo> updateWrapper);
}
