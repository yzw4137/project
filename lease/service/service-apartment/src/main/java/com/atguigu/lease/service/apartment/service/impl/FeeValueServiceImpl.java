package com.atguigu.lease.service.apartment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.lease.model.entity.FeeValue;
import com.atguigu.lease.service.apartment.service.FeeValueService;
import com.atguigu.lease.service.apartment.mapper.FeeValueMapper;
import org.springframework.stereotype.Service;

@Service
public class FeeValueServiceImpl extends ServiceImpl<FeeValueMapper, FeeValue>
    implements FeeValueService{

}
