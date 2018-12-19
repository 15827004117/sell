package com.fae.sell.service.impl;

import com.fae.sell.dto.OrderDTO;
import com.fae.sell.service.OrderService;
import com.fae.sell.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * 功能描述:
 *
 * @作者: lj
 * @创建时间: 2018/12/19 15:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PayServiceImplTest {

    @Autowired
    private PayService payService;

    @Autowired
    private OrderService orderService;

    @Test
    public void create() {
        OrderDTO orderDTO = orderService.findOne("1544778694648360672");
        payService.create(orderDTO);
    }
}