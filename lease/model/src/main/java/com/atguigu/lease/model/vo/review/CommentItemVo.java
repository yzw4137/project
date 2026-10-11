package com.atguigu.lease.model.vo.review;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(description = "评论项")
public class CommentItemVo {

    @Schema(description = "评论id")
    private Long id;

    @Schema(description = "所属评价id")
    private Long reviewId;

    @Schema(description = "评论用户id")
    private Long userId;

    @Schema(description = "评论用户昵称")
    private String nickname;

    @Schema(description = "评论用户头像")
    private String avatarUrl;

    @Schema(description = "评论内容")
    private String content;

    @Schema(description = "被回复评论id，顶级评论为 null")
    private Long parentId;

    @Schema(description = "被回复用户昵称")
    private String replyToNickname;

    @Schema(description = "评论点赞数")
    private Integer likeCount;

    @Schema(description = "当前用户是否已点赞该评论")
    private Boolean isLiked;

    @Schema(description = "是否为当前用户发布的评论（用于显示删除按钮）")
    private Boolean isMine;

    @Schema(description = "评论时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
