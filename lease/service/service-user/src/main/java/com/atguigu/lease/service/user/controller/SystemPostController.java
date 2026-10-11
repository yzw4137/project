package com.atguigu.lease.service.user.controller;

import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.entity.SystemPost;
import com.atguigu.lease.model.enums.BaseStatus;
import com.atguigu.lease.service.user.service.SystemPostService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "后台用户岗位管理")
@RestController
@RequestMapping("/system/post")
public class SystemPostController {

    @Autowired
    private SystemPostService systemPostService;

    @GetMapping("page")
    @Operation(summary = "分页获取岗位信息")
    public Result<Page<SystemPost>> page(@RequestParam long current, @RequestParam long size) {
        Page<SystemPost> page = new Page<>(current, size);
        return Result.ok((Page<SystemPost>) systemPostService.page(page));
    }

    @PostMapping("saveOrUpdate")
    @Operation(summary = "保存或更新岗位信息")
    public Result saveOrUpdate(@RequestBody SystemPost systemPost) {
        systemPostService.saveOrUpdate(systemPost);
        return Result.ok();
    }

    @DeleteMapping("deleteById")
    @Operation(summary = "根据id删除岗位")
    public Result removeById(@RequestParam Long id) {
        systemPostService.removeById(id);
        return Result.ok();
    }

    @GetMapping("getById")
    @Operation(summary = "根据id获取岗位信息")
    public Result<SystemPost> getById(@RequestParam Long id) {
        return Result.ok(systemPostService.getById(id));
    }

    @GetMapping("list")
    @Operation(summary = "获取全部岗位列表")
    public Result<List<SystemPost>> list() {
        return Result.ok(systemPostService.list());
    }

    @PostMapping("updateStatusByPostId")
    @Operation(summary = "根据岗位id修改状态")
    public Result updateStatusByPostId(@RequestParam Long id, @RequestParam BaseStatus status) {
        systemPostService.lambdaUpdate()
                .eq(SystemPost::getId, id)
                .set(SystemPost::getStatus, status)
                .update();
        return Result.ok();
    }
}
