package com.fae.sell.dao;

import com.fae.sell.entity.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDaoTest {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    public void findTest(){
        Optional<ProductCategory> category = productCategoryDao.findById(1);
        System.out.println(category);
    }

    @Test
    public void addTest(){
        ProductCategory category = new ProductCategory();
        category.setCategoryName("女生最爱");
        category.setCategoryType(4);
        productCategoryDao.save(category);

    }

    @Test
    public void updateTest(){
        ProductCategory category = new ProductCategory();
        category.setCategoryId(3);
        category.setCategoryType(10);
        category.setCategoryName("双十一特惠");
        productCategoryDao.save(category);

    }

}