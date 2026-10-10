package com.atguigu.lease.service.apartment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.lease.model.entity.RoomLeaseTerm;
import com.atguigu.lease.service.apartment.service.RoomLeaseTermService;
import com.atguigu.lease.service.apartment.mapper.RoomLeaseTermMapper;
import org.springframework.stereotype.Service;

@Service
public class RoomLeaseTermServiceImpl extends ServiceImpl<RoomLeaseTermMapper, RoomLeaseTerm>
    implements RoomLeaseTermService{

}
