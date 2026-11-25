package com.atguigu.lease.service.user.service;

public interface SmsService {
    void sendCode(String phone, String code);
}
