package com.atguigu.lease.web.admin.service;

import com.atguigu.lease.model.entity.AttrValue;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.io.Serializable;

/**
* @author liubo
* @description 针对表【attr_value(房间基本属性值表)】的数据库操作Service
* @createDate 2023-07-24 15:48:00
*/
public interface AttrValueService {

    boolean saveOrUpdate(AttrValue attrValue);

    boolean removeById(Serializable id);

    boolean remove(LambdaQueryWrapper<AttrValue> wrapper);
}
