package com.atguigu.lease.model.vo.review;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "发表评论请求参数")
public class CommentSubmitVo {

    @Schema(description = "所属评价id")
    private Long reviewId;

    @Schema(description = "评论内容")
    private String content;

    @Schema(description = "回复的评论id，不传为顶级评论")
    private Long parentId;
}
