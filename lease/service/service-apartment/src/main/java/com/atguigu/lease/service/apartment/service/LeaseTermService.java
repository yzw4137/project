package com.atguigu.lease.service.apartment.service;

import com.atguigu.lease.model.entity.LeaseTerm;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface LeaseTermService extends IService<LeaseTerm> {

    List<LeaseTerm> listByRoomId(Long id);
}
