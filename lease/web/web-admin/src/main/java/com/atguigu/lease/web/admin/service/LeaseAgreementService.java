package com.atguigu.lease.web.admin.service;

import com.atguigu.lease.model.entity.LeaseAgreement;
import com.atguigu.lease.model.vo.lease.AgreementQueryVo;
import com.atguigu.lease.web.admin.vo.agreement.AgreementVo;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
* @author liubo
* @description 针对表【lease_agreement(租约信息表)】的数据库操作Service
* @createDate 2023-07-24 15:48:00
*/
public interface LeaseAgreementService {

    AgreementVo getAgreementById(Long id);

    IPage<AgreementVo> pageAgreement(Page<AgreementVo> page, AgreementQueryVo queryVo);

    boolean saveOrUpdate(LeaseAgreement leaseAgreement);

    boolean removeById(Long id);

    boolean update(LambdaUpdateWrapper<LeaseAgreement> updateWrapper);
}
