package com.atguigu.lease.service.user.controller;

import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.entity.UserInfo;
import com.atguigu.lease.service.user.service.UserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Tag(name = "用户信息管理")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/info")
    @Operation(summary = "根据id查询用户信息")
    public Result<UserInfo> getUserInfo(@RequestParam("userId") Long userId) {
        UserInfo userInfo = userInfoService.getById(userId);
        return Result.ok(userInfo);
    }
}
