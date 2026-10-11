package com.atguigu.lease.service.user.controller;

import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.entity.UserInfo;
import com.atguigu.lease.model.enums.BaseStatus;
import com.atguigu.lease.model.vo.user.UserInfoQueryVo;
import com.atguigu.lease.service.user.service.UserInfoService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Tag(name = "用户信息管理")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/info")
    @Operation(summary = "根据id查询用户信息")
    public Result<UserInfo> getUserInfo(@RequestParam("userId") Long userId) {
        return Result.ok(userInfoService.getById(userId));
    }

    @GetMapping("page")
    @Operation(summary = "分页查询用户信息")
    public Result<Page<UserInfo>> pageUserInfo(@RequestParam long current, @RequestParam long size, UserInfoQueryVo queryVo) {
        Page<UserInfo> page = new Page<>(current, size);
        return Result.ok((Page<UserInfo>) userInfoService.pageUserInfo(page, queryVo));
    }

    @PostMapping("updateStatusById")
    @Operation(summary = "根据用户id更新账号状态")
    public Result updateStatusById(@RequestParam Long id, @RequestParam BaseStatus status) {
        userInfoService.updateStatusById(id, status);
        return Result.ok();
    }

    @GetMapping("getByPhone")
    @Operation(summary = "根据手机号查询用户")
    public Result<UserInfo> getByPhone(@RequestParam String phone) {
        return Result.ok(userInfoService.getByPhone(phone));
    }

    @PostMapping("register")
    @Operation(summary = "注册用户")
    public Result<UserInfo> register(@RequestBody UserInfo userInfo) {
        userInfoService.register(userInfo);
        return Result.ok(userInfo);
    }
}
