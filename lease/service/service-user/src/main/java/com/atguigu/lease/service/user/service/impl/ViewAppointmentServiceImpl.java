package com.atguigu.lease.service.user.service.impl;

import com.atguigu.lease.model.entity.ViewAppointment;
import com.atguigu.lease.model.enums.AppointmentStatus;
import com.atguigu.lease.model.vo.appointment.AppointmentItemVo;
import com.atguigu.lease.model.vo.appointment.AppointmentQueryVo;
import com.atguigu.lease.model.vo.appointment.AppointmentVo;
import com.atguigu.lease.service.user.mapper.ViewAppointmentMapper;
import com.atguigu.lease.service.user.service.ViewAppointmentService;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewAppointmentServiceImpl extends ServiceImpl<ViewAppointmentMapper, ViewAppointment>
        implements ViewAppointmentService {

    @Autowired
    private ViewAppointmentMapper viewAppointmentMapper;

    @Override
    public IPage<AppointmentVo> pageAppointment(Page<AppointmentVo> page, AppointmentQueryVo queryVo) {
        return viewAppointmentMapper.pageAppointment(page, queryVo);
    }

    @Override
    public void updateStatusById(Long id, AppointmentStatus status) {
        LambdaUpdateWrapper<ViewAppointment> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(ViewAppointment::getId, id);
        wrapper.set(ViewAppointment::getAppointmentStatus, status);
        this.update(wrapper);
    }

    @Override
    public List<AppointmentItemVo> listItemByUserId(Long userId) {
        return viewAppointmentMapper.listItemByUserId(userId);
    }
}
