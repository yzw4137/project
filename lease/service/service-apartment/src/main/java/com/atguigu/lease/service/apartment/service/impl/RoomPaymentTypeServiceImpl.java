package com.atguigu.lease.service.apartment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.lease.model.entity.RoomPaymentType;
import com.atguigu.lease.service.apartment.service.RoomPaymentTypeService;
import com.atguigu.lease.service.apartment.mapper.RoomPaymentTypeMapper;
import org.springframework.stereotype.Service;

@Service
public class RoomPaymentTypeServiceImpl extends ServiceImpl<RoomPaymentTypeMapper, RoomPaymentType>
    implements RoomPaymentTypeService{

}
