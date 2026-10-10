package com.atguigu.lease.web.admin.custom.converter;

import com.atguigu.lease.model.enums.ItemType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

//@Component
public class StringToItemTypeConverter implements Converter<String, ItemType> {
    @Override
    public ItemType convert(String source) {

        ItemType[] values = ItemType.values();
        for(ItemType itemType : values){
            if(itemType.getCode().equals(Integer.parseInt(source))){
                return itemType;
            }
        }
        throw new IllegalArgumentException(source+"是无效的参数");
    }
}
