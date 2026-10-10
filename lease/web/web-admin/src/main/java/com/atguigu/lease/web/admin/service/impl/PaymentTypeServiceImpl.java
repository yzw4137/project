package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.api.apartment.PaymentTypeClient;
import com.atguigu.lease.model.entity.PaymentType;
import com.atguigu.lease.web.admin.service.PaymentTypeService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public boolean removeById(java.io.Serializable id) {
        paymentTypeClient.deletePaymentById((Long) id);
        return true;
    }

    @Override
    public BaseMapper<PaymentType> getBaseMapper() {
        return null;
    }

    @Override
    public Class<PaymentType> getEntityClass() {
        return PaymentType.class;
    }

    @Override
    public boolean saveBatch(java.util.Collection<PaymentType> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean saveOrUpdateBatch(java.util.Collection<PaymentType> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean updateBatchById(java.util.Collection<PaymentType> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public PaymentType getOne(Wrapper<PaymentType> queryWrapper, boolean throwEx) {
        throw new UnsupportedOperationException();
    }

    @Override
    public java.util.Map<String, Object> getMap(Wrapper<PaymentType> queryWrapper) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <V> V getObj(Wrapper<PaymentType> queryWrapper, java.util.function.Function<? super Object, V> mapper) {
        throw new UnsupportedOperationException();
    }
}
