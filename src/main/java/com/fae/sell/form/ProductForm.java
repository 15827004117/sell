package com.fae.sell.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

/**
 * 功能描述:
 *
 * @作者: lj
 * @创建时间: 2018/12/25 9:54
 */
@Data
public class ProductForm {

    private String productId;   //商品id

    private String productName;  //商品名称

    private BigDecimal productPrice;    //商品单价

    private Integer productStock;   //商品库存

    private String productDescription;  //商品描述

    private String productIcon;    //商品图片

    private Integer categoryType;   //商品类目编号
}
