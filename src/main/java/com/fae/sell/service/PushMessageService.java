package com.fae.sell.service;

import com.fae.sell.dto.OrderDTO;

/**
 * 功能描述: 微信消息推送service
 *
 * @作者: lj
 * @创建时间: 2018/12/27 9:55
 */
public interface PushMessageService {

    void orderStatus(OrderDTO orderDTO);
}
