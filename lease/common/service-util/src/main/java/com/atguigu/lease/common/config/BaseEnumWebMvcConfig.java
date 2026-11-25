package com.atguigu.lease.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 注册全局 String → BaseEnum 转换器，使 @RequestParam / @PathVariable 支持按枚举 code 绑定。
 * 置于 service-util，被各领域服务组件扫描后自动生效。
 */
@Configuration
public class BaseEnumWebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new BaseEnumConverterFactory());
    }
}
