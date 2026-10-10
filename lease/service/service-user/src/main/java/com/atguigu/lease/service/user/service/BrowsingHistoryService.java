package com.atguigu.lease.service.user.service;

import com.atguigu.lease.model.entity.BrowsingHistory;
import com.atguigu.lease.model.vo.history.HistoryItemVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface BrowsingHistoryService extends IService<BrowsingHistory> {

    IPage<HistoryItemVo> pageItemByUserId(Page<HistoryItemVo> page, Long userId);

    void saveHistory(Long userId, Long roomId);
}
