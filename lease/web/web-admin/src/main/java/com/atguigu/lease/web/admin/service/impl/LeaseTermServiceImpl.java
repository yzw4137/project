package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.api.apartment.LeaseTermClient;
import com.atguigu.lease.model.entity.LeaseTerm;
import com.atguigu.lease.web.admin.service.LeaseTermService;
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
    public List<LeaseTerm> list() {
        return leaseTermClient.listLeaseTerm().getData();
    }

    @Override
    public boolean saveOrUpdate(LeaseTerm leaseTerm) {
        leaseTermClient.saveOrUpdate(leaseTerm);
        return true;
    }

    @Override
    public boolean removeById(java.io.Serializable id) {
        leaseTermClient.deleteLeaseTermById((Long) id);
        return true;
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
