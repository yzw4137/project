package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.api.user.UserInfoClient;
import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.entity.UserInfo;
import com.atguigu.lease.model.enums.BaseStatus;
import com.atguigu.lease.model.vo.user.UserInfoQueryVo;
import com.atguigu.lease.web.admin.service.UserInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoClient userInfoClient;

    @Override
    public IPage<UserInfo> page(Page<UserInfo> page, UserInfoQueryVo queryVo) {
        Result<Page<UserInfo>> result = userInfoClient.pageUserInfo(page.getCurrent(), page.getSize(), queryVo);
        return result.getData();
    }

    @Override
    public boolean updateStatusById(Long id, BaseStatus status) {
        userInfoClient.updateStatusById(id, status);
        return true;
    }
}
