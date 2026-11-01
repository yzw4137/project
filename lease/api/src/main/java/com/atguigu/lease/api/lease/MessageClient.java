package com.atguigu.lease.api.lease;

import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.vo.message.MessageItemVo;
import com.atguigu.lease.model.vo.message.MessageOverviewVo;
import com.atguigu.lease.model.vo.message.MessageReadVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "service-lease", contextId = "messageClient")
public interface MessageClient {

    @GetMapping("/message/overview")
    Result<MessageOverviewVo> overview(@RequestParam("userId") Long userId);

    @GetMapping("/message/pageItem")
    Result<Page<MessageItemVo>> pageItem(@RequestParam("current") long current,
                                         @RequestParam("size") long size,
                                         @RequestParam(value = "type", required = false) Integer type,
                                         @RequestParam("userId") Long userId);

    @PostMapping("/message/read")
    Result read(@RequestBody MessageReadVo vo, @RequestParam("userId") Long userId);

    @DeleteMapping("/message/removeById")
    Result removeById(@RequestParam("id") Long id, @RequestParam("userId") Long userId);
}
