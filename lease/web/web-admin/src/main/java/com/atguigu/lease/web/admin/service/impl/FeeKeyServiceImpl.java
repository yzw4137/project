package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.api.apartment.FeeClient;
import com.atguigu.lease.model.entity.FeeKey;
import com.atguigu.lease.model.vo.fee.FeeKeyVo;
import com.atguigu.lease.web.admin.service.FeeKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class FeeKeyServiceImpl implements FeeKeyService {

    @Autowired
    private FeeClient feeClient;

    @Override
    public List<FeeKeyVo> listFeeInfo() {
        return feeClient.feeInfoList().getData();
    }

    @Override
    public boolean saveOrUpdate(FeeKey feeKey) {
        feeClient.saveOrUpdateFeeKey(feeKey);
        return true;
    }

    @Override
    public boolean removeById(Serializable id) {
        feeClient.deleteFeeKeyById((Long) id);
        return true;
    }
}
