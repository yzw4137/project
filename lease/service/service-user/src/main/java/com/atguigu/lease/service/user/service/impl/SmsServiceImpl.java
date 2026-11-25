package com.atguigu.lease.service.user.service.impl;

import com.atguigu.lease.service.user.service.SmsService;
import org.springframework.stereotype.Service;

/**
 * Mock 短信服务 —— 不调阿里云 SDK，验证码直接打印到后端日志。
 * 开发阶段够用：Redis 照常存验证码，前端照常调登录接口，
 * 从后端控制台复制验证码即可。
 */
@Service
public class SmsServiceImpl implements SmsService {

    @Override
    public void sendCode(String phone, String code) {
        // 真实环境接入阿里云时替换为 AliyunSmsServiceImpl（保留 Mock 可直接切换）
        System.out.println("\u001b[33m[Mock SMS]\u001b[0m 手机号 " + phone + " 的验证码: \u001b[32m" + code + "\u001b[0m");
    }
}
