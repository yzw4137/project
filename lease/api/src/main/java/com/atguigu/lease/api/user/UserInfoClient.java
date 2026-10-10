package com.atguigu.lease.api.user;

import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.entity.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户服务 Feign 接口（示范）
 */
@FeignClient(value = "service-user")
public interface UserInfoClient {

    @GetMapping("/user/info")
    Result<UserInfo> getUserInfo(@RequestParam("userId") Long userId);
}
