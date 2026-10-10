package com.atguigu.lease.service.apartment.service;

import com.atguigu.lease.model.entity.AttrKey;
import com.atguigu.lease.model.vo.attr.AttrKeyVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface AttrKeyService extends IService<AttrKey> {

    List<AttrKeyVo> listAttrInfo();
}
