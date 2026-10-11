package com.atguigu.lease.api.lease;

import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.entity.LeaseAgreement;
import com.atguigu.lease.model.enums.LeaseStatus;
import com.atguigu.lease.model.vo.lease.AgreementQueryVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "service-lease", contextId = "leaseAgreementClient")
public interface LeaseAgreementClient {

    @PostMapping("/agreement/saveOrUpdate")
    Result saveOrUpdate(@RequestBody LeaseAgreement leaseAgreement);

    @GetMapping("/agreement/page")
    Result<Page<LeaseAgreement>> page(@RequestParam("current") long current, @RequestParam("size") long size, @SpringQueryMap AgreementQueryVo queryVo);

    @GetMapping("/agreement/getById")
    Result<LeaseAgreement> getById(@RequestParam("id") Long id);

    @DeleteMapping("/agreement/removeById")
    Result removeById(@RequestParam("id") Long id);

    @PostMapping("/agreement/updateStatusById")
    Result updateStatusById(@RequestParam("id") Long id, @RequestParam("status") LeaseStatus status);

    @GetMapping("/agreement/listByPhone")
    Result<List<LeaseAgreement>> listByPhone(@RequestParam("phone") String phone);
}
