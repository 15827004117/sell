package com.fae.sell.service;

import com.fae.sell.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * 商品service
 */
public interface ProductInfoService {

    //根据id查询
    Optional<ProductInfo> findById(String productId);

    //查询所有
    Page<ProductInfo> findAll(Pageable pageable);

    //查询上架商品
    List<ProductInfo> findUpAll();

    //新增、更新
    ProductInfo save(ProductInfo productCategory);

    //加库存

    //减库存
}