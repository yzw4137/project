package com.atguigu.lease.api.user;

import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.entity.SystemPost;
import com.atguigu.lease.model.enums.BaseStatus;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "service-user")
public interface SystemPostClient {

    @GetMapping("/system/post/page")
    Result<IPage<SystemPost>> page(@RequestParam("current") long current, @RequestParam("size") long size);

    @PostMapping("/system/post/saveOrUpdate")
    Result saveOrUpdate(@RequestBody SystemPost systemPost);

    @DeleteMapping("/system/post/deleteById")
    Result deleteById(@RequestParam("id") Long id);

    @GetMapping("/system/post/getById")
    Result<SystemPost> getById(@RequestParam("id") Long id);

    @GetMapping("/system/post/list")
    Result<List<SystemPost>> list();

    @PostMapping("/system/post/updateStatusByPostId")
    Result updateStatusByPostId(@RequestParam("id") Long id, @RequestParam("status") BaseStatus status);
}
