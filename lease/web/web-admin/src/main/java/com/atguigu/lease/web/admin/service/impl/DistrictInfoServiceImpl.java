package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.api.apartment.RegionClient;
import com.atguigu.lease.model.entity.DistrictInfo;
import com.atguigu.lease.web.admin.service.DistrictInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictInfoServiceImpl implements DistrictInfoService {

    @Autowired
    private RegionClient regionClient;

    @Override
    public List<DistrictInfo> list(LambdaQueryWrapper<DistrictInfo> queryWrapper) {
        Long cityId = (Long) queryWrapper.getParamNameValuePairs().values().iterator().next();
        return regionClient.listDistrictInfoByCityId(cityId).getData();
    }
}
