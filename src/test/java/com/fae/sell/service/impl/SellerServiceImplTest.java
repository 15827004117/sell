package com.fae.sell.service.impl;

import com.fae.sell.entity.SellerInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * 功能描述:
 *
 * @作者: lj
 * @创建时间: 2018/12/26 10:38
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerServiceImplTest {

    @Autowired
    private SellerServiceImpl sellerService;

    @Test
    public void findSellerInfoByOpenid() {

        SellerInfo result = sellerService.findSellerInfoByOpenid("wx7569s45a45fd");
        Assert.assertEquals("wx7569s45a45fd", result.getOpenid());

    }
}