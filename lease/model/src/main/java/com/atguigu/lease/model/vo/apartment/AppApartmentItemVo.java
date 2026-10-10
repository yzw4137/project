package com.atguigu.lease.model.vo.apartment;


import com.atguigu.lease.model.entity.ApartmentInfo;
import com.atguigu.lease.model.entity.LabelInfo;
import com.atguigu.lease.model.vo.graph.GraphVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Schema(description = "App端公寓信息")
public class AppApartmentItemVo extends ApartmentInfo {

    private List<LabelInfo> labelInfoList;

    private List<GraphVo> graphVoList;

    private BigDecimal minRent;
}
