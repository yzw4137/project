package com.atguigu.lease.service.user.service.impl;

import com.atguigu.lease.model.entity.SystemPost;
import com.atguigu.lease.model.entity.SystemUser;
import com.atguigu.lease.model.vo.system.user.SystemUserItemVo;
import com.atguigu.lease.model.vo.system.user.SystemUserQueryVo;
import com.atguigu.lease.service.user.mapper.SystemPostMapper;
import com.atguigu.lease.service.user.mapper.SystemUserMapper;
import com.atguigu.lease.service.user.service.SystemUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser>
        implements SystemUserService {

    @Autowired
    private SystemUserMapper systemUserMapper;

    @Autowired
    private SystemPostMapper systemPostMapper;

    @Override
    public IPage<SystemUserItemVo> pageSystemUser(Page<SystemUserItemVo> page, SystemUserQueryVo queryVo) {
        return systemUserMapper.pageSystemUser(page, queryVo);
    }

    @Override
    public SystemUserItemVo getSystemUserById(Long id) {
        SystemUser systemUser = systemUserMapper.selectById(id);
        SystemPost systemPost = systemPostMapper.selectById(systemUser.getPostId());
        SystemUserItemVo systemUserItemVo = new SystemUserItemVo();
        BeanUtils.copyProperties(systemUser, systemUserItemVo);
        systemUserItemVo.setPostName(systemPost.getName());
        return systemUserItemVo;
    }

    @Override
    public SystemUser getByUsername(String username) {
        return systemUserMapper.selectOneByUsername(username);
    }
}
