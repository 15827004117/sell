package com.fae.sell.dao;

import com.fae.sell.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterDao extends JpaRepository<OrderMaster, String> {

    /*根据买家openId查询订单(分页)*/
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
