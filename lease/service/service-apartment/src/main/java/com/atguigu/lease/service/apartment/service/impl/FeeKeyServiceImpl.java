package com.atguigu.lease.service.apartment.service.impl;

import com.atguigu.lease.model.entity.FeeKey;
import com.atguigu.lease.model.vo.fee.FeeKeyVo;
import com.atguigu.lease.service.apartment.mapper.FeeKeyMapper;
import com.atguigu.lease.service.apartment.service.FeeKeyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeeKeyServiceImpl extends ServiceImpl<FeeKeyMapper, FeeKey>
    implements FeeKeyService{

    @Autowired
    private FeeKeyMapper feeKeyMapper;
    @Override
    public List<FeeKeyVo> listFeeInfo() {
        List<FeeKeyVo> feeKeyVoList = feeKeyMapper.listFeeInfo();
        return feeKeyVoList;
    }
}
