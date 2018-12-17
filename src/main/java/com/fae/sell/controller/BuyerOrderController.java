package com.fae.sell.controller;

import com.fae.sell.VO.ResultVO;
import com.fae.sell.converter.OrderFormToOrderDTO;
import com.fae.sell.dto.OrderDTO;
import com.fae.sell.enums.ResultEnum;
import com.fae.sell.exception.SellException;
import com.fae.sell.form.OrderForm;
import com.fae.sell.service.OrderService;
import com.fae.sell.utils.ResultVoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述: 买家订单controller
 *
 * @作者 lj
 * @日期 2018/12/14 0014 20:27
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService; //订单

    /**
     * 功能描述: 创建订单
     * @参数: orderForm
     * @返回: success
     * @作者: lj
     * @创建时间: 2018/12/17 16:37
     */
    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm,
                                               BindingResult bindingResult){
        // 判断，参数不正确抛异常
        if(bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确 orderFrom = {}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        // 数据封装
        OrderDTO orderDTO = OrderFormToOrderDTO.convert(orderForm);
        // 判断是否为空
        if(orderDTO == null) {
            log.error("【创建订单】 购物车为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        // 执行数据库操作
        OrderDTO createResult = orderService.create(orderDTO);
        //返回值数据封装
        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());

        return ResultVoUtil.success(map);
    }

    /**
     * 功能描述: 订单列表
     * @参数: page:默认为0，size默认为10
     * @返回: 订单列表
     * @作者: lj
     * @创建时间: 2018/12/17 16:37
     */
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam(value = "openid") String openId,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size) {
        // 判断openid不能为空
        if(StringUtils.isEmpty(openId)) {
            log.error("【订单列表查询】 openId为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        // 数据库查询，分页
        Page<OrderDTO> orderDTOPage = orderService.findList(openId, PageRequest.of(page, size));

        return ResultVoUtil.success(orderDTOPage.getContent());
    }
    // 订单详情

    // 取消订单


}
