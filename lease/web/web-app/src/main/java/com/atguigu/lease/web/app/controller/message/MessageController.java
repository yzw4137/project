package com.atguigu.lease.web.app.controller.message;

import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.vo.message.MessageItemVo;
import com.atguigu.lease.model.vo.message.MessageOverviewVo;
import com.atguigu.lease.model.vo.message.MessageReadVo;
import com.atguigu.lease.web.app.service.MessageService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "消息通知")
@RestController
@RequestMapping("/app/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Operation(summary = "查询三类消息未读数概览（角标）")
    @GetMapping("overview")
    public Result<MessageOverviewVo> overview() {
        return Result.ok(messageService.overview());
    }

    @Operation(summary = "分页查询消息列表（可按类型过滤）")
    @GetMapping("pageItem")
    public Result<Page<MessageItemVo>> pageItem(@RequestParam long current,
                                                @RequestParam long size,
                                                @RequestParam(required = false) Integer type) {
        return Result.ok(messageService.pageItem(current, size, type));
    }

    @Operation(summary = "标记已读（单条或按类型全部）")
    @PostMapping("read")
    public Result read(@RequestBody MessageReadVo vo) {
        messageService.read(vo);
        return Result.ok();
    }

    @Operation(summary = "删除消息")
    @DeleteMapping("removeById")
    public Result removeById(@RequestParam Long id) {
        messageService.removeById(id);
        return Result.ok();
    }
}
