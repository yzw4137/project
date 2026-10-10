package com.atguigu.lease.service.apartment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.lease.model.entity.LabelInfo;
import com.atguigu.lease.service.apartment.service.LabelInfoService;
import com.atguigu.lease.service.apartment.mapper.LabelInfoMapper;
import org.springframework.stereotype.Service;

@Service
public class LabelInfoServiceImpl extends ServiceImpl<LabelInfoMapper, LabelInfo>
    implements LabelInfoService{

}
