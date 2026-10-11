package com.atguigu.lease.web.admin.service;

import com.atguigu.lease.model.entity.FeeValue;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.io.Serializable;

/**
* @author liubo
* @description 针对表【fee_value(杂项费用值表)】的数据库操作Service
* @createDate 2023-07-24 15:48:00
*/
public interface FeeValueService {

    boolean saveOrUpdate(FeeValue feeValue);

    boolean removeById(Serializable id);

    boolean remove(LambdaQueryWrapper<FeeValue> wrapper);
}
