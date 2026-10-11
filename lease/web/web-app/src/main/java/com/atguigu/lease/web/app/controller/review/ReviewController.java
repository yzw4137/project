package com.atguigu.lease.web.app.controller.review;

import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.vo.review.CommentItemVo;
import com.atguigu.lease.model.vo.review.CommentSubmitVo;
import com.atguigu.lease.model.vo.review.ReviewItemVo;
import com.atguigu.lease.model.vo.review.ReviewSubmitVo;
import com.atguigu.lease.web.app.service.ReviewService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "评价信息")
@RestController
@RequestMapping("/app/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Operation(summary = "分页查询评价列表（圈子，支持最新/最热）")
    @GetMapping("pageItem")
    public Result<Page<ReviewItemVo>> pageItem(@RequestParam long current,
                                               @RequestParam long size,
                                               @RequestParam(required = false) String sortType) {
        return Result.ok(reviewService.pageItem(current, size, sortType));
    }

    @Operation(summary = "查询我的评价列表")
    @GetMapping("listMyItem")
    public Result<List<ReviewItemVo>> listMyItem() {
        return Result.ok(reviewService.listMyItem());
    }

    @Operation(summary = "发布评价")
    @PostMapping("save")
    public Result save(@RequestBody ReviewSubmitVo vo) {
        reviewService.save(vo);
        return Result.ok();
    }

    @Operation(summary = "点赞/取消点赞评价")
    @PostMapping("like")
    public Result like(@RequestBody Map<String, Object> body) {
        Long id = Long.valueOf(body.get("id").toString());
        reviewService.like(id);
        return Result.ok();
    }

    @Operation(summary = "查询评价的评论列表（支持最新/最热）")
    @GetMapping("comment/list")
    public Result<List<CommentItemVo>> listComment(@RequestParam Long reviewId,
                                                   @RequestParam(required = false) String sortType) {
        return Result.ok(reviewService.listComment(reviewId, sortType));
    }

    @Operation(summary = "发表评论")
    @PostMapping("comment/save")
    public Result saveComment(@RequestBody CommentSubmitVo vo) {
        reviewService.saveComment(vo);
        return Result.ok();
    }

    @Operation(summary = "点赞/取消点赞评论")
    @PostMapping("comment/like")
    public Result commentLike(@RequestBody Map<String, Object> body) {
        Long id = Long.valueOf(body.get("id").toString());
        reviewService.commentLike(id);
        return Result.ok();
    }

    @Operation(summary = "删除评论")
    @DeleteMapping("comment/removeById")
    public Result removeComment(@RequestParam Long id) {
        reviewService.removeComment(id);
        return Result.ok();
    }
}
