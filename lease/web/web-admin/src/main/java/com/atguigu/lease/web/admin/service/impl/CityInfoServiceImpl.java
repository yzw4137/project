package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.api.apartment.RegionClient;
import com.atguigu.lease.model.entity.CityInfo;
import com.atguigu.lease.web.admin.service.CityInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityInfoServiceImpl implements CityInfoService {

    @Autowired
    private RegionClient regionClient;

    @Override
    public List<CityInfo> list(LambdaQueryWrapper<CityInfo> queryWrapper) {
        Long provinceId = (Long) queryWrapper.getParamNameValuePairs().values().iterator().next();
        return regionClient.listCityInfoByProvinceId(provinceId).getData();
    }
}
