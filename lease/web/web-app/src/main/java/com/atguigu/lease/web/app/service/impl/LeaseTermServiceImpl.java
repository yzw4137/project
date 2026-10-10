package com.atguigu.lease.web.app.service.impl;

import com.atguigu.lease.api.apartment.LeaseTermClient;
import com.atguigu.lease.model.entity.LeaseTerm;
import com.atguigu.lease.web.app.service.LeaseTermService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaseTermServiceImpl implements LeaseTermService {

    @Autowired
    private LeaseTermClient leaseTermClient;

    @Override
    public List<LeaseTerm> listByRoomId(Long id) {
        return leaseTermClient.listByRoomId(id).getData();
    }

    @Override
    public BaseMapper<LeaseTerm> getBaseMapper() {
        return null;
    }

    @Override
    public Class<LeaseTerm> getEntityClass() {
        return LeaseTerm.class;
    }

    @Override
    public boolean saveBatch(java.util.Collection<LeaseTerm> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean saveOrUpdateBatch(java.util.Collection<LeaseTerm> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean updateBatchById(java.util.Collection<LeaseTerm> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean saveOrUpdate(LeaseTerm entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public LeaseTerm getOne(Wrapper<LeaseTerm> queryWrapper, boolean throwEx) {
        throw new UnsupportedOperationException();
    }

    @Override
    public java.util.Map<String, Object> getMap(Wrapper<LeaseTerm> queryWrapper) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <V> V getObj(Wrapper<LeaseTerm> queryWrapper, java.util.function.Function<? super Object, V> mapper) {
        throw new UnsupportedOperationException();
    }
}
