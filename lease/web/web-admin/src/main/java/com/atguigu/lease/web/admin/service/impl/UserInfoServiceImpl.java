package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.api.user.UserInfoClient;
import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.entity.UserInfo;
import com.atguigu.lease.model.enums.BaseStatus;
import com.atguigu.lease.web.admin.service.UserInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoClient userInfoClient;

    @Override
    public IPage<UserInfo> page(Page<UserInfo> page, LambdaQueryWrapper<UserInfo> queryWrapper) {
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
        Result<Page<UserInfo>> result = userInfoClient.pageUserInfo(page.getCurrent(), page.getSize(), q);
        return result.getData();
    }

    @Override
    public boolean update(LambdaUpdateWrapper<UserInfo> updateWrapper) {
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
}
