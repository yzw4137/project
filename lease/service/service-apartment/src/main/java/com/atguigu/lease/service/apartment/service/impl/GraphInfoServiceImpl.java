package com.atguigu.lease.service.apartment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.lease.model.entity.GraphInfo;
import com.atguigu.lease.service.apartment.service.GraphInfoService;
import com.atguigu.lease.service.apartment.mapper.GraphInfoMapper;
import org.springframework.stereotype.Service;

@Service
public class GraphInfoServiceImpl extends ServiceImpl<GraphInfoMapper, GraphInfo>
    implements GraphInfoService{

}
