package com.atguigu.lease.service.apartment.mapper;

import com.atguigu.lease.model.entity.AttrKey;
import com.atguigu.lease.model.vo.attr.AttrKeyVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface AttrKeyMapper extends BaseMapper<AttrKey> {

    List<AttrKeyVo> listAttrInfo();
}
