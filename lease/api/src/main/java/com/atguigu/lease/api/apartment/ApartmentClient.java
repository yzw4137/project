package com.atguigu.lease.api.apartment;

import com.atguigu.lease.common.result.Result;
import com.atguigu.lease.model.entity.ApartmentInfo;
import com.atguigu.lease.model.enums.ReleaseStatus;
import com.atguigu.lease.model.vo.apartment.ApartmentDetailVo;
import com.atguigu.lease.model.vo.apartment.ApartmentItemVo;
import com.atguigu.lease.model.vo.apartment.ApartmentQueryVo;
import com.atguigu.lease.model.vo.apartment.ApartmentSubmitVo;
import com.atguigu.lease.model.vo.apartment.AppApartmentDetailVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "service-apartment")
public interface ApartmentClient {

    @PostMapping("/apartment/saveOrUpdate")
    Result saveOrUpdate(@RequestBody ApartmentSubmitVo apartmentSubmitVo);

    @GetMapping("/apartment/pageItem")
    Result<IPage<ApartmentItemVo>> pageItem(@RequestParam("current") long current, @RequestParam("size") long size, ApartmentQueryVo queryVo);

    @GetMapping("/apartment/getDetailById")
    Result<ApartmentDetailVo> getDetailById(@RequestParam("id") Long id);

    @DeleteMapping("/apartment/removeById")
    Result removeById(@RequestParam("id") Long id);

    @PostMapping("/apartment/updateReleaseStatusById")
    Result updateReleaseStatusById(@RequestParam("id") Long id, @RequestParam("status") ReleaseStatus status);

    @GetMapping("/apartment/listInfoByDistrictId")
    Result<List<ApartmentInfo>> listInfoByDistrictId(@RequestParam("id") Long id);

    @GetMapping("/apartment/app/getDetailById")
    Result<AppApartmentDetailVo> getAppDetailById(@RequestParam("id") Long id);
}
