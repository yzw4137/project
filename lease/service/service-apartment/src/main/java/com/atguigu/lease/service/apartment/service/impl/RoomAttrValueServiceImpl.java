package com.atguigu.lease.service.apartment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.lease.model.entity.RoomAttrValue;
import com.atguigu.lease.service.apartment.service.RoomAttrValueService;
import com.atguigu.lease.service.apartment.mapper.RoomAttrValueMapper;
import org.springframework.stereotype.Service;

@Service
public class RoomAttrValueServiceImpl extends ServiceImpl<RoomAttrValueMapper, RoomAttrValue>
    implements RoomAttrValueService{

}
