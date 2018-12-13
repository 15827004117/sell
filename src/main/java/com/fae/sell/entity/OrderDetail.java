package com.fae.sell.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 功能描述:
 *
 * @作者 lj
 * @日期 2018/12/11 0011 20:48
 */
@Entity
@Data
@DynamicUpdate
public class OrderDetail {

    @Id
    private String detailId;    //

    private String orderId;     //订单id

    private String productId;   //商品id

    private String productName; //商品名称

    private BigDecimal productPrice;    //商品单价

    private Integer productQuantity;    //商品数量

    private String productIcon; //商品图片

    private Date createTime;    //创建时间

    private Date updateTime;    //更新时间
}
