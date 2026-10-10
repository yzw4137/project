package com.atguigu.lease.service.user.mapper;

import com.atguigu.lease.model.entity.SystemUser;
import com.atguigu.lease.model.vo.system.user.SystemUserItemVo;
import com.atguigu.lease.model.vo.system.user.SystemUserQueryVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Select;

public interface SystemUserMapper extends BaseMapper<SystemUser> {

    IPage<SystemUserItemVo> pageSystemUser(Page<SystemUserItemVo> page, SystemUserQueryVo queryVo);

    @Select("select id, username, password, name, type, phone, avatar_url, additional_info, post_id, status from system_user where is_deleted = 0 and username = #{username}")
    SystemUser selectOneByUsername(String username);
}
