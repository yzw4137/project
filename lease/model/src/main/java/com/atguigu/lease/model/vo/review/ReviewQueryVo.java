package com.atguigu.lease.model.vo.review;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "评价分页查询参数")
public class ReviewQueryVo {

    @Schema(description = "排序方式：latest 最新 / hot 最热")
    private String sortType;

    @Schema(description = "当前用户id（用于 isLiked 判断）")
    private Long userId;

    @Schema(description = "公寓id筛选（预留）")
    private Long apartmentId;
}
