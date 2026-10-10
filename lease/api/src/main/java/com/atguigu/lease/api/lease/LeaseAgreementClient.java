package com.atguigu.lease.api.lease;

import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.entity.LeaseAgreement;
import com.atguigu.lease.model.enums.LeaseStatus;
import com.atguigu.lease.model.vo.lease.AgreementQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "service-lease")
public interface LeaseAgreementClient {

    @PostMapping("/agreement/saveOrUpdate")
    Result saveOrUpdate(@RequestBody LeaseAgreement leaseAgreement);

    @GetMapping("/agreement/page")
    Result<IPage<LeaseAgreement>> page(@RequestParam("current") long current, @RequestParam("size") long size, AgreementQueryVo queryVo);

    @GetMapping("/agreement/getById")
    Result<LeaseAgreement> getById(@RequestParam("id") Long id);

    @DeleteMapping("/agreement/removeById")
    Result removeById(@RequestParam("id") Long id);

    @PostMapping("/agreement/updateStatusById")
    Result updateStatusById(@RequestParam("id") Long id, @RequestParam("status") LeaseStatus status);

    @GetMapping("/agreement/listByPhone")
    Result<List<LeaseAgreement>> listByPhone(@RequestParam("phone") String phone);
}
