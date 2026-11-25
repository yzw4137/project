package com.atguigu.lease.service.user.controller;

import com.atguigu.lease.common.login.LoginUserHolder;
import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.entity.BrowsingHistory;
import com.atguigu.lease.model.vo.history.HistoryItemVo;
import com.atguigu.lease.service.user.service.BrowsingHistoryService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "浏览历史管理")
@RestController
@RequestMapping("/history")
public class BrowsingHistoryController {

    @Autowired
    private BrowsingHistoryService browsingHistoryService;

    @GetMapping("pageItem")
    @Operation(summary = "获取当前登录用户的浏览历史")
    public Result<Page<HistoryItemVo>> pageItem(@RequestParam long current, @RequestParam long size) {
        Long userId = LoginUserHolder.getLoginUser().getUserId();
        Page<HistoryItemVo> page = new Page<>(current, size);
        return Result.ok((Page<HistoryItemVo>) browsingHistoryService.pageItemByUserId(page, userId));
    }

    @PostMapping("saveHistory")
    @Operation(summary = "保存浏览历史")
    public Result saveHistory(@RequestParam Long roomId) {
        Long userId = LoginUserHolder.getLoginUser().getUserId();
        browsingHistoryService.saveHistory(userId, roomId);
        return Result.ok();
    }
}
