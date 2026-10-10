package com.atguigu.lease.api.apartment;

import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.entity.FeeKey;
import com.atguigu.lease.model.entity.FeeValue;
import com.atguigu.lease.model.vo.fee.FeeKeyVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "service-apartment")
public interface FeeClient {

    @PostMapping("/fee/key/saveOrUpdate")
    Result saveOrUpdateFeeKey(@RequestBody FeeKey feeKey);

    @PostMapping("/fee/value/saveOrUpdate")
    Result saveOrUpdateFeeValue(@RequestBody FeeValue feeValue);

    @GetMapping("/fee/list")
    Result<List<FeeKeyVo>> feeInfoList();

    @DeleteMapping("/fee/key/deleteById")
    Result deleteFeeKeyById(@RequestParam("feeKeyId") Long feeKeyId);

    @DeleteMapping("/fee/value/deleteById")
    Result deleteFeeValueById(@RequestParam("id") Long id);
}
