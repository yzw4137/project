package com.atguigu.lease.service.user.mapper;

import com.atguigu.lease.model.entity.BrowsingHistory;
import com.atguigu.lease.model.vo.graph.GraphVo;
import com.atguigu.lease.model.vo.history.HistoryItemVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public interface BrowsingHistoryMapper extends BaseMapper<BrowsingHistory> {

    IPage<HistoryItemVo> pageItemByUserId(Page<HistoryItemVo> page, Long userId);

    IPage<GraphVo> selectGraphVoByRoomId(Page<GraphVo> page, Long roomId);
}
