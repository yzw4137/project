package com.atguigu.lease.web.app.service;

import com.atguigu.lease.model.entity.CityInfo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.util.List;

/**
* @author liubo
* @description 针对表【city_info】的数据库操作Service
* @createDate 2023-07-26 11:12:39
*/
public interface CityInfoService {

    List<CityInfo> list(LambdaQueryWrapper<CityInfo> queryWrapper);
}
