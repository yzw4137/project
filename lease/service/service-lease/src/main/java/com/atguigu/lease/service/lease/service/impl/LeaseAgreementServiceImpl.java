package com.atguigu.lease.service.lease.service.impl;

import com.atguigu.lease.model.entity.LeaseAgreement;
import com.atguigu.lease.model.enums.LeaseStatus;
import com.atguigu.lease.model.vo.lease.AgreementQueryVo;
import com.atguigu.lease.service.lease.mapper.LeaseAgreementMapper;
import com.atguigu.lease.service.lease.service.LeaseAgreementService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaseAgreementServiceImpl extends ServiceImpl<LeaseAgreementMapper, LeaseAgreement>
        implements LeaseAgreementService {

    @Override
    public IPage<LeaseAgreement> pageAgreement(Page<LeaseAgreement> page, AgreementQueryVo queryVo) {
        LambdaQueryWrapper<LeaseAgreement> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(queryVo.getApartmentId() != null, LeaseAgreement::getApartmentId, queryVo.getApartmentId());
        wrapper.like(queryVo.getName() != null && !queryVo.getName().isEmpty(), LeaseAgreement::getName, queryVo.getName());
        wrapper.like(queryVo.getPhone() != null && !queryVo.getPhone().isEmpty(), LeaseAgreement::getPhone, queryVo.getPhone());
        return this.page(page, wrapper);
    }

    @Override
    public void updateStatusById(Long id, LeaseStatus status) {
        LambdaUpdateWrapper<LeaseAgreement> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(LeaseAgreement::getId, id);
        wrapper.set(LeaseAgreement::getStatus, status);
        this.update(wrapper);
    }

    @Override
    public List<LeaseAgreement> listByPhone(String phone) {
        LambdaQueryWrapper<LeaseAgreement> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(LeaseAgreement::getPhone, phone);
        return this.list(wrapper);
    }
}
