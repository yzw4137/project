package com.atguigu.lease.service.apartment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.lease.model.entity.DistrictInfo;
import com.atguigu.lease.service.apartment.service.DistrictInfoService;
import com.atguigu.lease.service.apartment.mapper.DistrictInfoMapper;
import org.springframework.stereotype.Service;

@Service
public class DistrictInfoServiceImpl extends ServiceImpl<DistrictInfoMapper, DistrictInfo>
    implements DistrictInfoService{

}
