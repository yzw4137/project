package com.atguigu.lease.web.app.service.impl;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.atguigu.lease.common.sms.AliyunSMSProperties;
import com.atguigu.lease.web.app.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsServiceImpl implements SmsService {
    @Autowired
    private Client client;

    @Override
    public void sendCode(String phone, String code) {
//        SendSmsRequest sendSmsRequest = new SendSmsRequest();
//        sendSmsRequest.setPhoneNumbers(phone);
//        sendSmsRequest.setSignName("速通互联验证码");
//        sendSmsRequest.setTemplateCode("100001");
//        sendSmsRequest.setTemplateParam("{\"code\":\"" + code + "\"}");
//        try {
//            client.sendSms(sendSmsRequest);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
        // 使用阿里云官方测试签名
        String signName = "阿里云短信测试";
        // 使用阿里云官方测试模板
        String templateCode = "SMS_154950909";
        // 确保模板参数格式正确
        String templateParam = "{\"code\":\"" + code + "\"}";

        SendSmsRequest sendSmsRequest = new SendSmsRequest();
        sendSmsRequest.setPhoneNumbers(phone);
        sendSmsRequest.setSignName(signName);
        sendSmsRequest.setTemplateCode(templateCode);
        sendSmsRequest.setTemplateParam(templateParam);

        try {
            System.out.println("开始发送短信，手机号：" + phone + "，验证码：" + code);
            com.aliyun.dysmsapi20170525.models.SendSmsResponse response = client.sendSms(sendSmsRequest);
            System.out.println("短信发送结果：" + response.getBody().toString());
        } catch (Exception e) {
            System.err.println("短信发送失败：" + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
