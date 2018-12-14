package com.fae.sell.utils;

import java.util.Random;

/**
 * 功能描述:生成唯一主键工具
 *
 * @作者: lj
 * @创建时间: 2018/12/14 10:27
 */
public class KeyUtil {

    public static synchronized String genUniqueKye(){
        // 生成六位随机数
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        // 加上系统时间，精确到毫秒
        String key = System.currentTimeMillis() + String.valueOf(number);

        return key;
    }
}
