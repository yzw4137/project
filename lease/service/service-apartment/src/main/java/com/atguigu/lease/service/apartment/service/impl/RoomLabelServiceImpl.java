package com.atguigu.lease.service.apartment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.lease.model.entity.RoomLabel;
import com.atguigu.lease.service.apartment.service.RoomLabelService;
import com.atguigu.lease.service.apartment.mapper.RoomLabelMapper;
import org.springframework.stereotype.Service;

@Service
public class RoomLabelServiceImpl extends ServiceImpl<RoomLabelMapper, RoomLabel>
    implements RoomLabelService{

}
