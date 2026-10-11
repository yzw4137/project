package com.atguigu.lease.api.user;

import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.vo.history.HistoryItemVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-user", contextId = "browsingHistoryClient")
public interface BrowsingHistoryClient {

    @GetMapping("/history/pageItem")
    Result<Page<HistoryItemVo>> pageItem(@RequestParam("current") long current, @RequestParam("size") long size, @RequestParam("userId") Long userId);

    @PostMapping("/history/saveHistory")
    Result saveHistory(@RequestParam("userId") Long userId, @RequestParam("roomId") Long roomId);
}
