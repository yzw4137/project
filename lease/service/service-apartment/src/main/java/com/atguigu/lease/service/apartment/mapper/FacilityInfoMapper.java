package com.atguigu.lease.service.apartment.mapper;

import com.atguigu.lease.model.entity.FacilityInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface FacilityInfoMapper extends BaseMapper<FacilityInfo> {

    List<FacilityInfo> selectListByApartmentId(Long id);

    List<FacilityInfo> selectListByRoomId(Long id);
}
