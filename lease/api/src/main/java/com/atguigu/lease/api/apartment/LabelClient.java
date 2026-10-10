package com.atguigu.lease.api.apartment;

import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.entity.LabelInfo;
import com.atguigu.lease.model.enums.ItemType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "service-apartment")
public interface LabelClient {

    @GetMapping("/label/list")
    Result<List<LabelInfo>> labelList(@RequestParam(value = "type", required = false) ItemType type);

    @PostMapping("/label/saveOrUpdate")
    Result saveOrUpdateLabel(@RequestBody LabelInfo labelInfo);

    @DeleteMapping("/label/deleteById")
    Result deleteLabelById(@RequestParam("id") Long id);
}
