package com.atguigu.lease.web.app.controller.demo;

import com.atguigu.lease.api.user.UserInfoClient;
import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Feign 链路示范 controller（骨架验证用，后续迁移时删除）
 * 调用链：gateway -> web-app -> (Feign) service-user
 */
@RestController
@RequestMapping("/app/demo")
public class DemoController {

    @Autowired
    private UserInfoClient userInfoClient;

    @GetMapping("/user")
    public Result<UserInfo> getUser(@RequestParam("userId") Long userId) {
        return userInfoClient.getUserInfo(userId);
    }
}
