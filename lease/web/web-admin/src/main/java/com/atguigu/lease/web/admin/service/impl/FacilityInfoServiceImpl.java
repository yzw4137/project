package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.api.apartment.FacilityClient;
import com.atguigu.lease.model.entity.FacilityInfo;
import com.atguigu.lease.model.enums.ItemType;
import com.atguigu.lease.web.admin.service.FacilityInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class FacilityInfoServiceImpl implements FacilityInfoService {

    @Autowired
    private FacilityClient facilityClient;

    @Override
    public List<FacilityInfo> list(LambdaQueryWrapper<FacilityInfo> queryWrapper) {
        ItemType type = (ItemType) queryWrapper.getParamNameValuePairs().values().stream()
                .filter(v -> v instanceof ItemType).findFirst().orElse(null);
        return facilityClient.listFacility(type).getData();
    }

    @Override
    public boolean saveOrUpdate(FacilityInfo facilityInfo) {
        facilityClient.saveOrUpdate(facilityInfo);
        return true;
    }

    @Override
    public boolean removeById(Serializable id) {
        facilityClient.removeFacilityById((Long) id);
        return true;
    }
}
