package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.api.user.ViewAppointmentClient;
import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.entity.ViewAppointment;
import com.atguigu.lease.model.enums.AppointmentStatus;
import com.atguigu.lease.web.admin.service.ViewAppointmentService;
import com.atguigu.lease.model.vo.appointment.AppointmentQueryVo;
import com.atguigu.lease.model.vo.appointment.AppointmentVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
        Result<IPage<AppointmentVo>> result =
                viewAppointmentClient.page(page.getCurrent(), page.getSize(), queryVo);
        return result.getData();
    }

    @Override
    public boolean update(Wrapper<ViewAppointment> updateWrapper) {
        Long id = null;
        AppointmentStatus status = null;
        for (Object v : updateWrapper.getParamNameValuePairs().values()) {
            if (v instanceof Long) {
                id = (Long) v;
            } else if (v instanceof AppointmentStatus) {
                status = (AppointmentStatus) v;
            }
        }
        viewAppointmentClient.updateStatusById(id, status);
        return true;
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
    public boolean saveOrUpdate(ViewAppointment entity) {
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
