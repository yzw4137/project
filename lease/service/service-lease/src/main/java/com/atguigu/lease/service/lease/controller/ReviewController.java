package com.atguigu.lease.service.lease.controller;

import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.vo.review.CommentItemVo;
import com.atguigu.lease.model.vo.review.CommentSubmitVo;
import com.atguigu.lease.model.vo.review.ReviewItemVo;
import com.atguigu.lease.model.vo.review.ReviewQueryVo;
import com.atguigu.lease.model.vo.review.ReviewSubmitVo;
import com.atguigu.lease.service.lease.service.ReviewService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "评价管理")
@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Operation(summary = "分页查询评价列表（圈子，支持最新/最热排序）")
    @GetMapping("pageItem")
    public Result<Page<ReviewItemVo>> pageItem(@RequestParam long current,
                                               @RequestParam long size,
                                               ReviewQueryVo queryVo) {
        return Result.ok(reviewService.pageItem(current, size, queryVo));
    }

    @Operation(summary = "查询当前用户发布的评价列表")
    @GetMapping("listMyItem")
    public Result<List<ReviewItemVo>> listMyItem(@RequestParam Long userId) {
        return Result.ok(reviewService.listMyItem(userId));
    }

    @Operation(summary = "根据id查询评价详情")
    @GetMapping("getDetailById")
    public Result<ReviewItemVo> getDetailById(@RequestParam Long id, @RequestParam(required = false) Long userId) {
        return Result.ok(reviewService.getDetailById(id, userId));
    }

    @Operation(summary = "发布评价")
    @PostMapping("save")
    public Result save(@RequestBody ReviewSubmitVo vo, @RequestParam Long userId) {
        reviewService.save(vo, userId);
        return Result.ok();
    }

    @Operation(summary = "点赞/取消点赞评价")
    @PostMapping("like")
    public Result like(@RequestParam Long id, @RequestParam Long userId) {
        reviewService.toggleLike(id, userId);
        return Result.ok();
    }

    @Operation(summary = "查询评价的评论列表（支持最新/最热排序）")
    @GetMapping("comment/list")
    public Result<List<CommentItemVo>> listComment(@RequestParam Long reviewId,
                                                   @RequestParam(required = false) String sortType,
                                                   @RequestParam(required = false) Long userId) {
        return Result.ok(reviewService.listComment(reviewId, sortType, userId));
    }

    @Operation(summary = "发表评论")
    @PostMapping("comment/save")
    public Result saveComment(@RequestBody CommentSubmitVo vo, @RequestParam Long userId) {
        reviewService.saveComment(vo, userId);
        return Result.ok();
    }

    @Operation(summary = "点赞/取消点赞评论")
    @PostMapping("comment/like")
    public Result commentLike(@RequestParam Long id, @RequestParam Long userId) {
        reviewService.toggleCommentLike(id, userId);
        return Result.ok();
    }

    @Operation(summary = "删除评论")
    @DeleteMapping("comment/removeById")
    public Result removeComment(@RequestParam Long id, @RequestParam Long userId) {
        reviewService.removeCommentById(id, userId);
        return Result.ok();
    }
}
