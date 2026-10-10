package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.api.apartment.FeeClient;
import com.atguigu.lease.model.entity.FeeValue;
import com.atguigu.lease.web.admin.service.FeeValueService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeeValueServiceImpl implements FeeValueService {

    @Autowired
    private FeeClient feeClient;

    @Override
    public boolean saveOrUpdate(FeeValue feeValue) {
        feeClient.saveOrUpdateFeeValue(feeValue);
        return true;
    }

    @Override
    public boolean removeById(java.io.Serializable id) {
        feeClient.deleteFeeValueById((Long) id);
        return true;
    }

    @Override
    public boolean remove(Wrapper<FeeValue> wrapper) {
        Long feeKeyId = (Long) wrapper.getParamNameValuePairs().values().iterator().next();
        feeClient.deleteFeeKeyById(feeKeyId);
        return true;
    }

    @Override
    public BaseMapper<FeeValue> getBaseMapper() {
        return null;
    }

    @Override
    public Class<FeeValue> getEntityClass() {
        return FeeValue.class;
    }

    @Override
    public boolean saveBatch(java.util.Collection<FeeValue> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean saveOrUpdateBatch(java.util.Collection<FeeValue> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean updateBatchById(java.util.Collection<FeeValue> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public FeeValue getOne(Wrapper<FeeValue> queryWrapper, boolean throwEx) {
        throw new UnsupportedOperationException();
    }

    @Override
    public java.util.Map<String, Object> getMap(Wrapper<FeeValue> queryWrapper) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <V> V getObj(Wrapper<FeeValue> queryWrapper, java.util.function.Function<? super Object, V> mapper) {
        throw new UnsupportedOperationException();
    }
}
