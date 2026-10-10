package com.atguigu.lease.api.apartment;

import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.entity.FacilityInfo;
import com.atguigu.lease.model.enums.ItemType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "service-apartment")
public interface FacilityClient {

    @GetMapping("/facility/list")
    Result<List<FacilityInfo>> listFacility(@RequestParam(value = "type", required = false) ItemType type);

    @PostMapping("/facility/saveOrUpdate")
    Result saveOrUpdate(@RequestBody FacilityInfo facilityInfo);

    @DeleteMapping("/facility/deleteById")
    Result removeFacilityById(@RequestParam("id") Long id);
}
