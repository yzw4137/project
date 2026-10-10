package com.atguigu.lease.api.apartment;

import com.atguigu.lease.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value = "service-apartment")
public interface FileClient {

    @PostMapping("/file/upload")
    Result<String> upload(@RequestParam("file") MultipartFile file);
}
