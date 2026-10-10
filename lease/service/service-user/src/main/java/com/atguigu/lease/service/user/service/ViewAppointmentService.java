package com.atguigu.lease.service.user.service;

import com.atguigu.lease.model.entity.ViewAppointment;
import com.atguigu.lease.model.enums.AppointmentStatus;
import com.atguigu.lease.model.vo.appointment.AppointmentItemVo;
import com.atguigu.lease.model.vo.appointment.AppointmentQueryVo;
import com.atguigu.lease.model.vo.appointment.AppointmentVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ViewAppointmentService extends IService<ViewAppointment> {

    IPage<AppointmentVo> pageAppointment(Page<AppointmentVo> page, AppointmentQueryVo queryVo);

    void updateStatusById(Long id, AppointmentStatus status);

    List<AppointmentItemVo> listItemByUserId(Long userId);
}
