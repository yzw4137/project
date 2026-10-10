package com.atguigu.lease.service.apartment.service;

import com.atguigu.lease.model.entity.FeeKey;
import com.atguigu.lease.model.vo.fee.FeeKeyVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface FeeKeyService extends IService<FeeKey> {

    List<FeeKeyVo> listFeeInfo();
}
