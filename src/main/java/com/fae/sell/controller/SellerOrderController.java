package com.fae.sell.controller;

import com.fae.sell.dto.OrderDTO;
import com.fae.sell.enums.ResultEnum;
import com.fae.sell.exception.SellException;
import com.fae.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 功能描述: 卖家端订单controller
 *
 * @作者 lj
 * @日期 2018/12/23 0023 17:27
 */
@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {

    @Autowired
    private OrderService orderService; //订单

    /**
     * 功能描述: 订单列表
     *
     * @param page 从第几页开始
     * @param size 每页显示条数
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "5") Integer size,
                             Map<String, Object> map) {
        // 查询订单
        Page<OrderDTO> orderDTOPage = orderService.findList(PageRequest.of(page - 1, size));
        // 封装数据
        map.put("orderDTOPage", orderDTOPage);
        map.put("curPage",page);

        // 返回
        return new ModelAndView("order/list", map);
    }

    /**
     * 功能描述: 取消订单
     * @参数:
     * @返回:
     * @作者: lj
     * @创建时间: 2018/12/24 8:57
     */
    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId,
                               Map<String, Object> map) {
        // 查询订单
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            // 取消订单
            orderService.cancel(orderDTO);
        } catch (SellException e) {
            // 订单不存在
            log.error("【卖家取消订单】 发生异常 {}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            // 错误返回
            return new ModelAndView("common/error", map);
        }

        map.put("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
        map.put("url", "/sell/seller/order/list");
        // 成功返回
        return new ModelAndView("common/success", map);
    }

    /**
     * 功能描述: 查看订单详情
     * @参数: orderId
     * @返回:
     * @作者: lj
     * @创建时间: 2018/12/24 8:56
     */
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId,
                               Map<String, Object> map) {
        // 根据id查询订单
        OrderDTO orderDTO = null;
        try {
            orderDTO = orderService.findOne(orderId);
        } catch (Exception e) {
            // 订单不存在
            log.error("【查看订单详情】 发生异常 {}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            // 错误返回
            return new ModelAndView("common/error", map);
        }

        map.put("orderDTO", orderDTO);

        return new ModelAndView("order/detail", map);
    }

    /**
     * 功能描述: 完结订单
     * @参数: orderId
     * @返回:
     * @作者: lj
     * @创建时间: 2018/12/24 9:42
     */
    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId") String orderId,
                               Map<String, Object> map) {
        // 查询订单
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            // 完结订单
            orderService.finish(orderDTO);
        } catch (SellException e) {
            // 订单不存在
            log.error("【卖家完结订单】 发生异常 {}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            // 错误返回
            return new ModelAndView("common/error", map);
        }

        map.put("msg", ResultEnum.ORDER_FINISH_SUCCESS.getMessage());
        map.put("url", "/sell/seller/order/list");
        // 成功返回
        return new ModelAndView("common/success", map);
    }
}
