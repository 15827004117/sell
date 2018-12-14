package com.fae.sell.service.impl;

import com.fae.sell.dao.OrderDetailDao;
import com.fae.sell.dao.OrderMasterDao;
import com.fae.sell.dto.CartDTO;
import com.fae.sell.dto.OrderDTO;
import com.fae.sell.entity.OrderDetail;
import com.fae.sell.entity.OrderMaster;
import com.fae.sell.entity.ProductInfo;
import com.fae.sell.enums.OrderStatusEnum;
import com.fae.sell.enums.PayStatusEnum;
import com.fae.sell.enums.ResultEnum;
import com.fae.sell.exception.SellException;
import com.fae.sell.service.OrderService;
import com.fae.sell.service.ProductInfoService;
import com.fae.sell.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 功能描述:
 *
 * @作者: lj
 * @创建时间: 2018/12/14 9:32
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private OrderMasterDao orderMasterDao;

    /**
     * 功能描述:创建订单
     * @参数:
     * @返回:
     * @作者: lj
     * @创建时间: 2018/12/14 13:55
     */
    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        // 定义订单编号
        String orderId = KeyUtil.genUniqueKye();
        // 定义订单总价，默认为0.0
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

        // 1.查询商品
        for (OrderDetail orderDetail: orderDTO.getOrderDetailList()){
            Optional<ProductInfo> productInfo = productInfoService.findById(orderDetail.getProductId());
            // 判断是否有商品
            if(productInfo.isPresent() == false){
                // 没有商品，返回异常，并指定异常信息
                throw new SellException(ResultEnum.PRODUCT_NOT_EXST);
            }

            // 2.计算订单总价 = 每个(商品单价 x 商品数量) + 订单总价
            orderAmount = productInfo.get().getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);

            // 订单详情入库
            orderDetail.setDetailId(KeyUtil.genUniqueKye()); //设置订单商品id
            orderDetail.setOrderId(orderId);    // 设置订单id
            //BeanUtils.copyProperties(productInfo,orderDetail); //设置其他id
            orderDetail.setProductName(productInfo.get().getProductName()); // 商品名称
            orderDetail.setProductPrice(productInfo.get().getProductPrice());
            orderDetail.setProductIcon(productInfo.get().getProductIcon());
            orderDetailDao.save(orderDetail);
        }

        // 3.写入订单数据库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterDao.save(orderMaster);

        // 4.减去库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.decreaseStock(cartDTOList);

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
