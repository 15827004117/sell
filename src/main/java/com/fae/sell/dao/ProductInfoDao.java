package com.fae.sell.dao;

import com.fae.sell.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 功能描述:
 *
 * @作者 lj
 * @日期 2018/12/2 0002 20:57
 */
public interface ProductInfoDao extends JpaRepository<ProductInfo,String> {

    List<ProductInfo> findByProductStatus(Integer productStatus);

}
