package com.atguigu.lease.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "房源评价表")
@TableName(value = "review_info")
@Data
public class ReviewInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema(description = "发布用户id")
    @TableField(value = "user_id")
    private Long userId;

    @Schema(description = "关联公寓id")
    @TableField(value = "apartment_id")
    private Long apartmentId;

    @Schema(description = "关联房间id")
    @TableField(value = "room_id")
    private Long roomId;

    @Schema(description = "关联租约id")
    @TableField(value = "agreement_id")
    private Long agreementId;

    @Schema(description = "评价正文")
    @TableField(value = "content")
    private String content;

    @Schema(description = "评分 1~5")
    @TableField(value = "rating")
    private Integer rating;

    @Schema(description = "点赞数（兜底，最终以 Redis 为准）")
    @TableField(value = "like_count")
    private Integer likeCount;

    @Schema(description = "评论数")
    @TableField(value = "comment_count")
    private Integer commentCount;
}
