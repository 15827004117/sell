package com.fae.sell.service;

import com.fae.sell.entity.SellerInfo;

/**
 * 功能描述: 卖家信息service
 *
 * @作者: lj
 * @创建时间: 2018/12/26 10:17
 */
public interface SellerService {

   SellerInfo findSellerInfoByOpenid(String openid);
}
