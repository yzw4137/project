package com.atguigu.lease.web.admin.service;

import com.atguigu.lease.model.entity.ProvinceInfo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;

import java.util.List;

/**
* @author liubo
* @description 针对表【province_info】的数据库操作Service
* @createDate 2023-07-24 15:48:00
*/
public interface ProvinceInfoService {

    List<ProvinceInfo> list();

    List<ProvinceInfo> list(Wrapper<ProvinceInfo> queryWrapper);
}
