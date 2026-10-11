package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.api.user.SystemUserClient;
import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.entity.SystemUser;
import com.atguigu.lease.model.enums.BaseStatus;
import com.atguigu.lease.web.admin.service.SystemUserService;
import com.atguigu.lease.model.vo.system.user.SystemUserItemVo;
import com.atguigu.lease.model.vo.system.user.SystemUserQueryVo;
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
    public boolean isUserNameAvailable(String username) {
        Result<Boolean> result = systemUserClient.isUserNameAvailable(username);
        return Boolean.TRUE.equals(result.getData());
    }

    @Override
    public boolean updateStatusById(Long id, BaseStatus status) {
        systemUserClient.updateStatusByUserId(id, status);
        return true;
    }
}
