package com.atguigu.lease.service.apartment.mapper;

import com.atguigu.lease.model.entity.FeeValue;
import com.atguigu.lease.model.vo.fee.FeeValueVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface FeeValueMapper extends BaseMapper<FeeValue> {

    List<FeeValueVo> selectListByApartmentId(Long id);
}
