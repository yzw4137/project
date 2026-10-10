package com.atguigu.lease.service.lease;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = "com.atguigu.lease")
@EnableDiscoveryClient
public class ServiceLeaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceLeaseApplication.class, args);
    }
}
