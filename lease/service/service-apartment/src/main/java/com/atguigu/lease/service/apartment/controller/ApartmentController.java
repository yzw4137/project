package com.atguigu.lease.service.apartment.controller;


import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.entity.ApartmentInfo;
import com.atguigu.lease.model.enums.ReleaseStatus;
import com.atguigu.lease.model.vo.apartment.ApartmentDetailVo;
import com.atguigu.lease.model.vo.apartment.ApartmentItemVo;
import com.atguigu.lease.model.vo.apartment.ApartmentQueryVo;
import com.atguigu.lease.model.vo.apartment.ApartmentSubmitVo;
import com.atguigu.lease.model.vo.apartment.AppApartmentDetailVo;
import com.atguigu.lease.service.apartment.service.ApartmentInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "公寓信息管理")
@RestController
@RequestMapping("/apartment")
public class ApartmentController {

    @Autowired
    private ApartmentInfoService apartmentService;

    @Operation(summary = "保存或更新公寓信息")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody ApartmentSubmitVo apartmentSubmitVo) {
        apartmentService.saveOrUpdateApatment(apartmentSubmitVo);
        return Result.ok();
    }

    @Operation(summary = "根据条件分页查询公寓列表")
    @GetMapping("pageItem")
    public Result<Page<ApartmentItemVo>> pageItem(@RequestParam long current, @RequestParam long size, ApartmentQueryVo queryVo) {
        Page<ApartmentItemVo> page = new Page<>(current, size);
        return Result.ok((Page<ApartmentItemVo>) apartmentService.pageItem(page, queryVo));
    }

    @Operation(summary = "根据ID获取公寓详细信息")
    @GetMapping("getDetailById")
    public Result<ApartmentDetailVo> getDetailById(@RequestParam Long id) {
        ApartmentDetailVo apartmentDetailVo = apartmentService.getDetailById(id);
        return Result.ok(apartmentDetailVo);
    }

    @Operation(summary = "根据id删除公寓信息")
    @DeleteMapping("removeById")
    public Result removeById(@RequestParam Long id) {
        apartmentService.removeApartmentById(id);
        return Result.ok();
    }

    @Operation(summary = "根据id修改公寓发布状态")
    @PostMapping("updateReleaseStatusById")
    public Result updateReleaseStatusById(@RequestParam Long id, @RequestParam ReleaseStatus status) {
        LambdaUpdateWrapper<ApartmentInfo> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(ApartmentInfo::getId, id);
        wrapper.set(ApartmentInfo::getIsRelease, status);
        apartmentService.update(wrapper);
        return Result.ok();
    }

    @Operation(summary = "根据区县id查询公寓信息列表")
    @GetMapping("listInfoByDistrictId")
    public Result<List<ApartmentInfo>> listInfoByDistrictId(@RequestParam Long id) {
        LambdaQueryWrapper<ApartmentInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ApartmentInfo::getDistrictId, id);
        List<ApartmentInfo> list = apartmentService.list(wrapper);
        return Result.ok(list);
    }

    @Operation(summary = "[APP]根据ID获取公寓详细信息")
    @GetMapping("app/getDetailById")
    public Result<AppApartmentDetailVo> getAppDetailById(@RequestParam Long id) {
        return Result.ok(apartmentService.getAppDetailById(id));
    }
}
