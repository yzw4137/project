package com.atguigu.lease.web.admin.service;

import com.atguigu.lease.model.entity.SystemUser;
import com.atguigu.lease.model.vo.system.user.SystemUserItemVo;
import com.atguigu.lease.model.vo.system.user.SystemUserQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
* @author liubo
* @description 针对表【system_user(员工信息表)】的数据库操作Service
* @createDate 2023-07-24 15:48:00
*/
public interface SystemUserService {

    IPage<SystemUserItemVo> pageSystemUser(Page<SystemUserItemVo> page, SystemUserQueryVo queryVo);

    SystemUserItemVo getSystemUserById(Long id);

    boolean saveOrUpdate(SystemUser systemUser);

    boolean removeById(Long id);

    long count(LambdaQueryWrapper<SystemUser> queryWrapper);

    boolean update(LambdaUpdateWrapper<SystemUser> updateWrapper);
}
