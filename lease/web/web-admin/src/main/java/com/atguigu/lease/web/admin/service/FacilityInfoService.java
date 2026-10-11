package com.atguigu.lease.web.admin.service;

import com.atguigu.lease.model.entity.FacilityInfo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.io.Serializable;
import java.util.List;

/**
* @author liubo
* @description 针对表【facility_info(配套信息表)】的数据库操作Service
* @createDate 2023-07-24 15:48:00
*/
public interface FacilityInfoService {

    List<FacilityInfo> list(LambdaQueryWrapper<FacilityInfo> queryWrapper);

    boolean saveOrUpdate(FacilityInfo facilityInfo);

    boolean removeById(Serializable id);
}
