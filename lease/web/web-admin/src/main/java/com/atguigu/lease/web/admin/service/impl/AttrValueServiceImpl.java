package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.api.apartment.AttrClient;
import com.atguigu.lease.model.entity.AttrValue;
import com.atguigu.lease.web.admin.service.AttrValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

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
    public boolean removeById(Serializable id) {
        attrClient.removeAttrValueById((Long) id);
        return true;
    }
}
