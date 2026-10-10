package com.atguigu.lease.service.lease;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.atguigu.lease")
@EnableDiscoveryClient
@EnableScheduling
public class ServiceLeaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceLeaseApplication.class, args);
    }
}
