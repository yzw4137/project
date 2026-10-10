package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.api.apartment.AttrClient;
import com.atguigu.lease.model.entity.AttrKey;
import com.atguigu.lease.web.admin.service.AttrKeyService;
import com.atguigu.lease.model.vo.attr.AttrKeyVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttrKeyServiceImpl implements AttrKeyService {

    @Autowired
    private AttrClient attrClient;

    @Override
    public List<AttrKeyVo> listAttrInfo() {
        return attrClient.listAttrInfo().getData().stream().map(src -> {
            AttrKeyVo dst = new AttrKeyVo();
            BeanUtils.copyProperties(src, dst);
            return dst;
        }).collect(Collectors.toList());
    }

    @Override
    public boolean saveOrUpdate(AttrKey attrKey) {
        attrClient.saveOrUpdateAttrKey(attrKey);
        return true;
    }

    @Override
    public boolean removeById(java.io.Serializable id) {
        attrClient.removeAttrKeyById((Long) id);
        return true;
    }

    @Override
    public BaseMapper<AttrKey> getBaseMapper() {
        return null;
    }

    @Override
    public Class<AttrKey> getEntityClass() {
        return AttrKey.class;
    }

    @Override
    public boolean saveBatch(java.util.Collection<AttrKey> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean saveOrUpdateBatch(java.util.Collection<AttrKey> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean updateBatchById(java.util.Collection<AttrKey> entityList, int batchSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public AttrKey getOne(Wrapper<AttrKey> queryWrapper, boolean throwEx) {
        throw new UnsupportedOperationException();
    }

    @Override
    public java.util.Map<String, Object> getMap(Wrapper<AttrKey> queryWrapper) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <V> V getObj(Wrapper<AttrKey> queryWrapper, java.util.function.Function<? super Object, V> mapper) {
        throw new UnsupportedOperationException();
    }
}
