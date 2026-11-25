package com.atguigu.lease.model.vo.lease;

import com.atguigu.lease.model.entity.ApartmentInfo;
import com.atguigu.lease.model.entity.LeaseAgreement;
import com.atguigu.lease.model.entity.LeaseTerm;
import com.atguigu.lease.model.entity.PaymentType;
import com.atguigu.lease.model.entity.RoomInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "租约信息VO（管理端列表/详情，富化公寓/房间/支付方式/租期）")
public class AgreementVo extends LeaseAgreement {

    @Schema(description = "所属公寓信息")
    private ApartmentInfo apartmentInfo;

    @Schema(description = "所属房间信息")
    private RoomInfo roomInfo;

    @Schema(description = "支付方式")
    private PaymentType paymentType;

    @Schema(description = "租期")
    private LeaseTerm leaseTerm;
}
