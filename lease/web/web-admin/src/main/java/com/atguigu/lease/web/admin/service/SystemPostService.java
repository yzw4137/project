package com.atguigu.lease.web.admin.service;

import com.atguigu.lease.model.entity.SystemPost;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
* @author liubo
* @description 针对表【system_post(岗位信息表)】的数据库操作Service
* @createDate 2023-07-24 15:48:00
*/
public interface SystemPostService {

    IPage<SystemPost> page(Page<SystemPost> page);

    boolean saveOrUpdate(SystemPost systemPost);

    boolean removeById(Long id);

    SystemPost getById(Long id);

    List<SystemPost> list();

    boolean update(LambdaUpdateWrapper<SystemPost> updateWrapper);
}
