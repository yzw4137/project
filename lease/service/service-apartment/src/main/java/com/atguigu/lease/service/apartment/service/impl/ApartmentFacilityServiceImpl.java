package com.atguigu.lease.service.apartment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.lease.model.entity.ApartmentFacility;
import com.atguigu.lease.service.apartment.service.ApartmentFacilityService;
import com.atguigu.lease.service.apartment.mapper.ApartmentFacilityMapper;
import org.springframework.stereotype.Service;

@Service
public class ApartmentFacilityServiceImpl extends ServiceImpl<ApartmentFacilityMapper, ApartmentFacility>
    implements ApartmentFacilityService{

}
