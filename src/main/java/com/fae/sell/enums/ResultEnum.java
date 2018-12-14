package com.fae.sell.enums;

import lombok.Getter;

/**
 * 功能描述:返回前端提示信息枚举
 *
 * @作者: lj
 * @创建时间: 2018/12/14 9:42
 */
@Getter
public enum ResultEnum {

    PRODUCT_NOT_EXST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"库存不足"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
