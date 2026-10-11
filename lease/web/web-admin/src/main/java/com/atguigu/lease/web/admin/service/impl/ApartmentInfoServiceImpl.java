package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.api.apartment.ApartmentClient;
import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.entity.ApartmentInfo;
import com.atguigu.lease.model.enums.ReleaseStatus;
import com.atguigu.lease.model.vo.apartment.ApartmentDetailVo;
import com.atguigu.lease.model.vo.apartment.ApartmentItemVo;
import com.atguigu.lease.model.vo.apartment.ApartmentQueryVo;
import com.atguigu.lease.model.vo.apartment.ApartmentSubmitVo;
import com.atguigu.lease.web.admin.service.ApartmentInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApartmentInfoServiceImpl implements ApartmentInfoService {

    @Autowired
    private ApartmentClient apartmentClient;

    @Override
    public void saveOrUpdateApatment(ApartmentSubmitVo apartmentSubmitVo) {
        apartmentClient.saveOrUpdate(apartmentSubmitVo);
    }

    @Override
    public IPage<ApartmentItemVo> pageItem(Page<ApartmentItemVo> page, ApartmentQueryVo queryVo) {
        Result<Page<ApartmentItemVo>> result =
                apartmentClient.pageItem(page.getCurrent(), page.getSize(), queryVo);
        return result.getData();
    }

    @Override
    public ApartmentDetailVo getDetailById(Long id) {
        return apartmentClient.getDetailById(id).getData();
    }

    @Override
    public void removeApartmentById(Long id) {
        apartmentClient.removeById(id);
    }

    @Override
    public boolean updateReleaseStatusById(Long id, ReleaseStatus status) {
        apartmentClient.updateReleaseStatusById(id, status);
        return true;
    }

    @Override
    public List<ApartmentInfo> listByDistrictId(Long districtId) {
        return apartmentClient.listInfoByDistrictId(districtId).getData();
    }
}
