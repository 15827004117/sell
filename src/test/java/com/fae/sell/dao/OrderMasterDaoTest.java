package com.fae.sell.dao;

import com.fae.sell.entity.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
        master.setOrderId("dd201812112010");
        master.setBuyerName("大师兄");
        master.setBuyerPhone("15827001444");
        master.setBuyerAddress("北京市海淀区中关村理想国际大厦22-105");
        master.setOpenId("SW484AJI56");
        master.setOrderAmount(new BigDecimal(21.00));

        OrderMaster result = dao.save(master);
        Assert.assertNotNull(result);

    }

    @Test
    public void findByBuyerOpenId() {



    }
}