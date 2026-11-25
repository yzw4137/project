package com.atguigu.lease.service.user.service;

import com.atguigu.lease.service.user.vo.app.AppLoginVo;
import com.atguigu.lease.service.user.vo.app.AppUserInfoVo;

public interface AppLoginService {
    void getCode(String phone);
    String login(AppLoginVo loginVo);
    AppUserInfoVo getLoginUserInfoById(Long userId);
}
