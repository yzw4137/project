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
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
        Result<IPage<ApartmentItemVo>> result =
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
    public boolean update(Wrapper<ApartmentInfo> updateWrapper) {
        Long id = (Long) updateWrapper.getParamNameValuePairs().values().stream()
                .filter(v -> v instanceof Long).findFirst().orElse(null);
        Object status = updateWrapper.getParamNameValuePairs().values().stream()
                .filter(v -> v instanceof ReleaseStatus).findFirst().orElse(null);
        apartmentClient.updateReleaseStatusById(id, (ReleaseStatus) status);
        return true;
    }

    @Override
    public List<ApartmentInfo> list(Wrapper<ApartmentInfo> queryWrapper) {
        Long districtId = (Long) queryWrapper.getParamNameValuePairs().values().iterator().next();
        return apartmentClient.listInfoByDistrictId(districtId).getData();
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
    public ApartmentInfo getOne(com.baomidou.mybatisplus.core.conditions.Wrapper<ApartmentInfo> queryWrapper, boolean throwEx) {
        throw new UnsupportedOperationException();
    }

    @Override
    public java.util.Map<String, Object> getMap(com.baomidou.mybatisplus.core.conditions.Wrapper<ApartmentInfo> queryWrapper) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <V> V getObj(com.baomidou.mybatisplus.core.conditions.Wrapper<ApartmentInfo> queryWrapper, java.util.function.Function<? super Object, V> mapper) {
        throw new UnsupportedOperationException();
    }
}
