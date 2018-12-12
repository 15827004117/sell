package com.fae.sell.service;

import com.fae.sell.entity.ProductCategory;

import java.util.List;
import java.util.Optional;

/**
 * 商品类目service
 */
public interface ProductCategoryService {

    //根据id查询
    Optional<ProductCategory> findById(Integer categoryId);

    //查询所有
    List<ProductCategory> findAll();

    //根据类目编号查询
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    //新增、更新
    ProductCategory save(ProductCategory productCategory);
}
