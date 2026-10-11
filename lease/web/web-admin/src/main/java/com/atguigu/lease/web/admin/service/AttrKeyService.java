package com.atguigu.lease.web.admin.service;

import com.atguigu.lease.model.entity.AttrKey;
import com.atguigu.lease.model.vo.attr.AttrKeyVo;

import java.io.Serializable;
import java.util.List;

/**
* @author liubo
* @description 针对表【attr_key(房间基本属性表)】的数据库操作Service
* @createDate 2023-07-24 15:48:00
*/
public interface AttrKeyService {

    List<AttrKeyVo> listAttrInfo();

    boolean saveOrUpdate(AttrKey attrKey);

    boolean removeById(Serializable id);
}
