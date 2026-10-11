package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.api.apartment.FeeClient;
import com.atguigu.lease.model.entity.FeeValue;
import com.atguigu.lease.web.admin.service.FeeValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class FeeValueServiceImpl implements FeeValueService {

    @Autowired
    private FeeClient feeClient;

    @Override
    public boolean saveOrUpdate(FeeValue feeValue) {
        feeClient.saveOrUpdateFeeValue(feeValue);
        return true;
    }

    @Override
    public boolean removeById(Serializable id) {
        feeClient.deleteFeeValueById((Long) id);
        return true;
    }
}
