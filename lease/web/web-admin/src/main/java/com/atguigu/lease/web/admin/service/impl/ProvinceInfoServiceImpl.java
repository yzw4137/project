package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.api.apartment.RegionClient;
import com.atguigu.lease.model.entity.ProvinceInfo;
import com.atguigu.lease.web.admin.service.ProvinceInfoService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceInfoServiceImpl implements ProvinceInfoService {

    @Autowired
    private RegionClient regionClient;

    @Override
    public List<ProvinceInfo> list() {
        return regionClient.listProvince().getData();
    }

    @Override
    public List<ProvinceInfo> list(Wrapper<ProvinceInfo> queryWrapper) {
        return list();
    }
}
