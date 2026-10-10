package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.model.entity.GraphInfo;
import com.atguigu.lease.web.admin.service.GraphInfoService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Service;

@Service
public class GraphInfoServiceImpl implements GraphInfoService {

    @Override
    public BaseMapper<GraphInfo> getBaseMapper() {
        return null;
    }

    @Override
    public Class<GraphInfo> getEntityClass() {
        return GraphInfo.class;
    }

    @Override
    public boolean saveBatch(java.util.Collection<GraphInfo> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean saveOrUpdateBatch(java.util.Collection<GraphInfo> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean updateBatchById(java.util.Collection<GraphInfo> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean saveOrUpdate(GraphInfo entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GraphInfo getOne(Wrapper<GraphInfo> queryWrapper, boolean throwEx) {
        throw new UnsupportedOperationException();
    }

    @Override
    public java.util.Map<String, Object> getMap(Wrapper<GraphInfo> queryWrapper) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <V> V getObj(Wrapper<GraphInfo> queryWrapper, java.util.function.Function<? super Object, V> mapper) {
        throw new UnsupportedOperationException();
    }
}
