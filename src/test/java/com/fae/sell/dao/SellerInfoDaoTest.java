package com.fae.sell.dao;

import com.fae.sell.entity.SellerInfo;
import com.fae.sell.utils.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoDaoTest {

    @Autowired
    private SellerInfoDao dao;

    @Test
    public void save() {
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setId(KeyUtil.genUniqueKye());
        sellerInfo.setOpenid("wx7569s45a45fd");
        sellerInfo.setUsername("小胖");
        sellerInfo.setPassword("123456");

        SellerInfo info = dao.save(sellerInfo);
        Assert.assertNotNull(info);
    }

    @Test
    public void findByOpenid() {
        SellerInfo sellerInfo = dao.findByOpenid("wx7569s45a45fd");
        Assert.assertEquals("wx7569s45a45fd", sellerInfo.getOpenid());

    }
}