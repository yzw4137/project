package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.api.apartment.FeeClient;
import com.atguigu.lease.model.entity.FeeValue;
import com.atguigu.lease.web.admin.service.FeeValueService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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

    @Override
    public boolean remove(LambdaQueryWrapper<FeeValue> wrapper) {
        Long feeKeyId = (Long) wrapper.getParamNameValuePairs().values().iterator().next();
        feeClient.deleteFeeKeyById(feeKeyId);
        return true;
    }
}
