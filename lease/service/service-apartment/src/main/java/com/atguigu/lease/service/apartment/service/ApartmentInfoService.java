package com.atguigu.lease.service.apartment.service;

import com.atguigu.lease.model.entity.ApartmentInfo;
import com.atguigu.lease.model.vo.apartment.ApartmentDetailVo;
import com.atguigu.lease.model.vo.apartment.ApartmentItemVo;
import com.atguigu.lease.model.vo.apartment.ApartmentQueryVo;
import com.atguigu.lease.model.vo.apartment.ApartmentSubmitVo;
import com.atguigu.lease.model.vo.apartment.AppApartmentItemVo;
import com.atguigu.lease.model.vo.apartment.AppApartmentDetailVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ApartmentInfoService extends IService<ApartmentInfo> {

    void saveOrUpdateApatment(ApartmentSubmitVo apartmentSubmitVo);

    IPage<ApartmentItemVo> pageItem(Page<ApartmentItemVo> page, ApartmentQueryVo queryVo);

    ApartmentDetailVo getDetailById(Long id);

    void removeApartmentById(Long id);

    AppApartmentItemVo selectApartmentItemVoById(Long apartmentId);

    AppApartmentDetailVo getAppDetailById(Long id);
}
