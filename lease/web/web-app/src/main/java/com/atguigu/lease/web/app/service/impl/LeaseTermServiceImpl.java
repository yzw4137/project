package com.atguigu.lease.web.app.service.impl;

import com.atguigu.lease.api.apartment.LeaseTermClient;
import com.atguigu.lease.model.entity.LeaseTerm;
import com.atguigu.lease.web.app.service.LeaseTermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaseTermServiceImpl implements LeaseTermService {

    @Autowired
    private LeaseTermClient leaseTermClient;

    @Override
    public List<LeaseTerm> listByRoomId(Long id) {
        return leaseTermClient.listByRoomId(id).getData();
    }
}
