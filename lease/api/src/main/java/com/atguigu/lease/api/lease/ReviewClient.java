package com.atguigu.lease.api.lease;

import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.vo.review.CommentItemVo;
import com.atguigu.lease.model.vo.review.CommentSubmitVo;
import com.atguigu.lease.model.vo.review.ReviewItemVo;
import com.atguigu.lease.model.vo.review.ReviewQueryVo;
import com.atguigu.lease.model.vo.review.ReviewSubmitVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "service-lease", contextId = "reviewClient")
public interface ReviewClient {

    @GetMapping("/review/pageItem")
    Result<Page<ReviewItemVo>> pageItem(@RequestParam("current") long current,
                                        @RequestParam("size") long size,
                                        @SpringQueryMap ReviewQueryVo queryVo);

    @GetMapping("/review/listMyItem")
    Result<List<ReviewItemVo>> listMyItem(@RequestParam("userId") Long userId);

    @GetMapping("/review/getDetailById")
    Result<ReviewItemVo> getDetailById(@RequestParam("id") Long id,
                                       @RequestParam(value = "userId", required = false) Long userId);

    @PostMapping("/review/save")
    Result save(@RequestBody ReviewSubmitVo vo, @RequestParam("userId") Long userId);

    @PostMapping("/review/like")
    Result like(@RequestParam("id") Long id, @RequestParam("userId") Long userId);

    @GetMapping("/review/comment/list")
    Result<List<CommentItemVo>> listComment(@RequestParam("reviewId") Long reviewId,
                                            @RequestParam(value = "sortType", required = false) String sortType,
                                            @RequestParam(value = "userId", required = false) Long userId);

    @PostMapping("/review/comment/save")
    Result saveComment(@RequestBody CommentSubmitVo vo, @RequestParam("userId") Long userId);

    @PostMapping("/review/comment/like")
    Result commentLike(@RequestParam("id") Long id, @RequestParam("userId") Long userId);

    @DeleteMapping("/review/comment/removeById")
    Result removeComment(@RequestParam("id") Long id, @RequestParam("userId") Long userId);
}
