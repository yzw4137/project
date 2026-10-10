package com.atguigu.lease.api.user;

import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.entity.UserInfo;
import com.atguigu.lease.model.enums.BaseStatus;
import com.atguigu.lease.model.vo.user.UserInfoQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "service-user")
public interface UserInfoClient {

    @GetMapping("/user/info")
    Result<UserInfo> getUserInfo(@RequestParam("userId") Long userId);

    @GetMapping("/user/page")
    Result<IPage<UserInfo>> pageUserInfo(@RequestParam("current") long current, @RequestParam("size") long size, UserInfoQueryVo queryVo);

    @PostMapping("/user/updateStatusById")
    Result updateStatusById(@RequestParam("id") Long id, @RequestParam("status") BaseStatus status);

    @GetMapping("/user/getByPhone")
    Result<UserInfo> getByPhone(@RequestParam("phone") String phone);

    @PostMapping("/user/register")
    Result<UserInfo> register(@RequestBody UserInfo userInfo);
}
