package com.fae.sell.service.impl;

import com.fae.sell.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {

    @Autowired
    private ProductCategoryServiceImpl service;

    @Test
    public void findById() {
        Optional<ProductCategory> category = service.findById(1);
        System.out.println(category.get().getCategoryId());
    }

    @Test
    public void findAll() {
        List<ProductCategory> list = service.findAll();
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> type = service.findByCategoryTypeIn(Arrays.asList(1, 2, 3, 4));
        Assert.assertNotEquals(0,type.size());
    }

    @Test
    public void save() {
        ProductCategory category = new ProductCategory("最新最热",5);
        ProductCategory result = service.save(category);
        Assert.assertNotNull(result);
    }
}