package com.atguigu.lease.service.apartment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.lease.model.entity.CityInfo;
import com.atguigu.lease.service.apartment.service.CityInfoService;
import com.atguigu.lease.service.apartment.mapper.CityInfoMapper;
import org.springframework.stereotype.Service;

@Service
public class CityInfoServiceImpl extends ServiceImpl<CityInfoMapper, CityInfo>
    implements CityInfoService{

}
