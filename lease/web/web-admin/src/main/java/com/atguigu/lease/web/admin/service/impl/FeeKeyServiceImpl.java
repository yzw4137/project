package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.api.apartment.FeeClient;
import com.atguigu.lease.model.entity.FeeKey;
import com.atguigu.lease.model.vo.fee.FeeKeyVo;
import com.atguigu.lease.web.admin.service.FeeKeyService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeeKeyServiceImpl implements FeeKeyService {

    @Autowired
    private FeeClient feeClient;

    @Override
    public List<FeeKeyVo> listFeeInfo() {
        return feeClient.feeInfoList().getData();
    }

    @Override
    public boolean saveOrUpdate(FeeKey feeKey) {
        feeClient.saveOrUpdateFeeKey(feeKey);
        return true;
    }

    @Override
    public boolean removeById(java.io.Serializable id) {
        feeClient.deleteFeeKeyById((Long) id);
        return true;
    }

    @Override
    public BaseMapper<FeeKey> getBaseMapper() {
        return null;
    }

    @Override
    public Class<FeeKey> getEntityClass() {
        return FeeKey.class;
    }

    @Override
    public boolean saveBatch(java.util.Collection<FeeKey> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean saveOrUpdateBatch(java.util.Collection<FeeKey> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean updateBatchById(java.util.Collection<FeeKey> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public FeeKey getOne(Wrapper<FeeKey> queryWrapper, boolean throwEx) {
        throw new UnsupportedOperationException();
    }

    @Override
    public java.util.Map<String, Object> getMap(Wrapper<FeeKey> queryWrapper) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <V> V getObj(Wrapper<FeeKey> queryWrapper, java.util.function.Function<? super Object, V> mapper) {
        throw new UnsupportedOperationException();
    }
}
