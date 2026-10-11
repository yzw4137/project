package com.atguigu.lease.api.user;

import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.entity.SystemUser;
import com.atguigu.lease.model.enums.BaseStatus;
import com.atguigu.lease.model.vo.system.user.SystemUserItemVo;
import com.atguigu.lease.model.vo.system.user.SystemUserQueryVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "service-user", contextId = "systemUserClient")
public interface SystemUserClient {

    @GetMapping("/system/user/page")
    Result<Page<SystemUserItemVo>> page(@RequestParam("current") long current, @RequestParam("size") long size, @SpringQueryMap SystemUserQueryVo queryVo);

    @GetMapping("/system/user/getById")
    Result<SystemUserItemVo> getById(@RequestParam("id") Long id);

    @PostMapping("/system/user/saveOrUpdate")
    Result saveOrUpdate(@RequestBody SystemUser systemUser);

    @GetMapping("/system/user/isUserNameAvailable")
    Result<Boolean> isUserNameAvailable(@RequestParam("username") String username);

    @DeleteMapping("/system/user/deleteById")
    Result deleteById(@RequestParam("id") Long id);

    @PostMapping("/system/user/updateStatusByUserId")
    Result updateStatusByUserId(@RequestParam("id") Long id, @RequestParam("status") BaseStatus status);

    @GetMapping("/system/user/getByUsername")
    Result<SystemUser> getByUsername(@RequestParam("username") String username);
}
