package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.api.apartment.RoomClient;
import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.entity.RoomInfo;
import com.atguigu.lease.model.enums.ReleaseStatus;
import com.atguigu.lease.model.vo.room.RoomDetailVo;
import com.atguigu.lease.model.vo.room.RoomItemVo;
import com.atguigu.lease.model.vo.room.RoomQueryVo;
import com.atguigu.lease.model.vo.room.RoomSubmitVo;
import com.atguigu.lease.web.admin.service.RoomInfoService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomInfoServiceImpl implements RoomInfoService {

    @Autowired
    private RoomClient roomClient;

    @Override
    public void saveOrUpdateRoom(RoomSubmitVo roomSubmitVo) {
        roomClient.saveOrUpdate(roomSubmitVo);
    }

    @Override
    public IPage<RoomItemVo> pageRoomItemByQuery(Page<RoomItemVo> page, RoomQueryVo queryVo) {
        Result<IPage<RoomItemVo>> result =
                roomClient.pageItem(page.getCurrent(), page.getSize(), queryVo);
        return result.getData();
    }

    @Override
    public RoomDetailVo getRoomDetailById(Long id) {
        return roomClient.getDetailById(id).getData();
    }

    @Override
    public void removeRoomById(Long id) {
        roomClient.removeById(id);
    }

    @Override
    public boolean update(Wrapper<RoomInfo> updateWrapper) {
        Long id = (Long) updateWrapper.getParamNameValuePairs().values().stream()
                .filter(v -> v instanceof Long).findFirst().orElse(null);
        Object status = updateWrapper.getParamNameValuePairs().values().stream()
                .filter(v -> v instanceof ReleaseStatus).findFirst().orElse(null);
        roomClient.updateReleaseStatusById(id, (ReleaseStatus) status);
        return true;
    }

    @Override
    public List<RoomInfo> list(Wrapper<RoomInfo> queryWrapper) {
        Long apartmentId = (Long) queryWrapper.getParamNameValuePairs().values().stream()
                .filter(v -> v instanceof Long).findFirst().orElse(null);
        return roomClient.listBasicByApartmentId(apartmentId).getData();
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
