package com.atguigu.lease.api.apartment;

import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.entity.AttrKey;
import com.atguigu.lease.model.entity.AttrValue;
import com.atguigu.lease.model.vo.attr.AttrKeyVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "service-apartment", contextId = "attrClient")
public interface AttrClient {

    @PostMapping("/attr/key/saveOrUpdate")
    Result saveOrUpdateAttrKey(@RequestBody AttrKey attrKey);

    @PostMapping("/attr/value/saveOrUpdate")
    Result saveOrUpdateAttrValue(@RequestBody AttrValue attrValue);

    @GetMapping("/attr/list")
    Result<List<AttrKeyVo>> listAttrInfo();

    @DeleteMapping("/attr/key/deleteById")
    Result removeAttrKeyById(@RequestParam("attrKeyId") Long attrKeyId);

    @DeleteMapping("/attr/value/deleteById")
    Result removeAttrValueById(@RequestParam("id") Long id);
}
