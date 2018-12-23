package com.fae.sell.service;

import com.fae.sell.dto.OrderDTO;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;

/**
 * 功能描述: 微信支付
 *
 * @作者: lj
 * @创建时间: 2018/12/19 10:41
 */
public interface PayService {

    PayResponse create(OrderDTO orderDTO);

    PayResponse notify(String notifyData);

    RefundResponse refund(OrderDTO orderDTO);
}
