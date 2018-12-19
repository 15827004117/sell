package com.fae.sell.controller;

import com.fae.sell.dto.OrderDTO;
import com.fae.sell.enums.ResultEnum;
import com.fae.sell.exception.SellException;
import com.fae.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 功能描述: 微信支付controller
 *
 * @作者: lj
 * @创建时间: 2018/12/19 10:35
 */
@Controller
@RequestMapping("pay")
@Slf4j
public class PayController {

    @Autowired
    private OrderService orderService;

    /**
     * 功能描述: 创建支付订单
     * @参数:
     * @返回:
     * @作者: lj
     * @创建时间: 2018/12/19 10:38
     */
    @GetMapping("/create")
    public void create(@RequestParam("openId") String openId,
                       @RequestParam("restrnUrl") String restrnUrl) {

        // 查询订单
        OrderDTO orderDTO = orderService.findOne(openId);
        if(orderDTO == null) {
            log.error("【创建支付订单】 找不到该订单信息 order={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        // 发起支付
    }
}
