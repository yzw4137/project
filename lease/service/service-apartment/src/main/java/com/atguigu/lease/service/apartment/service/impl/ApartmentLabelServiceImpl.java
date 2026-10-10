package com.atguigu.lease.service.apartment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.lease.model.entity.ApartmentLabel;
import com.atguigu.lease.service.apartment.service.ApartmentLabelService;
import com.atguigu.lease.service.apartment.mapper.ApartmentLabelMapper;
import org.springframework.stereotype.Service;

@Service
public class ApartmentLabelServiceImpl extends ServiceImpl<ApartmentLabelMapper, ApartmentLabel>
    implements ApartmentLabelService{

}
