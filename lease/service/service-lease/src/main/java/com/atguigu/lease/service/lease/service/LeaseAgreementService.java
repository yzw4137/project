package com.atguigu.lease.service.lease.service;

import com.atguigu.lease.model.entity.LeaseAgreement;
import com.atguigu.lease.model.enums.LeaseStatus;
import com.atguigu.lease.model.vo.lease.AgreementQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface LeaseAgreementService extends IService<LeaseAgreement> {

    IPage<LeaseAgreement> pageAgreement(Page<LeaseAgreement> page, AgreementQueryVo queryVo);

    void updateStatusById(Long id, LeaseStatus status);

    List<LeaseAgreement> listByPhone(String phone);
}
