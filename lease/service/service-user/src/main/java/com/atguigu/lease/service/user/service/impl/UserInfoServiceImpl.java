package com.atguigu.lease.service.user.service.impl;

import com.atguigu.lease.model.entity.UserInfo;
import com.atguigu.lease.model.enums.BaseStatus;
import com.atguigu.lease.model.vo.user.UserInfoQueryVo;
import com.atguigu.lease.service.user.mapper.UserInfoMapper;
import com.atguigu.lease.service.user.service.UserInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
        implements UserInfoService {

    @Override
    public IPage<UserInfo> pageUserInfo(Page<UserInfo> page, UserInfoQueryVo queryVo) {
        LambdaQueryWrapper<UserInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(queryVo.getPhone() != null, UserInfo::getPhone, queryVo.getPhone());
        wrapper.eq(queryVo.getStatus() != null, UserInfo::getStatus, queryVo.getStatus());
        return this.page(page, wrapper);
    }

    @Override
    public void updateStatusById(Long id, BaseStatus status) {
        LambdaUpdateWrapper<UserInfo> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(UserInfo::getId, id);
        wrapper.set(UserInfo::getStatus, status);
        this.update(wrapper);
    }

    @Override
    public UserInfo getByPhone(String phone) {
        LambdaQueryWrapper<UserInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserInfo::getPhone, phone);
        return this.getOne(wrapper);
    }

    @Override
    public void register(UserInfo userInfo) {
        this.save(userInfo);
    }
}
