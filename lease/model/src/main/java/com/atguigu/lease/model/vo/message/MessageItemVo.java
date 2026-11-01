package com.atguigu.lease.model.vo.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Schema(description = "消息项")
@Data
public class MessageItemVo {

    @Schema(description = "消息id")
    private Long id;

    @Schema(description = "消息类型 1系统通知 2评论 3点赞")
    private Integer type;

    @Schema(description = "触发用户id（系统通知为null）")
    private Long senderId;

    @Schema(description = "发送方昵称")
    private String nickname;

    @Schema(description = "发送方头像")
    private String avatarUrl;

    @Schema(description = "消息内容摘要")
    private String content;

    @Schema(description = "关联业务类型 review/comment/appointment/agreement")
    private String bizType;

    @Schema(description = "关联业务id（评论/点赞消息指向所属评价id）")
    private Long bizId;

    @Schema(description = "是否已读")
    private Boolean isRead;

    @Schema(description = "消息时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
