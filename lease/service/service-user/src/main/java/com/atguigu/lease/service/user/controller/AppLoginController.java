package com.atguigu.lease.service.user.controller;

import com.atguigu.lease.common.login.LoginUserHolder;
import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.service.user.service.AppLoginService;
import com.atguigu.lease.service.user.vo.app.AppLoginVo;
import com.atguigu.lease.service.user.vo.app.AppUserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户端登录/验证码/用户信息接口。
 * Gateway 路由原样转发 /app/login/** 和 /app/info，不 StripPrefix。
 */
@RestController
@RequestMapping("/app")
public class AppLoginController {

    @Autowired
    private AppLoginService appLoginService;

    @GetMapping("login/getCode")
    public Result getCode(@RequestParam String phone) {
        appLoginService.getCode(phone);
        return Result.ok();
    }

    @PostMapping("login")
    public Result<String> login(@RequestBody AppLoginVo loginVo) {
        return Result.ok(appLoginService.login(loginVo));
    }

    @GetMapping("info")
    public Result<AppUserInfoVo> info() {
        Long userId = LoginUserHolder.getLoginUser().getUserId();
        return Result.ok(appLoginService.getLoginUserInfoById(userId));
    }
}
