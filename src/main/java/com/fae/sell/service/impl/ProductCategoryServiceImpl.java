package com.fae.sell.service.impl;

import com.fae.sell.dao.ProductCategoryDao;
import com.fae.sell.entity.ProductCategory;
import com.fae.sell.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 功能描述:商品类目实现类
 *
 * @作者 lj
 * @日期 2018/12/2 0002 14:20
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryDao dao;


    @Override
    public Optional<ProductCategory> findById(Integer categoryId) {
        return dao.findById(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return dao.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return dao.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return dao.save(productCategory);
    }
}
