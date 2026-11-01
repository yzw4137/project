package com.atguigu.lease.model.vo.message;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "消息未读数概览")
@Data
public class MessageOverviewVo {

    @Schema(description = "系统通知未读数")
    private Integer noticeUnread = 0;

    @Schema(description = "评论消息未读数")
    private Integer commentUnread = 0;

    @Schema(description = "点赞消息未读数")
    private Integer likeUnread = 0;
}
