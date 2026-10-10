package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.api.user.BrowsingHistoryClient;
import com.atguigu.lease.model.entity.BrowsingHistory;
import com.atguigu.lease.web.admin.service.BrowsingHistoryService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrowsingHistoryServiceImpl implements BrowsingHistoryService {

    @Autowired
    private BrowsingHistoryClient browsingHistoryClient;

    @Override
    public BaseMapper<BrowsingHistory> getBaseMapper() {
        return null;
    }

    @Override
    public Class<BrowsingHistory> getEntityClass() {
        return BrowsingHistory.class;
    }

    @Override
    public boolean saveBatch(java.util.Collection<BrowsingHistory> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean saveOrUpdateBatch(java.util.Collection<BrowsingHistory> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean updateBatchById(java.util.Collection<BrowsingHistory> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean saveOrUpdate(BrowsingHistory entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public BrowsingHistory getOne(Wrapper<BrowsingHistory> queryWrapper, boolean throwEx) {
        throw new UnsupportedOperationException();
    }

    @Override
    public java.util.Map<String, Object> getMap(Wrapper<BrowsingHistory> queryWrapper) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <V> V getObj(Wrapper<BrowsingHistory> queryWrapper, java.util.function.Function<? super Object, V> mapper) {
        throw new UnsupportedOperationException();
    }
}
