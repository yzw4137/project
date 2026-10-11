package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.api.apartment.AttrClient;
import com.atguigu.lease.model.entity.AttrKey;
import com.atguigu.lease.web.admin.service.AttrKeyService;
import com.atguigu.lease.model.vo.attr.AttrKeyVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
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
    public boolean removeById(Serializable id) {
        attrClient.removeAttrKeyById((Long) id);
        return true;
    }
}
