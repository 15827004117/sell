package com.fae.sell.dto;

import lombok.Data;

/**
 * 功能描述: 购物车DTO
 *
 * @作者: lj
 * @创建时间: 2018/12/14 11:04
 */
@Data
public class CartDTO {

    private String productId;

    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
