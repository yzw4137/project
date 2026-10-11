package com.atguigu.lease.web.app.service.impl;

import com.atguigu.lease.api.user.BrowsingHistoryClient;
import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.vo.history.HistoryItemVo;
import com.atguigu.lease.web.app.service.BrowsingHistoryService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class BrowsingHistoryServiceImpl implements BrowsingHistoryService {

    @Autowired
    private BrowsingHistoryClient browsingHistoryClient;

    @Override
    public IPage<HistoryItemVo> pageItemByUserId(Page<HistoryItemVo> page, Long userId) {
        Result<Page<HistoryItemVo>> result =
                browsingHistoryClient.pageItem(page.getCurrent(), page.getSize(), userId);
        return result.getData();
    }

    @Override
    @Async
    public void saveHistory(Long userId, Long id) {
        browsingHistoryClient.saveHistory(userId, id);
    }
}
