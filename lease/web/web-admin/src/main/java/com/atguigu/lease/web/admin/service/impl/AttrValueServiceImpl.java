package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.api.apartment.AttrClient;
import com.atguigu.lease.model.entity.AttrValue;
import com.atguigu.lease.web.admin.service.AttrValueService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttrValueServiceImpl implements AttrValueService {

    @Autowired
    private AttrClient attrClient;

    @Override
    public boolean saveOrUpdate(AttrValue attrValue) {
        attrClient.saveOrUpdateAttrValue(attrValue);
        return true;
    }

    @Override
    public boolean removeById(java.io.Serializable id) {
        attrClient.removeAttrValueById((Long) id);
        return true;
    }

    @Override
    public boolean remove(Wrapper<AttrValue> wrapper) {
        Long attrKeyId = (Long) wrapper.getParamNameValuePairs().values().iterator().next();
        attrClient.removeAttrKeyById(attrKeyId);
        return true;
    }

    @Override
    public BaseMapper<AttrValue> getBaseMapper() {
        return null;
    }

    @Override
    public Class<AttrValue> getEntityClass() {
        return AttrValue.class;
    }

    @Override
    public boolean saveBatch(java.util.Collection<AttrValue> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean saveOrUpdateBatch(java.util.Collection<AttrValue> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean updateBatchById(java.util.Collection<AttrValue> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public AttrValue getOne(Wrapper<AttrValue> queryWrapper, boolean throwEx) {
        throw new UnsupportedOperationException();
    }

    @Override
    public java.util.Map<String, Object> getMap(Wrapper<AttrValue> queryWrapper) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <V> V getObj(Wrapper<AttrValue> queryWrapper, java.util.function.Function<? super Object, V> mapper) {
        throw new UnsupportedOperationException();
    }
}
