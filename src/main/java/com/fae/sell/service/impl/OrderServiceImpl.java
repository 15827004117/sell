package com.fae.sell.service.impl;

import com.fae.sell.converter.OrderMasterToOrderDTOConverter;
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
import com.fae.sell.service.PayService;
import com.fae.sell.service.ProductInfoService;
import com.fae.sell.service.PushMessageService;
import com.fae.sell.utils.KeyUtil;
import com.fae.sell.webscoker.WebSocket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductInfoService productInfoService; //商品

    @Autowired
    private OrderDetailDao orderDetailDao;  //订单商品

    @Autowired
    private OrderMasterDao orderMasterDao;  // 订单

    @Autowired
    private PayService payService; //支付

    @Autowired
    private PushMessageService pushMessageService; //微信模板推送

    @Autowired
    private WebSocket webSocket; //webSocket发送消息

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
            ProductInfo productInfo = productInfoService.findById(orderDetail.getProductId());
            // 判断是否有商品
            if(productInfo == null){
                // 没有商品，返回异常，并指定异常信息
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            // 2.计算订单总价 = 每个(商品单价 x 商品数量) + 订单总价
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);

            // 订单详情入库
            orderDetail.setDetailId(KeyUtil.genUniqueKye()); //设置订单商品id
            orderDetail.setOrderId(orderId);    // 设置订单id
            BeanUtils.copyProperties(productInfo,orderDetail); //设置其他

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

        // 5.发送websocket消息
        webSocket.sendMessage("您有新的订单了");

        return orderDTO;
    }

    /**
     * 功能描述: 查询单个订单
     * @参数:
     * @返回:
     * @作者: lj
     * @创建时间: 2018/12/17 11:41
     */
    @Override
    @Transactional
    public OrderDTO findOne(String orderId) {
        // 查询订单详情信息
        OrderMaster orderMaster = orderMasterDao.getOne(orderId);
        // 没有订单，返回异常，并指定异常信息
        if(orderMaster == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        // 查询订单商品详情
        List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId(orderId);
        // 没有商品信息，返回异常，并指定异常信息
        if(CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        // 数据封装
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

    /**
     * 功能描述: 查询订单列表(根据openid查询)
     * @参数:
     * @返回:
     * @作者: lj
     * @创建时间: 2018/12/17 11:41
     */
    @Override
    @Transactional
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        // 查询订单列表
        Page<OrderMaster> orderMasterPage = orderMasterDao.findByBuyerOpenid(buyerOpenid, pageable);
        // 数据封装
        List<OrderDTO> orderDTOList = OrderMasterToOrderDTOConverter.convert(orderMasterPage.getContent());
        Page<OrderDTO> orderDTOPage = new PageImpl<OrderDTO>(orderDTOList, pageable, orderMasterPage.getTotalElements());

        return orderDTOPage;
    }

    /**
     * 功能描述: 查询订单列表(查全部)
     * @参数:
     * @返回:
     * @作者: lj
     * @创建时间: 2018/12/23 17:19
     */
    @Override
    @Transactional
    public Page<OrderDTO> findList(Pageable pageable) {
        // 查询订单列表
        Page<OrderMaster> orderMasterPage = orderMasterDao.findAll(pageable);
        // 数据封装
        List<OrderDTO> orderDTOList = OrderMasterToOrderDTOConverter.convert(orderMasterPage.getContent());
        Page<OrderDTO> orderDTOPage = new PageImpl<OrderDTO>(orderDTOList, pageable, orderMasterPage.getTotalElements());

        return orderDTOPage;
    }

    /**
     * 功能描述: 取消订单
     * @参数:
     * @返回:
     * @作者: lj
     * @创建时间: 2018/12/17 11:42
     */
    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();

        // 判断订单状态
        // 判断订单状态为新订单才能进行取消操作
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【取消订单】 订单状态不正确 orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        // 修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster result = orderMasterDao.save(orderMaster);
        if(result == null) {
            log.error("【取消订单】 更新失败 orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        // 返还库存
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【取消订单】 订单详情为空 orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.increaseStock(cartDTOList);

        // 如已支付，需退款
        if(orderDTO.getOrderStatus().equals(PayStatusEnum.SUCCESS)) {
            payService.refund(orderDTO);
        }
        return orderDTO;
    }

    /**
     * 功能描述: 完结订单
     * @参数:
     * @返回:
     * @作者: lj
     * @创建时间: 2018/12/17 14:03
     */
    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        // 判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【完结订单】 订单状态错误 orderId={} orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        // 修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster result = orderMasterDao.save(orderMaster);
        if(result == null) {
            log.error("【完结订单】 订单更新失败 orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        //微信推送模板消息
        try {
            pushMessageService.orderStatus(orderDTO);
        } catch (Exception e) {
            log.error("【微信推送模板消息】 推送失败", e.getMessage());
        }

        return orderDTO;
    }

    /**
     * 功能描述: 订单支付成功
     * @参数:
     * @返回:
     * @作者: lj
     * @创建时间: 2018/12/17 14:39
     */
    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        // 判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【订单支付成功】 订单状态错误 orderId={} orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        // 判断支付状态
        if(!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
            log.error("【订单支付成功】 订单支付状态错误 orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }

        // 修改支付状态
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster result = orderMasterDao.save(orderMaster);
        if(result == null) {
            log.error("【订单支付成功】 订单更新失败 orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }
}
