package com.fae.sell.converter;

import com.fae.sell.dto.OrderDTO;
import com.fae.sell.entity.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 功能描述: orderDTO转换工具
 *
 * @作者: lj
 * @创建时间: 2018/12/17 10:07
 */
public class OrderMasterToOrderDTOConverter {

    public static OrderDTO convert(OrderMaster orderMaster) {

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList) {

        return orderMasterList.stream().map(e -> convert(e))
                .collect(Collectors.toList());
    }
}
