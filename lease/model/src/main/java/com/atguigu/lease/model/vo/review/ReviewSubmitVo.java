package com.atguigu.lease.model.vo.review;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "发布评价请求参数")
public class ReviewSubmitVo {

    @Schema(description = "租约id")
    private Long agreementId;

    @Schema(description = "评分 1~5")
    private Integer rating;

    @Schema(description = "评价正文")
    private String content;

    @Schema(description = "评价图片地址列表")
    private List<String> imageUrlList;
}
