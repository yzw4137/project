package com.atguigu.lease.service.user.service;

import com.atguigu.lease.model.entity.UserInfo;
import com.atguigu.lease.model.enums.BaseStatus;
import com.atguigu.lease.model.vo.user.UserInfoQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 针对表【user_info(用户信息表)】的数据库操作Service
 */
public interface UserInfoService extends IService<UserInfo> {

    IPage<UserInfo> pageUserInfo(Page<UserInfo> page, UserInfoQueryVo queryVo);

    void updateStatusById(Long id, BaseStatus status);

    UserInfo getByPhone(String phone);

    void register(UserInfo userInfo);
}

