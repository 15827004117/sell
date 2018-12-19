package com.fae.sell.service;

import com.fae.sell.dto.OrderDTO;

/**
 * 功能描述: 微信支付
 *
 * @作者: lj
 * @创建时间: 2018/12/19 10:41
 */
public interface PayService {

    void create(OrderDTO orderDTO);
}
