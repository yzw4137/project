package com.atguigu.lease.common.utils;

import java.util.Random;

public class CodeUtil {
    public static String getRandomCode(Integer length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(10);
            sb.append(number);
        }
        return sb.toString();
    }
}
