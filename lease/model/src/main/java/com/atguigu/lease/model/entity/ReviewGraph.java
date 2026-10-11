package com.atguigu.lease.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "评价图片表")
@TableName(value = "review_graph")
@Data
public class ReviewGraph {

    @Schema(description = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "所属评价id")
    @TableField(value = "review_id")
    private Long reviewId;

    @Schema(description = "图片地址")
    @TableField(value = "url")
    private String url;

    @Schema(description = "图片名称")
    @TableField(value = "name")
    private String name;
}
