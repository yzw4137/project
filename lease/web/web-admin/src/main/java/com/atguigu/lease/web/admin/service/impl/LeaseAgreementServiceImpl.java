package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.api.apartment.ApartmentClient;
import com.atguigu.lease.api.apartment.LeaseTermClient;
import com.atguigu.lease.api.apartment.PaymentTypeClient;
import com.atguigu.lease.api.apartment.RoomClient;
import com.atguigu.lease.api.lease.LeaseAgreementClient;
import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.entity.*;
import com.atguigu.lease.model.enums.LeaseStatus;
import com.atguigu.lease.model.vo.lease.AgreementQueryVo;
import com.atguigu.lease.web.admin.service.LeaseAgreementService;
import com.atguigu.lease.web.admin.vo.agreement.AgreementVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeaseAgreementServiceImpl implements LeaseAgreementService {

    @Autowired
    private LeaseAgreementClient leaseAgreementClient;

    @Autowired
    private ApartmentClient apartmentClient;

    @Autowired
    private RoomClient roomClient;

    @Autowired
    private PaymentTypeClient paymentTypeClient;

    @Autowired
    private LeaseTermClient leaseTermClient;

    @Override
    public AgreementVo getAgreementById(Long id) {
        LeaseAgreement leaseAgreement = leaseAgreementClient.getById(id).getData();
        if (leaseAgreement == null) {
            return null;
        }
        return assemble(leaseAgreement);
    }

    @Override
    public IPage<AgreementVo> pageAgreement(Page<AgreementVo> page, AgreementQueryVo queryVo) {
        Result<IPage<LeaseAgreement>> result = leaseAgreementClient.page(page.getCurrent(), page.getSize(), queryVo);
        IPage<LeaseAgreement> data = result.getData();
        Page<AgreementVo> ret = new Page<>(data.getCurrent(), data.getSize(), data.getTotal());
        List<AgreementVo> records = new ArrayList<>();
        for (LeaseAgreement leaseAgreement : data.getRecords()) {
            records.add(assemble(leaseAgreement));
        }
        ret.setRecords(records);
        return ret;
    }

    private AgreementVo assemble(LeaseAgreement leaseAgreement) {
        AgreementVo agreementVo = new AgreementVo();
        BeanUtils.copyProperties(leaseAgreement, agreementVo);

        ApartmentInfo apartmentInfo = apartmentClient.getDetailById(leaseAgreement.getApartmentId()).getData();
        agreementVo.setApartmentInfo(apartmentInfo);

        RoomInfo roomInfo = roomClient.getDetailById(leaseAgreement.getRoomId()).getData();
        agreementVo.setRoomInfo(roomInfo);

        List<PaymentType> paymentTypeList = paymentTypeClient.listByRoomId(leaseAgreement.getRoomId()).getData();
        if (paymentTypeList != null) {
            for (PaymentType pt : paymentTypeList) {
                if (pt.getId().equals(leaseAgreement.getPaymentTypeId())) {
                    agreementVo.setPaymentType(pt);
                    break;
                }
            }
        }

        List<LeaseTerm> leaseTermList = leaseTermClient.listByRoomId(leaseAgreement.getRoomId()).getData();
        if (leaseTermList != null) {
            for (LeaseTerm lt : leaseTermList) {
                if (lt.getId().equals(leaseAgreement.getLeaseTermId())) {
                    agreementVo.setLeaseTerm(lt);
                    break;
                }
            }
        }
        return agreementVo;
    }

    @Override
    public boolean saveOrUpdate(LeaseAgreement leaseAgreement) {
        leaseAgreementClient.saveOrUpdate(leaseAgreement);
        return true;
    }

    @Override
    public boolean removeById(Long id) {
        leaseAgreementClient.removeById(id);
        return true;
    }

    @Override
    public boolean update(Wrapper<LeaseAgreement> updateWrapper) {
        Long id = null;
        LeaseStatus status = null;
        for (Object v : updateWrapper.getParamNameValuePairs().values()) {
            if (v instanceof Long) {
                id = (Long) v;
            } else if (v instanceof LeaseStatus) {
                status = (LeaseStatus) v;
            }
        }
        leaseAgreementClient.updateStatusById(id, status);
        return true;
    }

    @Override
    public BaseMapper<LeaseAgreement> getBaseMapper() {
        return null;
    }

    @Override
    public Class<LeaseAgreement> getEntityClass() {
        return LeaseAgreement.class;
    }

    @Override
    public boolean saveBatch(java.util.Collection<LeaseAgreement> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean saveOrUpdateBatch(java.util.Collection<LeaseAgreement> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean updateBatchById(java.util.Collection<LeaseAgreement> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public LeaseAgreement getOne(Wrapper<LeaseAgreement> queryWrapper, boolean throwEx) {
        throw new UnsupportedOperationException();
    }

    @Override
    public java.util.Map<String, Object> getMap(Wrapper<LeaseAgreement> queryWrapper) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <V> V getObj(Wrapper<LeaseAgreement> queryWrapper, java.util.function.Function<? super Object, V> mapper) {
        throw new UnsupportedOperationException();
    }
}
