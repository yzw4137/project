package com.atguigu.lease.model.vo.room;

import com.atguigu.lease.model.entity.*;
import com.atguigu.lease.model.vo.apartment.AppApartmentItemVo;
import com.atguigu.lease.model.vo.attr.AttrValueVo;
import com.atguigu.lease.model.vo.fee.FeeValueVo;
import com.atguigu.lease.model.vo.graph.GraphVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "APP房间详情")
public class AppRoomDetailVo extends RoomInfo {

    @Schema(description = "所属公寓信息")
    private AppApartmentItemVo apartmentItemVo;

    @Schema(description = "图片列表")
    private List<GraphVo> graphVoList;

    @Schema(description = "属性信息列表")
    private List<AttrValueVo> attrValueVoList;

    @Schema(description = "配套信息列表")
    private List<FacilityInfo> facilityInfoList;

    @Schema(description = "标签信息列表")
    private List<LabelInfo> labelInfoList;

    @Schema(description = "支付方式列表")
    private List<PaymentType> paymentTypeList;

    @Schema(description = "杂费列表")
    private List<FeeValueVo> feeValueVoList;

    @Schema(description = "租期列表")
    private List<LeaseTerm> leaseTermList;

}
