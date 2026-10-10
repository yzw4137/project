package com.atguigu.lease.web.app.service.impl;

import com.atguigu.lease.api.apartment.ApartmentClient;
import com.atguigu.lease.api.apartment.LeaseTermClient;
import com.atguigu.lease.api.apartment.PaymentTypeClient;
import com.atguigu.lease.api.apartment.RoomClient;
import com.atguigu.lease.api.lease.LeaseAgreementClient;
import com.atguigu.lease.common.login.LoginUserHolder;
import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.entity.LeaseAgreement;
import com.atguigu.lease.model.entity.LeaseTerm;
import com.atguigu.lease.model.entity.PaymentType;
import com.atguigu.lease.model.enums.LeaseStatus;
import com.atguigu.lease.model.vo.apartment.AppApartmentDetailVo;
import com.atguigu.lease.model.vo.room.AppRoomDetailVo;
import com.atguigu.lease.web.app.service.LeaseAgreementService;
import com.atguigu.lease.web.app.vo.agreement.AgreementDetailVo;
import com.atguigu.lease.web.app.vo.agreement.AgreementItemVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
    public List<AgreementItemVo> listItemByPhone() {
        String phone = LoginUserHolder.getLoginUser().getUsername();
        Result<List<LeaseAgreement>> result = leaseAgreementClient.listByPhone(phone);
        List<LeaseAgreement> list = result.getData();
        if (list == null) {
            return new ArrayList<>();
        }
        List<AgreementItemVo> ret = new ArrayList<>();
        for (LeaseAgreement leaseAgreement : list) {
            AgreementItemVo item = new AgreementItemVo();
            item.setId(leaseAgreement.getId());
            item.setLeaseStatus(leaseAgreement.getStatus());
            item.setLeaseStartDate(leaseAgreement.getLeaseStartDate());
            item.setLeaseEndDate(leaseAgreement.getLeaseEndDate());
            item.setSourceType(leaseAgreement.getSourceType());
            item.setRent(leaseAgreement.getRent());

            AppRoomDetailVo room = roomClient.getAppDetailById(leaseAgreement.getRoomId()).getData();
            if (room != null) {
                item.setRoomNumber(room.getRoomNumber());
                if (room.getApartmentItemVo() != null) {
                    item.setApartmentName(room.getApartmentItemVo().getName());
                }
                if (room.getGraphVoList() != null) {
                    item.setRoomGraphVoList(room.getGraphVoList());
                }
            }
            ret.add(item);
        }
        return ret;
    }

    @Override
    public AgreementDetailVo getDetailById(Long id) {
        LeaseAgreement leaseAgreement = leaseAgreementClient.getById(id).getData();
        if (leaseAgreement == null) {
            return null;
        }
        AgreementDetailVo vo = new AgreementDetailVo();
        BeanUtils.copyProperties(leaseAgreement, vo);

        AppApartmentDetailVo apartment = apartmentClient.getAppDetailById(leaseAgreement.getApartmentId()).getData();
        if (apartment != null) {
            vo.setApartmentName(apartment.getName());
            if (apartment.getGraphVoList() != null) {
                vo.setApartmentGraphVoList(apartment.getGraphVoList());
            }
        }

        AppRoomDetailVo room = roomClient.getAppDetailById(leaseAgreement.getRoomId()).getData();
        if (room != null) {
            vo.setRoomNumber(room.getRoomNumber());
            if (room.getGraphVoList() != null) {
                vo.setRoomGraphVoList(room.getGraphVoList());
            }
        }

        List<PaymentType> paymentTypeList = paymentTypeClient.listByRoomId(leaseAgreement.getRoomId()).getData();
        if (paymentTypeList != null) {
            for (PaymentType pt : paymentTypeList) {
                if (pt.getId().equals(leaseAgreement.getPaymentTypeId())) {
                    vo.setPaymentTypeName(pt.getName());
                    break;
                }
            }
        }

        List<LeaseTerm> leaseTermList = leaseTermClient.listByRoomId(leaseAgreement.getRoomId()).getData();
        if (leaseTermList != null) {
            for (LeaseTerm lt : leaseTermList) {
                if (lt.getId().equals(leaseAgreement.getLeaseTermId())) {
                    vo.setLeaseTermMonthCount(lt.getMonthCount());
                    vo.setLeaseTermUnit(lt.getUnit());
                    break;
                }
            }
        }
        return vo;
    }

    @Override
    public boolean saveOrUpdate(LeaseAgreement leaseAgreement) {
        leaseAgreementClient.saveOrUpdate(leaseAgreement);
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
