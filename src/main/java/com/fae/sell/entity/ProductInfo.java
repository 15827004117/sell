package com.fae.sell.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 功能描述:商品实体类
 *
 * @作者 lj
 * @日期 2018/12/2 0002 20:36
 */
@Entity
@DynamicUpdate
@Data
public class ProductInfo {

    @Id
    private String productId;   //商品id

    private String productName;  //商品名称

    private BigDecimal productPrice;    //商品单价

    private Integer productStock;   //商品库存

    private String productDescription;  //商品描述

    private String productIcon;    //商品图片

    private Integer productStatus;  //商品状态

    private Integer categoryType;   //商品类目编号

    private Date createTime;    //创建时间

    private Date updateTime;    //更新时间

    public ProductInfo(){

    }

    public ProductInfo(String productId, String productName, BigDecimal productPrice, Integer productStock, String productDescription, String productIcon, Integer productStatus, Integer categoryType, Date createTime, Date updateTime) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.productDescription = productDescription;
        this.productIcon = productIcon;
        this.productStatus = productStatus;
        this.categoryType = categoryType;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
