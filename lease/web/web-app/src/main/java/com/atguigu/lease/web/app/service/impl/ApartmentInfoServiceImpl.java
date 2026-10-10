package com.atguigu.lease.web.app.service.impl;

import com.atguigu.lease.api.apartment.ApartmentClient;
import com.atguigu.lease.model.entity.ApartmentInfo;
import com.atguigu.lease.model.vo.apartment.AppApartmentDetailVo;
import com.atguigu.lease.model.vo.apartment.AppApartmentItemVo;
import com.atguigu.lease.web.app.service.ApartmentInfoService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApartmentInfoServiceImpl implements ApartmentInfoService {

    @Autowired
    private ApartmentClient apartmentClient;

    @Override
    public AppApartmentItemVo selectApartmentItemVoById(Long apartmentId) {
        AppApartmentDetailVo src = apartmentClient.getAppDetailById(apartmentId).getData();
        if (src == null) {
            return null;
        }
        AppApartmentItemVo dst = new AppApartmentItemVo();
        BeanUtils.copyProperties(src, dst);
        dst.setMinRent(src.getMinRent());
        return dst;
    }

    public AppApartmentDetailVo getDetailById(Long id) {
        return apartmentClient.getAppDetailById(id).getData();
    }

    @Override
    public BaseMapper<ApartmentInfo> getBaseMapper() {
        return null;
    }

    @Override
    public Class<ApartmentInfo> getEntityClass() {
        return ApartmentInfo.class;
    }

    @Override
    public boolean saveBatch(java.util.Collection<ApartmentInfo> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean saveOrUpdateBatch(java.util.Collection<ApartmentInfo> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean updateBatchById(java.util.Collection<ApartmentInfo> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean saveOrUpdate(ApartmentInfo entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ApartmentInfo getOne(Wrapper<ApartmentInfo> queryWrapper, boolean throwEx) {
        throw new UnsupportedOperationException();
    }

    @Override
    public java.util.Map<String, Object> getMap(Wrapper<ApartmentInfo> queryWrapper) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <V> V getObj(Wrapper<ApartmentInfo> queryWrapper, java.util.function.Function<? super Object, V> mapper) {
        throw new UnsupportedOperationException();
    }
}
