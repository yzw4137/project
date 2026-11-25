package com.atguigu.lease.service.user.service;

import com.atguigu.lease.service.user.vo.admin.AdminCaptchaVo;
import com.atguigu.lease.service.user.vo.admin.AdminLoginVo;
import com.atguigu.lease.service.user.vo.admin.AdminUserInfoVo;

public interface AdminLoginService {
    AdminCaptchaVo getCaptcha();
    String login(AdminLoginVo loginVo);
    AdminUserInfoVo getLoginUserInfoById(Long userId);
}
