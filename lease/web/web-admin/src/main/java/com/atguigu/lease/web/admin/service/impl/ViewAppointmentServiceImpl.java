package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.api.user.ViewAppointmentClient;
import com.atguigu.lease.model.enums.AppointmentStatus;
import com.atguigu.lease.web.admin.service.ViewAppointmentService;
import com.atguigu.lease.model.vo.appointment.AppointmentQueryVo;
import com.atguigu.lease.model.vo.appointment.AppointmentVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViewAppointmentServiceImpl implements ViewAppointmentService {

    @Autowired
    private ViewAppointmentClient viewAppointmentClient;

    @Override
    public IPage<AppointmentVo> pageAppointment(Page<AppointmentVo> page, AppointmentQueryVo queryVo) {
        return viewAppointmentClient.page(page.getCurrent(), page.getSize(), queryVo).getData();
    }

    @Override
    public boolean updateStatusById(Long id, AppointmentStatus status) {
        viewAppointmentClient.updateStatusById(id, status);
        return true;
    }
}
