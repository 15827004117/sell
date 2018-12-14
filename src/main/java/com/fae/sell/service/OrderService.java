package com.fae.sell.service;

import com.fae.sell.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 功能描述:订单service
 *
 * @作者: lj
 * @创建时间: 2018/12/14 9:22
 */
public interface OrderService {

    /*创建订单*/
    OrderDTO create(OrderDTO orderDto);

    /*查询单个订单*/
    OrderDTO findOne(String orderId);

    /*查询订单列表(分页)*/
    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);

    /*取消订单*/
    OrderDTO cancel(OrderDTO orderDTO);

    /*完结订单*/
    OrderDTO finish(OrderDTO orderDTO);

    /*支付订单*/
    OrderDTO paid(OrderDTO orderDTO);
}
