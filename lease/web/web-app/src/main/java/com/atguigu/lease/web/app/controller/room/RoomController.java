package com.atguigu.lease.web.app.controller.room;


import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.vo.room.AppRoomDetailVo;
import com.atguigu.lease.model.vo.room.AppRoomItemVo;
import com.atguigu.lease.model.vo.room.AppRoomQueryVo;
import com.atguigu.lease.web.app.service.RoomInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "房间信息")
@RestController
@RequestMapping("/app/room")
public class RoomController {

    @Autowired
    private RoomInfoService roomInfoService;

    @Operation(summary = "分页查询房间列表")
    @GetMapping("pageItem")
    public Result<IPage<AppRoomItemVo>> pageItem(@RequestParam long current, @RequestParam long size, AppRoomQueryVo queryVo) {
        Page<AppRoomItemVo> page = new Page<>(current, size);
        IPage<AppRoomItemVo> pageModel = roomInfoService.pageItem(page, queryVo);
        return Result.ok(pageModel);
    }

    @Operation(summary = "根据id获取房间的详细信息")
    @GetMapping("getDetailById")
    public Result<AppRoomDetailVo> getDetailById(@RequestParam Long id) {
        AppRoomDetailVo roomInfo = roomInfoService.getDetailById(id);
        return Result.ok(roomInfo);
    }

    @Operation(summary = "根据公寓id分页查询房间列表")
    @GetMapping("pageItemByApartmentId")
    public Result<IPage<AppRoomItemVo>> pageItemByApartmentId(@RequestParam long current, @RequestParam long size, @RequestParam Long id) {
        Page<AppRoomItemVo> page = new Page<>(current, size);
        IPage<AppRoomItemVo> pageModel = roomInfoService.pageItemByApartmentId(page, id);
        return Result.ok(pageModel);
    }
}
