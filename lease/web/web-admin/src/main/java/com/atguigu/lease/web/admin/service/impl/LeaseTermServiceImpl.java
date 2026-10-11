package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.api.apartment.LeaseTermClient;
import com.atguigu.lease.model.entity.LeaseTerm;
import com.atguigu.lease.web.admin.service.LeaseTermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class LeaseTermServiceImpl implements LeaseTermService {

    @Autowired
    private LeaseTermClient leaseTermClient;

    @Override
    public List<LeaseTerm> list() {
        return leaseTermClient.listLeaseTerm().getData();
    }

    @Override
    public boolean saveOrUpdate(LeaseTerm leaseTerm) {
        leaseTermClient.saveOrUpdate(leaseTerm);
        return true;
    }

    @Override
    public boolean removeById(Serializable id) {
        leaseTermClient.deleteLeaseTermById((Long) id);
        return true;
    }
}
