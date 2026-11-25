package com.atguigu.lease.service.user.vo.admin;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminCaptchaVo {
    private String image;
    private String key;
}
