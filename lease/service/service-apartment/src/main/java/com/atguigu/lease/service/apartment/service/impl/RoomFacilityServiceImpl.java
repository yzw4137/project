package com.atguigu.lease.service.apartment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.lease.model.entity.RoomFacility;
import com.atguigu.lease.service.apartment.service.RoomFacilityService;
import com.atguigu.lease.service.apartment.mapper.RoomFacilityMapper;
import org.springframework.stereotype.Service;

@Service
public class RoomFacilityServiceImpl extends ServiceImpl<RoomFacilityMapper, RoomFacility>
    implements RoomFacilityService{

}
