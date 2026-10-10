package com.atguigu.lease.web.admin.custom.converter;

import com.atguigu.lease.model.enums.BaseEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

@Component
public class StringToBaseEnumConverterFactory implements ConverterFactory<String, BaseEnum> {
    @Override
    public <T extends BaseEnum> Converter<String, T> getConverter(Class<T> targetType) {
        return new Converter<String, T>() {
            @Override
            public T convert(String source) {
                T[] values = targetType.getEnumConstants();
                for(T t : values){
                    if(t.getCode().equals(Integer.parseInt(source))){
                        return t;
                    }
                }
                throw new IllegalArgumentException(source+"是无效的参数");
            }
        };
    }
}
