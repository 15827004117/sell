package com.fae.sell.utils;

/**
 * 功能描述:判断两个金额是否相等
 *
 * @作者 lj
 * @日期 2018/12/23 0023 13:16
 */
public class MathUtil {

    private static final Double MONEY_RANGE = 0.01;

    /**
     * 判断两个金额是否相等
     * @param d1
     * @param d2
     * @return
     */
    public static Boolean equals(Double d1, Double d2) {

        Double result = Math.abs(d1 - d2);

        if(result < MONEY_RANGE) {
            return true;
        }else {
            return false;
        }

    }
}
