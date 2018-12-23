package com.fae.sell.service.impl;

import com.fae.sell.dto.OrderDTO;
import com.fae.sell.entity.OrderDetail;
import com.fae.sell.enums.OrderStatusEnum;
import com.fae.sell.enums.PayStatusEnum;
import com.fae.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    private final String BUYER_OPENID = "wx778sx178c9s7"; // openId

    private final String OEDER_ID = "1545029160961852939"; // orderId

    @Test
    public void create() {
        // 创建订单
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("四师兄");
        orderDTO.setBuyerAddress("书院二层楼");
        orderDTO.setBuyerPhone("13978456478");
        orderDTO.setBuyerOpenid("wx878sdf4845");

        // 创建购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();

        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setProductId("ls201812022115001");  //商品
        orderDetail2.setProductQuantity(13);  //购买数量
        orderDetailList.add(orderDetail2);

        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = orderService.create(orderDTO);
        log.info("-创建订单- result={}", result);

    }

    @Test
    public void findOne() {
        OrderDTO result = orderService.findOne(OEDER_ID);
        log.error(" 【查询单个订单】 result={}", result);
        Assert.assertEquals(OEDER_ID,result.getOrderId());
    }

    @Test
    public void findList() {
        Page<OrderDTO> orderDTOPage = orderService.findList("wx32x48542sdd7", PageRequest.of(0, 2));
        Assert.assertNotEquals(2, orderDTOPage.getTotalElements());
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = orderService.findOne(OEDER_ID);
        OrderDTO result = orderService.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(), result.getOrderStatus());
    }

    @Test
    public void finish() {
        OrderDTO orderDTO = orderService.findOne(OEDER_ID);
        OrderDTO result = orderService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(), result.getOrderStatus());
    }

    @Test
    public void paid() {
        OrderDTO orderDTO = orderService.findOne(OEDER_ID);
        OrderDTO result = orderService.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(), result.getPayStatus());
    }

    @Test
    public void list() {
        Page<OrderDTO> orderDTOPage = orderService.findList(PageRequest.of(0, 2));
        Assert.assertNotEquals(2, orderDTOPage.getTotalElements());
    }
}