package com.atguigu.lease.api.apartment;

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
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "service-apartment")
public interface RoomClient {

    @PostMapping("/room/saveOrUpdate")
    Result saveOrUpdate(@RequestBody RoomSubmitVo roomSubmitVo);

    @GetMapping("/room/pageItem")
    Result<IPage<RoomItemVo>> pageItem(@RequestParam("current") long current, @RequestParam("size") long size, RoomQueryVo queryVo);

    @GetMapping("/room/getDetailById")
    Result<RoomDetailVo> getDetailById(@RequestParam("id") Long id);

    @DeleteMapping("/room/removeById")
    Result removeById(@RequestParam("id") Long id);

    @PostMapping("/room/updateReleaseStatusById")
    Result updateReleaseStatusById(@RequestParam("id") Long id, @RequestParam("status") ReleaseStatus status);

    @GetMapping("/room/listBasicByApartmentId")
    Result<List<RoomInfo>> listBasicByApartmentId(@RequestParam("id") Long id);

    @GetMapping("/room/app/pageItem")
    Result<IPage<AppRoomItemVo>> pageAppItem(@RequestParam("current") long current, @RequestParam("size") long size, AppRoomQueryVo queryVo);

    @GetMapping("/room/app/pageItemByApartmentId")
    Result<IPage<AppRoomItemVo>> pageAppItemByApartmentId(@RequestParam("current") long current, @RequestParam("size") long size, @RequestParam("id") Long id);

    @GetMapping("/room/app/getDetailById")
    Result<AppRoomDetailVo> getAppDetailById(@RequestParam("id") Long id);
}
