package com.fae.sell.service;

import com.fae.sell.dto.OrderDTO;

/**
 * 功能描述: 买家service
 *
 * @作者: lj
 * @创建时间: 2018/12/18 8:28
 */
public interface BuyerService {

    public OrderDTO findOrderOne(String openid, String orderid);

    public OrderDTO cancelOrder(String openid, String orderid);
}
