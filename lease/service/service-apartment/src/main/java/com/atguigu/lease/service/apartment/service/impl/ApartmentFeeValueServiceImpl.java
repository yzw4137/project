package com.atguigu.lease.service.apartment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.lease.model.entity.ApartmentFeeValue;
import com.atguigu.lease.service.apartment.service.ApartmentFeeValueService;
import com.atguigu.lease.service.apartment.mapper.ApartmentFeeValueMapper;
import org.springframework.stereotype.Service;

@Service
public class ApartmentFeeValueServiceImpl extends ServiceImpl<ApartmentFeeValueMapper, ApartmentFeeValue>
    implements ApartmentFeeValueService{

}
