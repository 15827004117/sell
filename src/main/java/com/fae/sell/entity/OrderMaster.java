package com.fae.sell.entity;

import com.fae.sell.enums.OrderStatusEnum;
import com.fae.sell.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 功能描述:订单详情实体类
 *
 * @作者 lj
 * @日期 2018/12/11 0011 20:30
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {

    @Id
    private String orderId; //订单id

    private String buyerName;   //买家名称

    private String buyerPhone;  //买家电话

    private String buyerAddress;    //买家地址

    private String buyerOpenid; //买家微信openId

    private BigDecimal orderAmount; //订单总金额

    private Integer orderStatus = OrderStatusEnum.NEW.getCode();    //订单状态,默认为0，新订单

    private Integer payStatus = PayStatusEnum.WAIT.getCode();  //支付状态,默认为0,未支付

    private Date createTime;    //创建时间

    private Date updateTime;    //更新时间

}
