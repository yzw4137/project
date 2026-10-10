package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.api.apartment.FacilityClient;
import com.atguigu.lease.model.entity.FacilityInfo;
import com.atguigu.lease.model.enums.ItemType;
import com.atguigu.lease.web.admin.service.FacilityInfoService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacilityInfoServiceImpl implements FacilityInfoService {

    @Autowired
    private FacilityClient facilityClient;

    @Override
    public List<FacilityInfo> list(Wrapper<FacilityInfo> queryWrapper) {
        ItemType type = (ItemType) queryWrapper.getParamNameValuePairs().values().stream()
                .filter(v -> v instanceof ItemType).findFirst().orElse(null);
        return facilityClient.listFacility(type).getData();
    }

    @Override
    public boolean saveOrUpdate(FacilityInfo facilityInfo) {
        facilityClient.saveOrUpdate(facilityInfo);
        return true;
    }

    @Override
    public boolean removeById(java.io.Serializable id) {
        facilityClient.removeFacilityById((Long) id);
        return true;
    }

    @Override
    public BaseMapper<FacilityInfo> getBaseMapper() {
        return null;
    }

    @Override
    public Class<FacilityInfo> getEntityClass() {
        return FacilityInfo.class;
    }

    @Override
    public boolean saveBatch(java.util.Collection<FacilityInfo> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean saveOrUpdateBatch(java.util.Collection<FacilityInfo> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean updateBatchById(java.util.Collection<FacilityInfo> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public FacilityInfo getOne(Wrapper<FacilityInfo> queryWrapper, boolean throwEx) {
        throw new UnsupportedOperationException();
    }

    @Override
    public java.util.Map<String, Object> getMap(Wrapper<FacilityInfo> queryWrapper) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <V> V getObj(Wrapper<FacilityInfo> queryWrapper, java.util.function.Function<? super Object, V> mapper) {
        throw new UnsupportedOperationException();
    }
}
