package com.atguigu.lease.service.apartment.controller;


import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.entity.RoomInfo;
import com.atguigu.lease.model.enums.ReleaseStatus;
import com.atguigu.lease.model.vo.room.RoomDetailVo;
import com.atguigu.lease.model.vo.room.RoomItemVo;
import com.atguigu.lease.model.vo.room.RoomQueryVo;
import com.atguigu.lease.model.vo.room.RoomSubmitVo;
import com.atguigu.lease.model.vo.room.AppRoomDetailVo;
import com.atguigu.lease.model.vo.room.AppRoomItemVo;
import com.atguigu.lease.model.vo.room.AppRoomQueryVo;
import com.atguigu.lease.service.apartment.service.RoomInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "房间信息管理")
@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomInfoService service;

    @Operation(summary = "保存或更新房间信息")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody RoomSubmitVo roomSubmitVo) {
        service.saveOrUpdateRoom(roomSubmitVo);
        return Result.ok();
    }

    @Operation(summary = "根据条件分页查询房间列表")
    @GetMapping("pageItem")
    public Result<Page<RoomItemVo>> pageItem(@RequestParam long current, @RequestParam long size, RoomQueryVo queryVo) {
        Page<RoomItemVo> page = new Page<>(current, size);
        Page<RoomItemVo> result = (Page<RoomItemVo>) service.pageRoomItemByQuery(page, queryVo);
        return Result.ok(result);
    }

    @Operation(summary = "根据id获取房间详细信息")
    @GetMapping("getDetailById")
    public Result<RoomDetailVo> getDetailById(@RequestParam Long id) {
        RoomDetailVo roomInfo = service.getRoomDetailById(id);
        return Result.ok(roomInfo);
    }

    @Operation(summary = "根据id删除房间信息")
    @DeleteMapping("removeById")
    public Result removeById(@RequestParam Long id) {
        service.removeRoomById(id);
        return Result.ok();
    }

    @Operation(summary = "根据id修改房间发布状态")
    @PostMapping("updateReleaseStatusById")
    public Result updateReleaseStatusById(Long id, ReleaseStatus status) {
        LambdaUpdateWrapper<RoomInfo> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(RoomInfo::getId, id);
        updateWrapper.set(RoomInfo::getIsRelease, status);
        service.update(updateWrapper);
        return Result.ok();
    }

    @GetMapping("listBasicByApartmentId")
    @Operation(summary = "根据公寓id查询房间列表")
    public Result<List<RoomInfo>> listBasicByApartmentId(Long id) {
        LambdaQueryWrapper<RoomInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RoomInfo::getApartmentId, id);
        queryWrapper.eq(RoomInfo::getIsRelease, ReleaseStatus.RELEASED);
        List<RoomInfo> roomInfoList = service.list(queryWrapper);
        return Result.ok(roomInfoList);
    }

    @Operation(summary = "[APP]分页查询房间列表")
    @GetMapping("app/pageItem")
    public Result<Page<AppRoomItemVo>> pageAppItem(@RequestParam long current, @RequestParam long size, AppRoomQueryVo queryVo) {
        Page<AppRoomItemVo> page = new Page<>(current, size);
        return Result.ok((Page<AppRoomItemVo>) service.pageItem(page, queryVo));
    }

    @Operation(summary = "[APP]根据公寓id分页查询房间列表")
    @GetMapping("app/pageItemByApartmentId")
    public Result<Page<AppRoomItemVo>> pageAppItemByApartmentId(@RequestParam long current, @RequestParam long size, @RequestParam Long id) {
        Page<AppRoomItemVo> page = new Page<>(current, size);
        return Result.ok((Page<AppRoomItemVo>) service.pageItemByApartmentId(page, id));
    }

    @Operation(summary = "[APP]根据id获取房间详细信息")
    @GetMapping("app/getDetailById")
    public Result<AppRoomDetailVo> getAppDetailById(@RequestParam Long id) {
        return Result.ok(service.getDetailById(id));
    }

}
