package com.atguigu.lease.service.lease.controller;

import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.entity.LeaseAgreement;
import com.atguigu.lease.model.enums.LeaseStatus;
import com.atguigu.lease.model.vo.lease.AgreementQueryVo;
import com.atguigu.lease.service.lease.service.LeaseAgreementService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "租约管理")
@RestController
@RequestMapping("/agreement")
public class LeaseAgreementController {

    @Autowired
    private LeaseAgreementService leaseAgreementService;

    @Operation(summary = "保存或修改租约信息")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody LeaseAgreement leaseAgreement) {
        leaseAgreementService.saveOrUpdate(leaseAgreement);
        return Result.ok();
    }

    @Operation(summary = "根据条件分页查询租约列表")
    @GetMapping("page")
    public Result<Page<LeaseAgreement>> page(@RequestParam long current, @RequestParam long size, AgreementQueryVo queryVo) {
        Page<LeaseAgreement> page = new Page<>(current, size);
        return Result.ok((Page<LeaseAgreement>) leaseAgreementService.pageAgreement(page, queryVo));
    }

    @Operation(summary = "根据id查询租约信息")
    @GetMapping("getById")
    public Result<LeaseAgreement> getById(@RequestParam Long id) {
        return Result.ok(leaseAgreementService.getById(id));
    }

    @Operation(summary = "根据id删除租约信息")
    @DeleteMapping("removeById")
    public Result removeById(@RequestParam Long id) {
        leaseAgreementService.removeById(id);
        return Result.ok();
    }

    @Operation(summary = "根据id更新租约状态")
    @PostMapping("updateStatusById")
    public Result updateStatusById(@RequestParam Long id, @RequestParam LeaseStatus status) {
        leaseAgreementService.updateStatusById(id, status);
        return Result.ok();
    }

    @Operation(summary = "根据手机号查询租约列表")
    @GetMapping("listByPhone")
    public Result<List<LeaseAgreement>> listByPhone(@RequestParam String phone) {
        return Result.ok(leaseAgreementService.listByPhone(phone));
    }
}
