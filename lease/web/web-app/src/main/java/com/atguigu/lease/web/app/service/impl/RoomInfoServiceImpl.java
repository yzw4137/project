package com.atguigu.lease.web.app.service.impl;

import com.atguigu.lease.api.apartment.RoomClient;
import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.entity.RoomInfo;
import com.atguigu.lease.model.vo.room.AppRoomDetailVo;
import com.atguigu.lease.model.vo.room.AppRoomItemVo;
import com.atguigu.lease.model.vo.room.AppRoomQueryVo;
import com.atguigu.lease.web.app.service.RoomInfoService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
        Result<IPage<AppRoomItemVo>> result =
                roomClient.pageAppItem(page.getCurrent(), page.getSize(), queryVo);
        return result.getData();
    }

    @Override
    public IPage<AppRoomItemVo> pageItemByApartmentId(Page<AppRoomItemVo> page, Long id) {
        Result<IPage<AppRoomItemVo>> result =
                roomClient.pageAppItemByApartmentId(page.getCurrent(), page.getSize(), id);
        return result.getData();
    }

    @Override
    public AppRoomDetailVo getDetailById(Long id) {
        return roomClient.getAppDetailById(id).getData();
    }

    @Override
    public BaseMapper<RoomInfo> getBaseMapper() {
        return null;
    }

    @Override
    public Class<RoomInfo> getEntityClass() {
        return RoomInfo.class;
    }

    @Override
    public boolean saveBatch(java.util.Collection<RoomInfo> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean saveOrUpdateBatch(java.util.Collection<RoomInfo> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean updateBatchById(java.util.Collection<RoomInfo> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean saveOrUpdate(RoomInfo entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public RoomInfo getOne(Wrapper<RoomInfo> queryWrapper, boolean throwEx) {
        throw new UnsupportedOperationException();
    }

    @Override
    public java.util.Map<String, Object> getMap(Wrapper<RoomInfo> queryWrapper) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <V> V getObj(Wrapper<RoomInfo> queryWrapper, java.util.function.Function<? super Object, V> mapper) {
        throw new UnsupportedOperationException();
    }
}
