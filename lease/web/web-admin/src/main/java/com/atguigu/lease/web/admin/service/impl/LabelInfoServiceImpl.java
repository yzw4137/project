package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.api.apartment.LabelClient;
import com.atguigu.lease.model.entity.LabelInfo;
import com.atguigu.lease.model.enums.ItemType;
import com.atguigu.lease.web.admin.service.LabelInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class LabelInfoServiceImpl implements LabelInfoService {

    @Autowired
    private LabelClient labelClient;

    @Override
    public List<LabelInfo> listByType(ItemType type) {
        return labelClient.labelList(type).getData();
    }

    @Override
    public boolean saveOrUpdate(LabelInfo labelInfo) {
        labelClient.saveOrUpdateLabel(labelInfo);
        return true;
    }

    @Override
    public boolean removeById(Serializable id) {
        labelClient.deleteLabelById((Long) id);
        return true;
    }
}
