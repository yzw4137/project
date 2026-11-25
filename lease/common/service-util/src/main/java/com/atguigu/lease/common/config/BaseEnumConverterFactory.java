package com.atguigu.lease.common.config;

import com.atguigu.lease.model.enums.BaseEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

/**
 * 全局 String → BaseEnum 转换工厂：支持按枚举 code（数字）或 name 绑定 @RequestParam / @PathVariable。
 * 移除 BFF 后浏览器直接调用领域服务，前端传数字 code，Spring 默认按 name 转换会失败，故注册此工厂。
 */
public class BaseEnumConverterFactory implements ConverterFactory<String, BaseEnum> {

    @Override
    public <T extends BaseEnum> Converter<String, T> getConverter(Class<T> targetType) {
        return new BaseEnumConverter<>(targetType);
    }

    private static final class BaseEnumConverter<T extends BaseEnum> implements Converter<String, T> {

        private final Class<T> enumType;

        BaseEnumConverter(Class<T> enumType) {
            this.enumType = enumType;
        }

        @Override
        public T convert(String source) {
            if (source == null || source.isEmpty()) {
                return null;
            }
            for (T constant : enumType.getEnumConstants()) {
                // BaseEnum 实现均为枚举，此处强转取枚举 name 进行匹配
                if (((Enum<?>) constant).name().equals(source) || String.valueOf(constant.getCode()).equals(source)) {
                    return constant;
                }
            }
            throw new IllegalArgumentException("无法将值 '" + source + "' 转换为枚举 " + enumType.getSimpleName());
        }
    }
}
