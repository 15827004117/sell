package com.fae.sell.service;

import com.fae.sell.dto.CartDTO;
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
    ProductInfo findById(String productId);

    //查询所有
    Page<ProductInfo> findAll(Pageable pageable);

    //查询上架商品
    List<ProductInfo> findUpAll();

    //新增、更新
    ProductInfo save(ProductInfo productCategory);

    //加库存
    void increaseStock(List<CartDTO> cartDTOList);

    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);

    //上架
    ProductInfo onSale(String productId);

    //下架
    ProductInfo offSale(String productId);
}
