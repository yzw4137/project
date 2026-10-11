package com.atguigu.lease.api.user;

import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.entity.ViewAppointment;
import com.atguigu.lease.model.enums.AppointmentStatus;
import com.atguigu.lease.model.vo.appointment.AppointmentItemVo;
import com.atguigu.lease.model.vo.appointment.AppointmentQueryVo;
import com.atguigu.lease.model.vo.appointment.AppointmentVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "service-user", contextId = "viewAppointmentClient")
public interface ViewAppointmentClient {

    @GetMapping("/appointment/page")
    Result<Page<AppointmentVo>> page(@RequestParam("current") long current, @RequestParam("size") long size, @SpringQueryMap AppointmentQueryVo queryVo);

    @PostMapping("/appointment/updateStatusById")
    Result updateStatusById(@RequestParam("id") Long id, @RequestParam("status") AppointmentStatus status);

    @PostMapping("/appointment/saveOrUpdate")
    Result saveOrUpdate(@RequestBody ViewAppointment viewAppointment);

    @GetMapping("/appointment/listItemByUserId")
    Result<List<AppointmentItemVo>> listItemByUserId(@RequestParam("userId") Long userId);

    @GetMapping("/appointment/getById")
    Result<ViewAppointment> getById(@RequestParam("id") Long id);
}
