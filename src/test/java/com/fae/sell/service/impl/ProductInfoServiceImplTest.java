package com.fae.sell.service.impl;

import com.fae.sell.entity.ProductInfo;
import com.fae.sell.enums.ProductSatatsEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoServiceImpl service;

    @Test
    public void findById() {
        Optional<ProductInfo> info = service.findById("ls201812022115001");
        System.out.println(info);

    }

    @Test
    public void findAll() {
        Page<ProductInfo> list = service.findAll(PageRequest.of(0,2));
        System.out.println(list.getTotalElements());
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> list = service.findUpAll();
        Assert.assertNotEquals(0,list.size());
    }


    @Test
    public void save() {
        ProductInfo info = new ProductInfo();
        info.setProductId("ls201812032120003");
        info.setProductName("Macbook Air");
        info.setProductPrice(new BigDecimal(8888.8));
        info.setProductStock(30);
        info.setProductIcon("http://images.macx.cn/forum/201611/07/134433jngccejjgnmdtoni.jpg");
        info.setProductStatus(0);
        info.setCategoryType(10);

        ProductInfo result = service.save(info);
        Assert.assertNotNull(result);

    }

    @Test
    public void onSale() {
        ProductInfo result = service.onSale("ls201812022115001");
        Assert.assertEquals(ProductSatatsEnum.UP,result.getProductSatatsEnum());
    }

    @Test
    public void offSale() {
        ProductInfo result = service.offSale("ls201812022115001");
        Assert.assertEquals(ProductSatatsEnum.DOWN,result.getProductSatatsEnum());
    }
}