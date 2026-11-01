package com.atguigu.lease.model.vo.message;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "标记已读请求参数")
@Data
public class MessageReadVo {

    @Schema(description = "单条消息id（与type至少传一个）")
    private Long id;

    @Schema(description = "消息类型 1系统通知 2评论 3点赞（id为空时按类型全部已读）")
    private Integer type;
}
