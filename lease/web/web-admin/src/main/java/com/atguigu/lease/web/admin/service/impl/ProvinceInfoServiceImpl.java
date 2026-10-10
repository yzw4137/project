package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.api.apartment.RegionClient;
import com.atguigu.lease.model.entity.ProvinceInfo;
import com.atguigu.lease.web.admin.service.ProvinceInfoService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceInfoServiceImpl implements ProvinceInfoService {

    @Autowired
    private RegionClient regionClient;

    @Override
    public List<ProvinceInfo> list() {
        return regionClient.listProvince().getData();
    }

    @Override
    public List<ProvinceInfo> list(Wrapper<ProvinceInfo> queryWrapper) {
        return list();
    }

    @Override
    public BaseMapper<ProvinceInfo> getBaseMapper() {
        return null;
    }

    @Override
    public Class<ProvinceInfo> getEntityClass() {
        return ProvinceInfo.class;
    }

    @Override
    public boolean saveBatch(java.util.Collection<ProvinceInfo> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean saveOrUpdateBatch(java.util.Collection<ProvinceInfo> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean updateBatchById(java.util.Collection<ProvinceInfo> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean saveOrUpdate(ProvinceInfo entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ProvinceInfo getOne(Wrapper<ProvinceInfo> queryWrapper, boolean throwEx) {
        throw new UnsupportedOperationException();
    }

    @Override
    public java.util.Map<String, Object> getMap(Wrapper<ProvinceInfo> queryWrapper) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <V> V getObj(Wrapper<ProvinceInfo> queryWrapper, java.util.function.Function<? super Object, V> mapper) {
        throw new UnsupportedOperationException();
    }
}
