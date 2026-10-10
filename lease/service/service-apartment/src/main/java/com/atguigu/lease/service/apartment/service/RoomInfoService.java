package com.atguigu.lease.service.apartment.service;

import com.atguigu.lease.model.entity.RoomInfo;
import com.atguigu.lease.model.vo.room.AppRoomDetailVo;
import com.atguigu.lease.model.vo.room.AppRoomItemVo;
import com.atguigu.lease.model.vo.room.AppRoomQueryVo;
import com.atguigu.lease.model.vo.room.RoomDetailVo;
import com.atguigu.lease.model.vo.room.RoomItemVo;
import com.atguigu.lease.model.vo.room.RoomQueryVo;
import com.atguigu.lease.model.vo.room.RoomSubmitVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;

public interface RoomInfoService extends IService<RoomInfo> {

    void saveOrUpdateRoom(RoomSubmitVo roomSubmitVo);

    IPage<RoomItemVo> pageRoomItemByQuery(Page<RoomItemVo> page, RoomQueryVo queryVo);

    RoomDetailVo getRoomDetailById(Long id);

    void removeRoomById(Long id);

    IPage<AppRoomItemVo> pageItem(Page<AppRoomItemVo> page, AppRoomQueryVo queryVo);

    IPage<AppRoomItemVo> pageItemByApartmentId(Page<AppRoomItemVo> page, Long id);

    AppRoomDetailVo getDetailById(Long id);

    BigDecimal selectMinRentByApartmentId(Long apartmentId);
}
