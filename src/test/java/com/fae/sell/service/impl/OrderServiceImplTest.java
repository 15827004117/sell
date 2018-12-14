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
        orderDTO.setBuyerName("三师姐");
        orderDTO.setBuyerAddress("木袖");
        orderDTO.setBuyerPhone("15812345678");
        orderDTO.setBuyerOpenid(BUYER_OPENID);

        // 创建购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("ls201812022115001");  //商品
        orderDetail.setProductQuantity(1);  //购买数量
        orderDetailList.add(orderDetail);
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