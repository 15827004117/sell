package com.fae.sell.service.impl;

import com.fae.sell.dao.SellerInfoDao;
import com.fae.sell.entity.SellerInfo;
import com.fae.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 功能描述: 卖家信息实现类
 *
 * @作者: lj
 * @创建时间: 2018/12/26 10:25
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoDao sellerInfoDao;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {

        return sellerInfoDao.findByOpenid(openid);
    }

}
