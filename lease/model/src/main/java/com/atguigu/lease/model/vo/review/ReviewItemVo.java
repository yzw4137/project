package com.atguigu.lease.model.vo.review;

import com.atguigu.lease.model.vo.graph.GraphVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Schema(description = "评价列表项")
public class ReviewItemVo {

    @Schema(description = "评价id")
    private Long id;

    @Schema(description = "发布用户id")
    private Long userId;

    @Schema(description = "发布用户昵称")
    private String nickname;

    @Schema(description = "发布用户头像")
    private String avatarUrl;

    @Schema(description = "关联公寓id")
    private Long apartmentId;

    @Schema(description = "关联公寓名称")
    private String apartmentName;

    @Schema(description = "关联房间id")
    private Long roomId;

    @Schema(description = "关联房间号")
    private String roomNumber;

    @Schema(description = "关联租约id")
    private Long agreementId;

    @Schema(description = "评价正文")
    private String content;

    @Schema(description = "评分 1~5")
    private Integer rating;

    @Schema(description = "评价图片列表")
    private List<GraphVo> graphVoList;

    @Schema(description = "点赞数")
    private Integer likeCount;

    @Schema(description = "当前用户是否已点赞")
    private Boolean isLiked;

    @Schema(description = "评论数")
    private Integer commentCount;

    @Schema(description = "发布时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
