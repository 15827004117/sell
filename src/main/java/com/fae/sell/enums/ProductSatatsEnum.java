package com.fae.sell.enums;

import lombok.Getter;

/**
 * 商品状态
 */
@Getter
public enum ProductSatatsEnum {

    UP(0,"在售"),
    DOWN(1,"下架");

    private Integer code;

    private String message;

    ProductSatatsEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
