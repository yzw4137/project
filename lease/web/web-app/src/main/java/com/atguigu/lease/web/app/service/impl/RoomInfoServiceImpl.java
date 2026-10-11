package com.atguigu.lease.web.app.service.impl;

import com.atguigu.lease.api.apartment.RoomClient;
import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.vo.room.AppRoomDetailVo;
import com.atguigu.lease.model.vo.room.AppRoomItemVo;
import com.atguigu.lease.model.vo.room.AppRoomQueryVo;
import com.atguigu.lease.web.app.service.RoomInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomInfoServiceImpl implements RoomInfoService {

    @Autowired
    private RoomClient roomClient;

    @Override
    public IPage<AppRoomItemVo> pageItem(Page<AppRoomItemVo> page, AppRoomQueryVo queryVo) {
        Result<Page<AppRoomItemVo>> result =
                roomClient.pageAppItem(page.getCurrent(), page.getSize(), queryVo);
        return result.getData();
    }

    @Override
    public IPage<AppRoomItemVo> pageItemByApartmentId(Page<AppRoomItemVo> page, Long id) {
        Result<Page<AppRoomItemVo>> result =
                roomClient.pageAppItemByApartmentId(page.getCurrent(), page.getSize(), id);
        return result.getData();
    }

    @Override
    public AppRoomDetailVo getDetailById(Long id) {
        return roomClient.getAppDetailById(id).getData();
    }
}
