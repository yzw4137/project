package com.atguigu.lease.service.apartment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.lease.model.entity.FacilityInfo;
import com.atguigu.lease.service.apartment.service.FacilityInfoService;
import com.atguigu.lease.service.apartment.mapper.FacilityInfoMapper;
import org.springframework.stereotype.Service;

@Service
public class FacilityInfoServiceImpl extends ServiceImpl<FacilityInfoMapper, FacilityInfo>
    implements FacilityInfoService{

}
