package com.atguigu.lease.service.apartment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.lease.model.entity.AttrValue;
import com.atguigu.lease.service.apartment.service.AttrValueService;
import com.atguigu.lease.service.apartment.mapper.AttrValueMapper;
import org.springframework.stereotype.Service;

@Service
public class AttrValueServiceImpl extends ServiceImpl<AttrValueMapper, AttrValue>
    implements AttrValueService{

}
