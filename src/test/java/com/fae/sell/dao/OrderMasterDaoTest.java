package com.fae.sell.dao;

import com.fae.sell.entity.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterDaoTest {

    @Autowired
    private OrderMasterDao dao;

    @Test
    public void save(){
        OrderMaster master = new OrderMaster();
        master.setOrderId("ll201812112010");
        master.setBuyerName("大师兄");
        master.setBuyerPhone("15921234567");
        master.setBuyerAddress("北京市海淀区中关村理想大厦");
        master.setBuyerOpenid("123456");
        master.setOrderAmount(new BigDecimal(9.9));

        OrderMaster result = dao.save(master);
        Assert.assertNotNull(result);

    }

    @Test
    public void findByBuyerOpenId() {
        Page<OrderMaster> result = dao.findByBuyerOpenid("456789",PageRequest.of(0,3));
        Assert.assertNotEquals(0,result.getTotalElements());

    }
}