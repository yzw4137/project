package com.atguigu.lease.web.app.service.impl;

import com.atguigu.lease.api.apartment.ApartmentClient;
import com.atguigu.lease.model.vo.apartment.AppApartmentDetailVo;
import com.atguigu.lease.model.vo.apartment.AppApartmentItemVo;
import com.atguigu.lease.web.app.service.ApartmentInfoService;
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
}
