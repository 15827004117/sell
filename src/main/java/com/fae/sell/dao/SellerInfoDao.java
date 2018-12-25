package com.fae.sell.dao;

import com.fae.sell.entity.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 卖家信息接口
 */
public interface SellerInfoDao extends JpaRepository<SellerInfo, String> {

    SellerInfo findByOpenid(String openid);
}
