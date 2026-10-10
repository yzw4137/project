package com.atguigu.lease.service.user.mapper;

import com.atguigu.lease.model.entity.ViewAppointment;
import com.atguigu.lease.model.vo.appointment.AppointmentItemVo;
import com.atguigu.lease.model.vo.appointment.AppointmentQueryVo;
import com.atguigu.lease.model.vo.appointment.AppointmentVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

public interface ViewAppointmentMapper extends BaseMapper<ViewAppointment> {

    IPage<AppointmentVo> pageAppointment(Page<AppointmentVo> page, AppointmentQueryVo queryVo);

    List<AppointmentItemVo> listItemByUserId(Long userId);
}
