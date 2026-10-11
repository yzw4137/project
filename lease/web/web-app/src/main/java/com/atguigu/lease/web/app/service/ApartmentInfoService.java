package com.atguigu.lease.web.app.service;

import com.atguigu.lease.model.vo.apartment.AppApartmentItemVo;

/**
 * @author liubo
 * @description 针对表【apartment_info(公寓信息表)】的数据库操作Service
 * @createDate 2023-07-26 11:12:39
 */
public interface ApartmentInfoService {

    AppApartmentItemVo selectApartmentItemVoById(Long apartmentId);
}
