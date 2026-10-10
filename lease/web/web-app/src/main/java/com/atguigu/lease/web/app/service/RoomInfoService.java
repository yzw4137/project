package com.atguigu.lease.web.app.service;

import com.atguigu.lease.model.entity.RoomInfo;
import com.atguigu.lease.model.vo.room.AppRoomDetailVo;
import com.atguigu.lease.model.vo.room.AppRoomItemVo;
import com.atguigu.lease.model.vo.room.AppRoomQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author liubo
* @description 针对表【room_info(房间信息表)】的数据库操作Service
* @createDate 2023-07-26 11:12:39
*/
public interface RoomInfoService extends IService<RoomInfo> {
    IPage<AppRoomItemVo> pageItem(Page<AppRoomItemVo> page, AppRoomQueryVo queryVo);

    IPage<AppRoomItemVo> pageItemByApartmentId(Page<AppRoomItemVo> page, Long id);

    AppRoomDetailVo getDetailById(Long id);
}
