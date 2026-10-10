package com.atguigu.lease.service.user.service.impl;

import com.atguigu.lease.model.entity.BrowsingHistory;
import com.atguigu.lease.model.vo.history.HistoryItemVo;
import com.atguigu.lease.service.user.mapper.BrowsingHistoryMapper;
import com.atguigu.lease.service.user.service.BrowsingHistoryService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BrowsingHistoryServiceImpl extends ServiceImpl<BrowsingHistoryMapper, BrowsingHistory>
        implements BrowsingHistoryService {

    @Autowired
    private BrowsingHistoryMapper browsingHistoryMapper;

    @Override
    public IPage<HistoryItemVo> pageItemByUserId(Page<HistoryItemVo> page, Long userId) {
        return browsingHistoryMapper.pageItemByUserId(page, userId);
    }

    @Override
    public void saveHistory(Long userId, Long roomId) {
        LambdaQueryWrapper<BrowsingHistory> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BrowsingHistory::getUserId, userId)
                .eq(BrowsingHistory::getRoomId, roomId);
        BrowsingHistory browsingHistory = browsingHistoryMapper.selectOne(wrapper);
        if (browsingHistory != null) {
            browsingHistory.setBrowseTime(new Date());
            browsingHistoryMapper.updateById(browsingHistory);
        } else {
            browsingHistory = new BrowsingHistory();
            browsingHistory.setUserId(userId);
            browsingHistory.setRoomId(roomId);
            browsingHistory.setBrowseTime(new Date());
            browsingHistoryMapper.insert(browsingHistory);
        }
    }
}
