package com.atguigu.lease.service.apartment.mapper;

import com.atguigu.lease.model.entity.ApartmentInfo;
import com.atguigu.lease.model.vo.apartment.ApartmentItemVo;
import com.atguigu.lease.model.vo.apartment.ApartmentQueryVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public interface ApartmentInfoMapper extends BaseMapper<ApartmentInfo> {

    IPage<ApartmentItemVo> pageItem(Page<ApartmentItemVo> page, ApartmentQueryVo queryVo);
}
