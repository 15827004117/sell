package com.fae.sell.service.impl;

import com.fae.sell.dto.OrderDTO;
import com.fae.sell.entity.OrderDetail;
import com.fae.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


/**
 * 功能描述:
 *
 * @作者: lj
 * @创建时间: 2018/12/14 14:22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    private final String BUYER_OPENID = "wx125754lj54as45"; //openId

    @Test
    public void create() {
        // 创建订单
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("光明之子");
        orderDTO.setBuyerAddress("隆庆");
        orderDTO.setBuyerPhone("13977777777");
        orderDTO.setBuyerOpenid("wx888xx79ad1x1");

        // 创建购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();

        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setProductId("ls201812022115001");  //商品
        orderDetail1.setProductQuantity(3);  //购买数量
        orderDetailList.add(orderDetail1);

        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setProductId("ls201812022125002");  //商品
        orderDetail2.setProductQuantity(1);  //购买数量
        orderDetailList.add(orderDetail2);

        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = orderService.create(orderDTO);
        log.info("-创建订单- result={}", result);

    }

    @Test
    public void findOne() {
    }

    @Test
    public void findList() {
    }

    @Test
    public void cancel() {
    }

    @Test
    public void finish() {
    }

    @Test
    public void paid() {
    }
}