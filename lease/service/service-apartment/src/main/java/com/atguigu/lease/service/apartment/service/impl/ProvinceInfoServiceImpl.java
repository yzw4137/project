package com.atguigu.lease.service.apartment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.lease.model.entity.ProvinceInfo;
import com.atguigu.lease.service.apartment.service.ProvinceInfoService;
import com.atguigu.lease.service.apartment.mapper.ProvinceInfoMapper;
import org.springframework.stereotype.Service;

@Service
public class ProvinceInfoServiceImpl extends ServiceImpl<ProvinceInfoMapper, ProvinceInfo>
    implements ProvinceInfoService{

}
