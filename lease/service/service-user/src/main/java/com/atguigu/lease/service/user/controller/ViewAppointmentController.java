package com.atguigu.lease.service.user.controller;

import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.entity.ViewAppointment;
import com.atguigu.lease.model.enums.AppointmentStatus;
import com.atguigu.lease.model.vo.appointment.AppointmentItemVo;
import com.atguigu.lease.model.vo.appointment.AppointmentQueryVo;
import com.atguigu.lease.model.vo.appointment.AppointmentVo;
import com.atguigu.lease.service.user.service.ViewAppointmentService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "预约看房管理")
@RequestMapping("/appointment")
@RestController
public class ViewAppointmentController {

    @Autowired
    private ViewAppointmentService viewAppointmentService;

    @GetMapping("page")
    @Operation(summary = "分页查询预约信息")
    public Result<Page<AppointmentVo>> page(@RequestParam long current, @RequestParam long size, AppointmentQueryVo queryVo) {
        Page<AppointmentVo> page = new Page<>(current, size);
        return Result.ok((Page<AppointmentVo>) viewAppointmentService.pageAppointment(page, queryVo));
    }

    @PostMapping("updateStatusById")
    @Operation(summary = "根据id更新预约状态")
    public Result updateStatusById(@RequestParam Long id, @RequestParam AppointmentStatus status) {
        viewAppointmentService.updateStatusById(id, status);
        return Result.ok();
    }

    @PostMapping("saveOrUpdate")
    @Operation(summary = "保存或更新看房预约")
    public Result saveOrUpdate(@RequestBody ViewAppointment viewAppointment) {
        viewAppointmentService.saveOrUpdate(viewAppointment);
        return Result.ok();
    }

    @GetMapping("listItemByUserId")
    @Operation(summary = "查询个人预约看房列表")
    public Result<List<AppointmentItemVo>> listItemByUserId(@RequestParam Long userId) {
        return Result.ok(viewAppointmentService.listItemByUserId(userId));
    }

    @GetMapping("getById")
    @Operation(summary = "根据ID查询预约信息")
    public Result<ViewAppointment> getById(@RequestParam Long id) {
        return Result.ok(viewAppointmentService.getById(id));
    }
}
