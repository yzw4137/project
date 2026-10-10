package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.api.apartment.LabelClient;
import com.atguigu.lease.model.entity.LabelInfo;
import com.atguigu.lease.model.enums.ItemType;
import com.atguigu.lease.web.admin.service.LabelInfoService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabelInfoServiceImpl implements LabelInfoService {

    @Autowired
    private LabelClient labelClient;

    @Override
    public List<LabelInfo> list(Wrapper<LabelInfo> queryWrapper) {
        ItemType type = (ItemType) queryWrapper.getParamNameValuePairs().values().stream()
                .filter(v -> v instanceof ItemType).findFirst().orElse(null);
        return labelClient.labelList(type).getData();
    }

    @Override
    public boolean saveOrUpdate(LabelInfo labelInfo) {
        labelClient.saveOrUpdateLabel(labelInfo);
        return true;
    }

    @Override
    public boolean removeById(java.io.Serializable id) {
        labelClient.deleteLabelById((Long) id);
        return true;
    }

    @Override
    public BaseMapper<LabelInfo> getBaseMapper() {
        return null;
    }

    @Override
    public Class<LabelInfo> getEntityClass() {
        return LabelInfo.class;
    }

    @Override
    public boolean saveBatch(java.util.Collection<LabelInfo> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean saveOrUpdateBatch(java.util.Collection<LabelInfo> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean updateBatchById(java.util.Collection<LabelInfo> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public LabelInfo getOne(Wrapper<LabelInfo> queryWrapper, boolean throwEx) {
        throw new UnsupportedOperationException();
    }

    @Override
    public java.util.Map<String, Object> getMap(Wrapper<LabelInfo> queryWrapper) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <V> V getObj(Wrapper<LabelInfo> queryWrapper, java.util.function.Function<? super Object, V> mapper) {
        throw new UnsupportedOperationException();
    }
}
