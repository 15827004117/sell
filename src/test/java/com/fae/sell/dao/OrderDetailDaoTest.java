package com.fae.sell.dao;

import com.fae.sell.entity.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailDaoTest {

    @Autowired
    private OrderDetailDao dao;

    @Test
    public void save(){
        OrderDetail detail = new OrderDetail();
        detail.setDetailId("1123456");
        detail.setOrderId("987654321");
        detail.setProductId("cd654");
        detail.setProductName("vivo NEX双屏版");
        detail.setProductPrice(new BigDecimal(4992));
        detail.setProductQuantity(100);
        detail.setProductIcon("http://yyy.jpg");
        OrderDetail result = dao.save(detail);
        Assert.assertNotNull(result);

    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> list = dao.findByOrderId("987654321");
        Assert.assertNotEquals(0,list.size());
    }
}