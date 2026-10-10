package com.atguigu.lease.web.app.service.impl;

import com.atguigu.lease.api.user.UserInfoClient;
import com.atguigu.lease.model.entity.UserInfo;
import com.atguigu.lease.web.app.service.UserInfoService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoClient userInfoClient;

    @Override
    public BaseMapper<UserInfo> getBaseMapper() {
        return null;
    }

    @Override
    public Class<UserInfo> getEntityClass() {
        return UserInfo.class;
    }

    @Override
    public boolean saveBatch(java.util.Collection<UserInfo> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean saveOrUpdateBatch(java.util.Collection<UserInfo> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean updateBatchById(java.util.Collection<UserInfo> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean saveOrUpdate(UserInfo entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public UserInfo getOne(Wrapper<UserInfo> queryWrapper, boolean throwEx) {
        throw new UnsupportedOperationException();
    }

    @Override
    public java.util.Map<String, Object> getMap(Wrapper<UserInfo> queryWrapper) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <V> V getObj(Wrapper<UserInfo> queryWrapper, java.util.function.Function<? super Object, V> mapper) {
        throw new UnsupportedOperationException();
    }
}
