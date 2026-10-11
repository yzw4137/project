package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.api.user.SystemUserClient;
import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.entity.SystemUser;
import com.atguigu.lease.model.enums.BaseStatus;
import com.atguigu.lease.web.admin.service.SystemUserService;
import com.atguigu.lease.model.vo.system.user.SystemUserItemVo;
import com.atguigu.lease.model.vo.system.user.SystemUserQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemUserServiceImpl implements SystemUserService {

    @Autowired
    private SystemUserClient systemUserClient;

    @Override
    public IPage<SystemUserItemVo> pageSystemUser(Page<SystemUserItemVo> page, SystemUserQueryVo queryVo) {
        Result<Page<SystemUserItemVo>> result =
                systemUserClient.page(page.getCurrent(), page.getSize(), queryVo);
        return result.getData();
    }

    @Override
    public SystemUserItemVo getSystemUserById(Long id) {
        return systemUserClient.getById(id).getData();
    }

    @Override
    public boolean saveOrUpdate(SystemUser systemUser) {
        systemUserClient.saveOrUpdate(systemUser);
        return true;
    }

    @Override
    public boolean removeById(Long id) {
        systemUserClient.deleteById(id);
        return true;
    }

    @Override
    public long count(LambdaQueryWrapper<SystemUser> queryWrapper) {
        String username = null;
        for (Object v : queryWrapper.getParamNameValuePairs().values()) {
            if (v instanceof String) {
                username = (String) v;
            }
        }
        Result<Boolean> result = systemUserClient.isUserNameAvailable(username);
        Boolean available = result.getData();
        return Boolean.TRUE.equals(available) ? 0L : 1L;
    }

    @Override
    public boolean update(LambdaUpdateWrapper<SystemUser> updateWrapper) {
        Long id = null;
        BaseStatus status = null;
        for (Object v : updateWrapper.getParamNameValuePairs().values()) {
            if (v instanceof Long) {
                id = (Long) v;
            } else if (v instanceof BaseStatus) {
                status = (BaseStatus) v;
            }
        }
        systemUserClient.updateStatusByUserId(id, status);
        return true;
    }
}
