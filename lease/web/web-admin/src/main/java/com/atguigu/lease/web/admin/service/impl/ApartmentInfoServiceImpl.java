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
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
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
    public boolean update(LambdaUpdateWrapper<ApartmentInfo> updateWrapper) {
        Long id = (Long) updateWrapper.getParamNameValuePairs().values().stream()
                .filter(v -> v instanceof Long).findFirst().orElse(null);
        Object status = updateWrapper.getParamNameValuePairs().values().stream()
                .filter(v -> v instanceof ReleaseStatus).findFirst().orElse(null);
        apartmentClient.updateReleaseStatusById(id, (ReleaseStatus) status);
        return true;
    }

    @Override
    public List<ApartmentInfo> list(LambdaQueryWrapper<ApartmentInfo> queryWrapper) {
        Long districtId = (Long) queryWrapper.getParamNameValuePairs().values().iterator().next();
        return apartmentClient.listInfoByDistrictId(districtId).getData();
    }
}
