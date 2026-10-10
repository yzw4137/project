package com.atguigu.lease.service.apartment.mapper;

import com.atguigu.lease.model.entity.AttrValue;
import com.atguigu.lease.model.vo.attr.AttrValueVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface AttrValueMapper extends BaseMapper<AttrValue> {

    List<AttrValueVo> selectListByRoomId(Long id);
}
