package com.atguigu.lease.service.lease.service;

import com.atguigu.lease.model.entity.LeaseAgreement;
import com.atguigu.lease.model.enums.LeaseStatus;
import com.atguigu.lease.model.vo.lease.AgreementDetailVo;
import com.atguigu.lease.model.vo.lease.AgreementItemVo;
import com.atguigu.lease.model.vo.lease.AgreementQueryVo;
import com.atguigu.lease.model.vo.lease.AgreementVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface LeaseAgreementService extends IService<LeaseAgreement> {

    /** 管理端：分页查询租约列表（富化公寓/房间/支付方式/租期信息） */
    IPage<AgreementVo> pageAgreement(Page<LeaseAgreement> page, AgreementQueryVo queryVo);

    /** 管理端：根据id查询租约信息（富化公寓/房间/支付方式/租期信息） */
    AgreementVo getAgreementVoById(Long id);

    void updateStatusById(Long id, LeaseStatus status);

    List<LeaseAgreement> listByPhone(String phone);

    void expireLeaseAgreement();

    /** App 端：当前用户租约列表（富化公寓/房间信息） */
    List<AgreementItemVo> listItemByPhone(String phone);

    /** App 端：租约详情（富化公寓/房间/支付方式/租期信息） */
    AgreementDetailVo getDetailById(Long id);
}
