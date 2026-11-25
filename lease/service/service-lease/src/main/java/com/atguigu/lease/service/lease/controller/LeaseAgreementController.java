package com.atguigu.lease.service.lease.controller;

import com.atguigu.lease.common.login.LoginUserHolder;
import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.entity.LeaseAgreement;
import com.atguigu.lease.model.enums.LeaseStatus;
import com.atguigu.lease.model.vo.lease.AgreementDetailVo;
import com.atguigu.lease.model.vo.lease.AgreementItemVo;
import com.atguigu.lease.model.vo.lease.AgreementQueryVo;
import com.atguigu.lease.model.vo.lease.AgreementVo;
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

    @Operation(summary = "根据条件分页查询租约列表（管理端，富化公寓/房间/支付方式/租期）")
    @GetMapping("page")
    public Result<Page<AgreementVo>> page(@RequestParam long current, @RequestParam long size, AgreementQueryVo queryVo) {
        Page<LeaseAgreement> page = new Page<>(current, size);
        return Result.ok((Page<AgreementVo>) leaseAgreementService.pageAgreement(page, queryVo));
    }

    @Operation(summary = "根据id查询租约信息（管理端，富化公寓/房间/支付方式/租期）")
    @GetMapping("getById")
    public Result<AgreementVo> getById(@RequestParam Long id) {
        return Result.ok(leaseAgreementService.getAgreementVoById(id));
    }

    @Operation(summary = "根据id查询租约详情（App端，富化公寓/房间信息）")
    @GetMapping("getDetailById")
    public Result<AgreementDetailVo> getDetailById(@RequestParam Long id) {
        return Result.ok(leaseAgreementService.getDetailById(id));
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

    @Operation(summary = "查询当前登录用户的租约列表（App端，富化公寓/房间信息）")
    @GetMapping("listItem")
    public Result<List<AgreementItemVo>> listItem() {
        // App 端 LoginUser.username 就是用户手机号
        String phone = LoginUserHolder.getLoginUser().getUsername();
        return Result.ok(leaseAgreementService.listItemByPhone(phone));
    }

    @Operation(summary = "根据手机号查询租约列表（管理端）")
    @GetMapping("listByPhone")
    public Result<List<LeaseAgreement>> listByPhone(@RequestParam String phone) {
        return Result.ok(leaseAgreementService.listByPhone(phone));
    }

    @Operation(summary = "将已到期租约置为已过期")
    @PostMapping("expireLeaseAgreement")
    public Result expireLeaseAgreement() {
        leaseAgreementService.expireLeaseAgreement();
        return Result.ok();
    }
}
