package com.atguigu.lease.api.apartment;

import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.entity.LeaseTerm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "service-apartment")
public interface LeaseTermClient {

    @GetMapping("/term/list")
    Result<List<LeaseTerm>> listLeaseTerm();

    @GetMapping("/term/listByRoomId")
    Result<List<LeaseTerm>> listByRoomId(@RequestParam("id") Long id);

    @PostMapping("/term/saveOrUpdate")
    Result saveOrUpdate(@RequestBody LeaseTerm leaseTerm);

    @DeleteMapping("/term/deleteById")
    Result deleteLeaseTermById(@RequestParam("id") Long id);
}
