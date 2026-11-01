package com.atguigu.lease.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "消息通知表")
@TableName(value = "message_info")
@Data
public class MessageInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema(description = "接收用户id")
    @TableField(value = "user_id")
    private Long userId;

    @Schema(description = "消息类型 1系统通知 2评论 3点赞")
    @TableField(value = "type")
    private Integer type;

    @Schema(description = "触发用户id（系统通知为空）")
    @TableField(value = "sender_id")
    private Long senderId;

    @Schema(description = "消息内容摘要")
    @TableField(value = "content")
    private String content;

    @Schema(description = "关联业务类型 review/comment/appointment/agreement")
    @TableField(value = "biz_type")
    private String bizType;

    @Schema(description = "关联业务id")
    @TableField(value = "biz_id")
    private Long bizId;

    @Schema(description = "是否已读")
    @TableField(value = "is_read")
    private Boolean isRead;
}
