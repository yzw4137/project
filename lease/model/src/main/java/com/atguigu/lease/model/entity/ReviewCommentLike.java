package com.atguigu.lease.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "评论点赞记录表")
@TableName(value = "review_comment_like")
@Data
public class ReviewCommentLike {

    @Schema(description = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "评论id")
    @TableField(value = "comment_id")
    private Long commentId;

    @Schema(description = "点赞用户id")
    @TableField(value = "user_id")
    private Long userId;
}
