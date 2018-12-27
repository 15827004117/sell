package com.fae.sell.handler;

import com.fae.sell.enums.ResultEnum;
import com.fae.sell.exception.SellerAuthorizeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述:
 * @参数:
 * @返回:
 * @作者: lj
 * @创建时间: 2018/12/27 8:47
 */
@ControllerAdvice
public class SellExceptionHandler {

    //拦截登录异常
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException() {
        Map<String, Object> map = new HashMap<>();
        map.put("msg", ResultEnum.USER_NO_LOGIN_ERROR.getMessage());
        return new ModelAndView("common/login");
    }
}
