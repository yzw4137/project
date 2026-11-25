package com.atguigu.lease.service.lease.controller;

import com.atguigu.lease.common.login.LoginUserHolder;
import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.vo.message.MessageItemVo;
import com.atguigu.lease.model.vo.message.MessageOverviewVo;
import com.atguigu.lease.model.vo.message.MessageReadVo;
import com.atguigu.lease.service.lease.service.MessageService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "消息管理")
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    private Long currentUserId() {
        return LoginUserHolder.getLoginUser().getUserId();
    }

    @Operation(summary = "查询三类消息未读数概览")
    @GetMapping("overview")
    public Result<MessageOverviewVo> overview() {
        return Result.ok(messageService.overview(currentUserId()));
    }

    @Operation(summary = "分页查询消息列表（可按类型过滤）")
    @GetMapping("pageItem")
    public Result<Page<MessageItemVo>> pageItem(@RequestParam long current,
                                                @RequestParam long size,
                                                @RequestParam(required = false) Integer type) {
        return Result.ok(messageService.pageItem(current, size, type, currentUserId()));
    }

    @Operation(summary = "标记已读（单条或按类型全部）")
    @PostMapping("read")
    public Result read(@RequestBody MessageReadVo vo) {
        messageService.markRead(vo.getId(), vo.getType(), currentUserId());
        return Result.ok();
    }

    @Operation(summary = "删除消息")
    @DeleteMapping("removeById")
    public Result removeById(@RequestParam Long id) {
        messageService.removeById(id, currentUserId());
        return Result.ok();
    }
}
