package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.api.apartment.RegionClient;
import com.atguigu.lease.model.entity.CityInfo;
import com.atguigu.lease.web.admin.service.CityInfoService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityInfoServiceImpl implements CityInfoService {

    @Autowired
    private RegionClient regionClient;

    @Override
    public List<CityInfo> list() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<CityInfo> list(Wrapper<CityInfo> queryWrapper) {
        Long provinceId = (Long) queryWrapper.getParamNameValuePairs().values().iterator().next();
        return regionClient.listCityInfoByProvinceId(provinceId).getData();
    }

    @Override
    public BaseMapper<CityInfo> getBaseMapper() {
        return null;
    }

    @Override
    public Class<CityInfo> getEntityClass() {
        return CityInfo.class;
    }

    @Override
    public boolean saveBatch(java.util.Collection<CityInfo> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean saveOrUpdateBatch(java.util.Collection<CityInfo> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean updateBatchById(java.util.Collection<CityInfo> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean saveOrUpdate(CityInfo entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public CityInfo getOne(Wrapper<CityInfo> queryWrapper, boolean throwEx) {
        throw new UnsupportedOperationException();
    }

    @Override
    public java.util.Map<String, Object> getMap(Wrapper<CityInfo> queryWrapper) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <V> V getObj(Wrapper<CityInfo> queryWrapper, java.util.function.Function<? super Object, V> mapper) {
        throw new UnsupportedOperationException();
    }
}
