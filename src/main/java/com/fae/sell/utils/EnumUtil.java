package com.fae.sell.utils;

import com.fae.sell.enums.CodeEnum;

/**
 * 功能描述: 获取Enum状态工具
 *
 * @作者 lj
 * @日期 2018/12/23 0023 19:42
 */
public class EnumUtil {

    public static <T extends CodeEnum>T getByCode(Integer code, Class<T> enumClass) {
        for (T each: enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
