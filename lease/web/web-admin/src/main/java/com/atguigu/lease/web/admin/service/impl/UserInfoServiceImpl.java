package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.api.user.UserInfoClient;
import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.entity.UserInfo;
import com.atguigu.lease.model.enums.BaseStatus;
import com.atguigu.lease.web.admin.service.UserInfoService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoClient userInfoClient;

    @Override
    public IPage<UserInfo> page(Page<UserInfo> page, Wrapper<UserInfo> queryWrapper) {
        com.atguigu.lease.model.vo.user.UserInfoQueryVo q = new com.atguigu.lease.model.vo.user.UserInfoQueryVo();
        if (queryWrapper != null && queryWrapper.getParamNameValuePairs() != null) {
            for (Object v : queryWrapper.getParamNameValuePairs().values()) {
                if (v instanceof String) {
                    q.setPhone((String) v);
                } else if (v instanceof BaseStatus) {
                    q.setStatus((BaseStatus) v);
                }
            }
        }
        Result<IPage<UserInfo>> result = userInfoClient.pageUserInfo(page.getCurrent(), page.getSize(), q);
        return result.getData();
    }

    @Override
    public boolean update(Wrapper<UserInfo> updateWrapper) {
        Long id = null;
        BaseStatus status = null;
        for (Object v : updateWrapper.getParamNameValuePairs().values()) {
            if (v instanceof Long) {
                id = (Long) v;
            } else if (v instanceof BaseStatus) {
                status = (BaseStatus) v;
            }
        }
        userInfoClient.updateStatusById(id, status);
        return true;
    }

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
