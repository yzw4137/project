package com.atguigu.lease.web.app.service;

import com.atguigu.lease.model.vo.review.CommentItemVo;
import com.atguigu.lease.model.vo.review.CommentSubmitVo;
import com.atguigu.lease.model.vo.review.ReviewItemVo;
import com.atguigu.lease.model.vo.review.ReviewSubmitVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

public interface ReviewService {

    Page<ReviewItemVo> pageItem(long current, long size, String sortType);

    List<ReviewItemVo> listMyItem();

    ReviewItemVo getDetailById(Long id);

    void save(ReviewSubmitVo vo);

    void like(Long id);

    List<CommentItemVo> listComment(Long reviewId, String sortType);

    void saveComment(CommentSubmitVo vo);

    void commentLike(Long id);

    void removeComment(Long id);
}
