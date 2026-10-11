package com.atguigu.lease.service.user.controller;

import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.entity.SystemUser;
import com.atguigu.lease.model.enums.BaseStatus;
import com.atguigu.lease.model.vo.system.user.SystemUserItemVo;
import com.atguigu.lease.model.vo.system.user.SystemUserQueryVo;
import com.atguigu.lease.service.user.service.SystemUserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "后台用户信息管理")
@RestController
@RequestMapping("/system/user")
public class SystemUserController {

    @Autowired
    private SystemUserService systemUserService;

    @GetMapping("page")
    @Operation(summary = "根据条件分页查询后台用户列表")
    public Result<Page<SystemUserItemVo>> page(@RequestParam long current, @RequestParam long size, SystemUserQueryVo queryVo) {
        Page<SystemUserItemVo> page = new Page<>(current, size);
        return Result.ok((Page<SystemUserItemVo>) systemUserService.pageSystemUser(page, queryVo));
    }

    @GetMapping("getById")
    @Operation(summary = "根据ID查询后台用户信息")
    public Result<SystemUserItemVo> getById(@RequestParam Long id) {
        return Result.ok(systemUserService.getSystemUserById(id));
    }

    @PostMapping("saveOrUpdate")
    @Operation(summary = "保存或更新后台用户信息")
    public Result saveOrUpdate(@RequestBody SystemUser systemUser) {
        systemUserService.saveOrUpdate(systemUser);
        return Result.ok();
    }

    @GetMapping("isUserNameAvailable")
    @Operation(summary = "判断后台用户名是否可用")
    public Result<Boolean> isUsernameExists(@RequestParam String username) {
        long count = systemUserService.lambdaQuery().eq(SystemUser::getUsername, username).count();
        return Result.ok(count == 0);
    }

    @DeleteMapping("deleteById")
    @Operation(summary = "根据ID删除后台用户信息")
    public Result removeById(@RequestParam Long id) {
        systemUserService.removeById(id);
        return Result.ok();
    }

    @PostMapping("updateStatusByUserId")
    @Operation(summary = "根据ID修改后台用户状态")
    public Result updateStatusByUserId(@RequestParam Long id, @RequestParam BaseStatus status) {
        systemUserService.lambdaUpdate()
                .eq(SystemUser::getId, id)
                .set(SystemUser::getStatus, status)
                .update();
        return Result.ok();
    }

    @GetMapping("getByUsername")
    @Operation(summary = "根据用户名查询后台用户")
    public Result<SystemUser> getByUsername(@RequestParam String username) {
        return Result.ok(systemUserService.getByUsername(username));
    }
}
