package com.atguigu.lease.service.user.controller;

import com.atguigu.lease.common.login.LoginUserHolder;
import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.service.user.service.AdminLoginService;
import com.atguigu.lease.service.user.vo.admin.AdminCaptchaVo;
import com.atguigu.lease.service.user.vo.admin.AdminLoginVo;
import com.atguigu.lease.service.user.vo.admin.AdminUserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 管理端登录/验证码/用户信息接口。
 * Gateway 路由原样转发 /admin/login/** 和 /admin/info，不 StripPrefix。
 */
@RestController
@RequestMapping("/admin")
public class AdminLoginController {

    @Autowired
    private AdminLoginService adminLoginService;

    @GetMapping("login/captcha")
    public Result<AdminCaptchaVo> getCaptcha() {
        return Result.ok(adminLoginService.getCaptcha());
    }

    @PostMapping("login")
    public Result<String> login(@RequestBody AdminLoginVo loginVo) {
        return Result.ok(adminLoginService.login(loginVo));
    }

    @GetMapping("info")
    public Result<AdminUserInfoVo> info() {
        Long userId = LoginUserHolder.getLoginUser().getUserId();
        return Result.ok(adminLoginService.getLoginUserInfoById(userId));
    }
}
