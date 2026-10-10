package com.atguigu.lease.web.app.service.impl;

import com.atguigu.lease.api.apartment.ApartmentClient;
import com.atguigu.lease.api.user.ViewAppointmentClient;
import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.entity.ViewAppointment;
import com.atguigu.lease.web.app.service.ViewAppointmentService;
import com.atguigu.lease.model.vo.apartment.AppApartmentItemVo;
import com.atguigu.lease.model.vo.appointment.AppointmentDetailVo;
import com.atguigu.lease.model.vo.appointment.AppointmentItemVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewAppointmentServiceImpl implements ViewAppointmentService {

    @Autowired
    private ViewAppointmentClient viewAppointmentClient;

    @Autowired
    private ApartmentClient apartmentClient;

    @Override
    public boolean saveOrUpdate(ViewAppointment viewAppointment) {
        viewAppointmentClient.saveOrUpdate(viewAppointment);
        return true;
    }

    @Override
    public List<AppointmentItemVo> listItemByUserId(Long userId) {
        Result<List<AppointmentItemVo>> result =
                viewAppointmentClient.listItemByUserId(userId);
        return result.getData();
    }

    @Override
    public AppointmentDetailVo getDetailById(Long id) {
        Result<ViewAppointment> result = viewAppointmentClient.getById(id);
        ViewAppointment viewAppointment = result.getData();
        if (viewAppointment == null) {
            return null;
        }
        com.atguigu.lease.model.vo.apartment.AppApartmentDetailVo aptSrc =
                apartmentClient.getAppDetailById(viewAppointment.getApartmentId()).getData();

        AppointmentDetailVo appointmentDetailVo = new AppointmentDetailVo();
        BeanUtils.copyProperties(viewAppointment, appointmentDetailVo);

        if (aptSrc != null) {
            AppApartmentItemVo apartmentItemVo = new AppApartmentItemVo();
            BeanUtils.copyProperties(aptSrc, apartmentItemVo);
            apartmentItemVo.setMinRent(aptSrc.getMinRent());
            appointmentDetailVo.setApartmentItemVo(apartmentItemVo);
        }

        return appointmentDetailVo;
    }

    @Override
    public BaseMapper<ViewAppointment> getBaseMapper() {
        return null;
    }

    @Override
    public Class<ViewAppointment> getEntityClass() {
        return ViewAppointment.class;
    }

    @Override
    public boolean saveBatch(java.util.Collection<ViewAppointment> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean saveOrUpdateBatch(java.util.Collection<ViewAppointment> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean updateBatchById(java.util.Collection<ViewAppointment> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ViewAppointment getOne(Wrapper<ViewAppointment> queryWrapper, boolean throwEx) {
        throw new UnsupportedOperationException();
    }

    @Override
    public java.util.Map<String, Object> getMap(Wrapper<ViewAppointment> queryWrapper) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <V> V getObj(Wrapper<ViewAppointment> queryWrapper, java.util.function.Function<? super Object, V> mapper) {
        throw new UnsupportedOperationException();
    }
}
