package com.atguigu.lease.api.apartment;

import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.entity.PaymentType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "service-apartment", contextId = "paymentTypeClient")
public interface PaymentTypeClient {

    @GetMapping("/payment/list")
    Result<List<PaymentType>> listPaymentType();

    @GetMapping("/payment/listByRoomId")
    Result<List<PaymentType>> listByRoomId(@RequestParam("id") Long id);

    @PostMapping("/payment/saveOrUpdate")
    Result saveOrUpdatePaymentType(@RequestBody PaymentType paymentType);

    @DeleteMapping("/payment/deleteById")
    Result deletePaymentById(@RequestParam("id") Long id);
}
