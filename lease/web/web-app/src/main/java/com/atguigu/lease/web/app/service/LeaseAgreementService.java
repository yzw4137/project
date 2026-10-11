package com.atguigu.lease.web.app.service;

import com.atguigu.lease.model.entity.LeaseAgreement;
import com.atguigu.lease.model.enums.LeaseStatus;
import com.atguigu.lease.web.app.vo.agreement.AgreementDetailVo;
import com.atguigu.lease.web.app.vo.agreement.AgreementItemVo;

import java.util.List;

/**
* @author liubo
* @description 针对表【lease_agreement(租约信息表)】的数据库操作Service
* @createDate 2023-07-26 11:12:39
*/
public interface LeaseAgreementService {

    List<AgreementItemVo> listItemByPhone();

    AgreementDetailVo getDetailById(Long id);

    boolean saveOrUpdate(LeaseAgreement leaseAgreement);

    boolean updateStatusById(Long id, LeaseStatus status);
}
