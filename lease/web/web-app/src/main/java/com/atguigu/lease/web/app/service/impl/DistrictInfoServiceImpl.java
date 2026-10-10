package com.atguigu.lease.web.app.service.impl;

import com.atguigu.lease.api.apartment.RegionClient;
import com.atguigu.lease.model.entity.DistrictInfo;
import com.atguigu.lease.web.app.service.DistrictInfoService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictInfoServiceImpl implements DistrictInfoService {

    @Autowired
    private RegionClient regionClient;

    @Override
    public List<DistrictInfo> list(Wrapper<DistrictInfo> queryWrapper) {
        Long cityId = (Long) queryWrapper.getParamNameValuePairs().values().iterator().next();
        return regionClient.listDistrictInfoByCityId(cityId).getData();
    }

    @Override
    public BaseMapper<DistrictInfo> getBaseMapper() {
        return null;
    }

    @Override
    public Class<DistrictInfo> getEntityClass() {
        return DistrictInfo.class;
    }

    @Override
    public boolean saveBatch(java.util.Collection<DistrictInfo> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean saveOrUpdateBatch(java.util.Collection<DistrictInfo> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean updateBatchById(java.util.Collection<DistrictInfo> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean saveOrUpdate(DistrictInfo entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DistrictInfo getOne(Wrapper<DistrictInfo> queryWrapper, boolean throwEx) {
        throw new UnsupportedOperationException();
    }

    @Override
    public java.util.Map<String, Object> getMap(Wrapper<DistrictInfo> queryWrapper) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <V> V getObj(Wrapper<DistrictInfo> queryWrapper, java.util.function.Function<? super Object, V> mapper) {
        throw new UnsupportedOperationException();
    }
}
