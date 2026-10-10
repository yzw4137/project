package com.atguigu.lease.service.apartment.mapper;

import com.atguigu.lease.model.entity.RoomInfo;
import com.atguigu.lease.model.vo.room.AppRoomItemVo;
import com.atguigu.lease.model.vo.room.AppRoomQueryVo;
import com.atguigu.lease.model.vo.room.RoomItemVo;
import com.atguigu.lease.model.vo.room.RoomQueryVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.math.BigDecimal;

public interface RoomInfoMapper extends BaseMapper<RoomInfo> {

    IPage<RoomItemVo> pageRoomItemByQuery(Page<RoomItemVo> page, RoomQueryVo queryVo);

    IPage<AppRoomItemVo> pageItem(Page<AppRoomItemVo> page, AppRoomQueryVo queryVo);

    IPage<AppRoomItemVo> pageItemByApartmentId(Page<AppRoomItemVo> page, Long id);

    BigDecimal selectMinRentByApartmentId(Long apartmentId);
}
