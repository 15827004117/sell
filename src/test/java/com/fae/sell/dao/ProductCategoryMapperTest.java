package com.fae.sell.dao;

import com.fae.sell.dao.mapper.ProductCategoryMapper;
import com.fae.sell.entity.ProductCategory;
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
 * @创建时间: 2018/12/28 13:06
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper categoryMapper;

    @Test
    public void insertByMap() {
        ProductCategory category = new ProductCategory();
        category.setCategoryName("元旦特惠");
        category.setCategoryType(101);
        int result = categoryMapper.insertByMap(category);
        Assert.assertEquals(1, result);

    }
}