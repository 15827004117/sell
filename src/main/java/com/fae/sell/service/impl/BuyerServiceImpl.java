package com.fae.sell.service.impl;

import com.fae.sell.dto.OrderDTO;
import com.fae.sell.enums.ResultEnum;
import com.fae.sell.exception.SellException;
import com.fae.sell.service.BuyerService;
import com.fae.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能描述: 买家实现类
 *
 * @作者: lj
 * @创建时间: 2018/12/18 8:30
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openid, String orderid) {
        OrderDTO orderDTO = orderService.findOne(orderid);
        // 订单为空返回空
        if(orderDTO == null) {
            return null;
        }
        if(!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)) {
            log.error("【订单查询】 订单openid不一致");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        return orderDTO;
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderid) {
        OrderDTO orderDTO = orderService.findOne(orderid);
        // 订单为空抛异常
        if(orderDTO == null) {
            log.error("【取消订单】 查找不到该订单 orderId={}", orderid);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        if(!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)) {
            log.error("【订单查询】 订单openid不一致");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        return orderDTO;
    }
}
