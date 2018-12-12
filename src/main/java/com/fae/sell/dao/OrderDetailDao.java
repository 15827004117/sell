package com.fae.sell.dao;

import com.fae.sell.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailDao extends JpaRepository<OrderDetail, String> {

    /*根据订单id查询*/
    List<OrderDetail> findByOrderId(String orderId);
}
