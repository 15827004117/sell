package com.fae.sell.controller;

import com.fae.sell.VO.ResultVO;
import com.fae.sell.exception.SellException;
import com.fae.sell.form.OrderForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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

    /**
     * 创建订单
     * @return
     */
    @RequestMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm,
                                               BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确 orderFrom = {}", orderForm);
        }
      return null;
    }

    // 订单列表

    // 订单详情

    // 取消订单


}
