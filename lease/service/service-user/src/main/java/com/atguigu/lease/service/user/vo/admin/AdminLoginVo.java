package com.atguigu.lease.service.user.vo.admin;

import lombok.Data;

@Data
public class AdminLoginVo {
    private String username;
    private String password;
    private String captchaKey;
    private String captchaCode;
}
