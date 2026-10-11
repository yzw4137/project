package com.atguigu.lease.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "评价评论表")
@TableName(value = "review_comment")
@Data
public class ReviewComment extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema(description = "所属评价id")
    @TableField(value = "review_id")
    private Long reviewId;

    @Schema(description = "评论用户id")
    @TableField(value = "user_id")
    private Long userId;

    @Schema(description = "被回复的评论id，顶级评论为 NULL")
    @TableField(value = "parent_id")
    private Long parentId;

    @Schema(description = "评论内容")
    @TableField(value = "content")
    private String content;

    @Schema(description = "评论点赞数（兜底）")
    @TableField(value = "like_count")
    private Integer likeCount;
}
