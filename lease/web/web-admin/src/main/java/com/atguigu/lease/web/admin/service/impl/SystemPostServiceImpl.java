package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.api.user.SystemPostClient;
import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.entity.SystemPost;
import com.atguigu.lease.model.enums.BaseStatus;
import com.atguigu.lease.web.admin.service.SystemPostService;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
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
        Result<Page<SystemPost>> result = systemPostClient.page(page.getCurrent(), page.getSize());
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
    public boolean update(LambdaUpdateWrapper<SystemPost> updateWrapper) {
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
}
