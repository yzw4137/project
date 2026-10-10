package com.atguigu.lease.service.apartment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.lease.model.entity.LeaseTerm;
import com.atguigu.lease.service.apartment.service.LeaseTermService;
import com.atguigu.lease.service.apartment.mapper.LeaseTermMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaseTermServiceImpl extends ServiceImpl<LeaseTermMapper, LeaseTerm>
    implements LeaseTermService{

    @Autowired
    private LeaseTermMapper leaseTermMapper;

    @Override
    public List<LeaseTerm> listByRoomId(Long id) {
        return leaseTermMapper.selectListByRoomId(id);
    }
}
