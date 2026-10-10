package com.atguigu.lease.service.apartment.mapper;

import com.atguigu.lease.model.entity.LabelInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface LabelInfoMapper extends BaseMapper<LabelInfo> {

    List<LabelInfo> selectListByApartmentId(Long id);

    List<LabelInfo> selectListByRoomId(Long id);
}
