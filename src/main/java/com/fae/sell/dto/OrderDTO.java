package com.fae.sell.dto;

import com.fae.sell.entity.OrderDetail;
import com.fae.sell.utils.serializer.DateToLongSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 功能描述:订单DTO
 *
 * @作者: lj
 * @创建时间: 2018/12/14 9:28
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {

    private String orderId; //订单id

    private String buyerName;   //买家名称

    private String buyerPhone;  //买家电话

    private String buyerAddress;    //买家地址

    private String buyerOpenid; //买家微信openId

    private BigDecimal orderAmount; //订单总金额

    private Integer orderStatus;    //订单状态,默认为0，新订单

    private Integer payStatus;  //支付状态,默认为0,未支付

    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",locale="zh",timezone="GMT+8")
    private Date createTime;    //创建时间

    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",locale="zh",timezone="GMT+8")
    private Date updateTime;    //更新时间

    private List<OrderDetail> orderDetailList;
}
