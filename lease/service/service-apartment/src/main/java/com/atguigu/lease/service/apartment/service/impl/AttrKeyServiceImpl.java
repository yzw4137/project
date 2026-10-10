package com.atguigu.lease.service.apartment.service.impl;

import com.atguigu.lease.model.entity.AttrKey;
import com.atguigu.lease.model.vo.attr.AttrKeyVo;
import com.atguigu.lease.service.apartment.mapper.AttrKeyMapper;
import com.atguigu.lease.service.apartment.service.AttrKeyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttrKeyServiceImpl extends ServiceImpl<AttrKeyMapper, AttrKey>
    implements AttrKeyService{

    @Autowired
    private AttrKeyMapper attrKeyMapper;

    @Override
    public List<AttrKeyVo> listAttrInfo() {
        return attrKeyMapper.listAttrInfo();
    }
}
