package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.api.apartment.PaymentTypeClient;
import com.atguigu.lease.model.entity.PaymentType;
import com.atguigu.lease.web.admin.service.PaymentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class PaymentTypeServiceImpl implements PaymentTypeService {

    @Autowired
    private PaymentTypeClient paymentTypeClient;

    @Override
    public List<PaymentType> list() {
        return paymentTypeClient.listPaymentType().getData();
    }

    @Override
    public boolean saveOrUpdate(PaymentType paymentType) {
        paymentTypeClient.saveOrUpdatePaymentType(paymentType);
        return true;
    }

    @Override
    public boolean removeById(Serializable id) {
        paymentTypeClient.deletePaymentById((Long) id);
        return true;
    }
}
