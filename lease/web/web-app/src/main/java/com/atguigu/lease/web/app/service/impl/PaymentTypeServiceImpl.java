package com.atguigu.lease.web.app.service.impl;

import com.atguigu.lease.api.apartment.PaymentTypeClient;
import com.atguigu.lease.model.entity.PaymentType;
import com.atguigu.lease.web.app.service.PaymentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentTypeServiceImpl implements PaymentTypeService {

    @Autowired
    private PaymentTypeClient paymentTypeClient;

    @Override
    public List<PaymentType> listByRoomId(Long id) {
        return paymentTypeClient.listByRoomId(id).getData();
    }

    @Override
    public List<PaymentType> list() {
        return paymentTypeClient.listPaymentType().getData();
    }
}
