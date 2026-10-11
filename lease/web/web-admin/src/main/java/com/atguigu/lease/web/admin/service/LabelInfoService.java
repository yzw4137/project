package com.atguigu.lease.web.admin.service;

import com.atguigu.lease.model.entity.LabelInfo;
import com.atguigu.lease.model.enums.ItemType;

import java.io.Serializable;
import java.util.List;

/**
* @author liubo
* @description 针对表【label_info(标签信息表)】的数据库操作Service
* @createDate 2023-07-24 15:48:00
*/
public interface LabelInfoService {

    List<LabelInfo> listByType(ItemType type);

    boolean saveOrUpdate(LabelInfo labelInfo);

    boolean removeById(Serializable id);
}
