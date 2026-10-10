package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.api.user.SystemPostClient;
import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.entity.SystemPost;
import com.atguigu.lease.model.enums.BaseStatus;
import com.atguigu.lease.web.admin.service.SystemPostService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemPostServiceImpl implements SystemPostService {

    @Autowired
    private SystemPostClient systemPostClient;

    @Override
    public IPage<SystemPost> page(Page<SystemPost> page) {
        Result<IPage<SystemPost>> result = systemPostClient.page(page.getCurrent(), page.getSize());
        return result.getData();
    }

    @Override
    public boolean saveOrUpdate(SystemPost systemPost) {
        systemPostClient.saveOrUpdate(systemPost);
        return true;
    }

    @Override
    public boolean removeById(Long id) {
        systemPostClient.deleteById(id);
        return true;
    }

    @Override
    public SystemPost getById(Long id) {
        Result<SystemPost> result = systemPostClient.getById(id);
        return result.getData();
    }

    @Override
    public List<SystemPost> list() {
        Result<List<SystemPost>> result = systemPostClient.list();
        return result.getData();
    }

    @Override
    public boolean update(Wrapper<SystemPost> updateWrapper) {
        Long id = null;
        BaseStatus status = null;
        for (Object v : updateWrapper.getParamNameValuePairs().values()) {
            if (v instanceof Long) {
                id = (Long) v;
            } else if (v instanceof BaseStatus) {
                status = (BaseStatus) v;
            }
        }
        systemPostClient.updateStatusByPostId(id, status);
        return true;
    }

    @Override
    public BaseMapper<SystemPost> getBaseMapper() {
        return null;
    }

    @Override
    public Class<SystemPost> getEntityClass() {
        return SystemPost.class;
    }

    @Override
    public boolean saveBatch(java.util.Collection<SystemPost> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean saveOrUpdateBatch(java.util.Collection<SystemPost> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean updateBatchById(java.util.Collection<SystemPost> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean saveOrUpdate(SystemPost entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public SystemPost getOne(Wrapper<SystemPost> queryWrapper, boolean throwEx) {
        throw new UnsupportedOperationException();
    }

    @Override
    public java.util.Map<String, Object> getMap(Wrapper<SystemPost> queryWrapper) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <V> V getObj(Wrapper<SystemPost> queryWrapper, java.util.function.Function<? super Object, V> mapper) {
        throw new UnsupportedOperationException();
    }
}
