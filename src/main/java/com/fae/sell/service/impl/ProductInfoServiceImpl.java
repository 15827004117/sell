package com.fae.sell.service.impl;

import com.fae.sell.dao.ProductInfoDao;
import com.fae.sell.entity.ProductInfo;
import com.fae.sell.enums.ProductSatatsEnum;
import com.fae.sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 功能描述: 商品实现类
 *
 * @作者 lj
 * @日期 2018/12/2 0002 21:07
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoDao dao;

    @Override
    public Optional<ProductInfo> findById(String productId) {
        return dao.findById(productId);
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return dao.findAll(pageable);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return dao.findByProductStatus(ProductSatatsEnum.UP.getCode());
    }

    @Override
    public ProductInfo save(ProductInfo productCategory) {
        return dao.save(productCategory);
    }
}
