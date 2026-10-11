package com.atguigu.lease.api.apartment;

import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.entity.CityInfo;
import com.atguigu.lease.model.entity.DistrictInfo;
import com.atguigu.lease.model.entity.ProvinceInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "service-apartment", contextId = "regionClient")
public interface RegionClient {

    @GetMapping("/region/province/list")
    Result<List<ProvinceInfo>> listProvince();

    @GetMapping("/region/city/listByProvinceId")
    Result<List<CityInfo>> listCityInfoByProvinceId(@RequestParam("id") Long id);

    @GetMapping("/region/district/listByCityId")
    Result<List<DistrictInfo>> listDistrictInfoByCityId(@RequestParam("id") Long id);
}
