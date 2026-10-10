package com.atguigu.lease.service.apartment.mapper;

import com.atguigu.lease.model.entity.FeeKey;
import com.atguigu.lease.model.vo.fee.FeeKeyVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface FeeKeyMapper extends BaseMapper<FeeKey> {

    List<FeeKeyVo> listFeeInfo();
}
