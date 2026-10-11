package com.atguigu.lease.web.app.service.impl;

import com.atguigu.lease.api.lease.ReviewClient;
import com.atguigu.lease.common.login.LoginUserHolder;
import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.vo.review.CommentItemVo;
import com.atguigu.lease.model.vo.review.CommentSubmitVo;
import com.atguigu.lease.model.vo.review.ReviewItemVo;
import com.atguigu.lease.model.vo.review.ReviewQueryVo;
import com.atguigu.lease.model.vo.review.ReviewSubmitVo;
import com.atguigu.lease.web.app.service.ReviewService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewClient reviewClient;

    private Long currentUserId() {
        return LoginUserHolder.getLoginUser().getUserId();
    }

    @Override
    public Page<ReviewItemVo> pageItem(long current, long size, String sortType) {
        ReviewQueryVo queryVo = new ReviewQueryVo();
        queryVo.setSortType(sortType);
        queryVo.setUserId(currentUserId());
        Result<Page<ReviewItemVo>> result = reviewClient.pageItem(current, size, queryVo);
        return result.getData();
    }

    @Override
    public List<ReviewItemVo> listMyItem() {
        Result<List<ReviewItemVo>> result = reviewClient.listMyItem(currentUserId());
        return result.getData() == null ? new ArrayList<>() : result.getData();
    }

    @Override
    public ReviewItemVo getDetailById(Long id) {
        return reviewClient.getDetailById(id, currentUserId()).getData();
    }

    @Override
    public void save(ReviewSubmitVo vo) {
        reviewClient.save(vo, currentUserId());
    }

    @Override
    public void like(Long id) {
        reviewClient.like(id, currentUserId());
    }

    @Override
    public List<CommentItemVo> listComment(Long reviewId, String sortType) {
        Result<List<CommentItemVo>> result = reviewClient.listComment(reviewId, sortType, currentUserId());
        return result.getData() == null ? new ArrayList<>() : result.getData();
    }

    @Override
    public void saveComment(CommentSubmitVo vo) {
        reviewClient.saveComment(vo, currentUserId());
    }

    @Override
    public void commentLike(Long id) {
        reviewClient.commentLike(id, currentUserId());
    }

    @Override
    public void removeComment(Long id) {
        reviewClient.removeComment(id, currentUserId());
    }
}
