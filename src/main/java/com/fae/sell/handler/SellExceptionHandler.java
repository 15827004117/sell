package com.fae.sell.handler;

import com.fae.sell.VO.ResultVO;
import com.fae.sell.enums.ResultEnum;
import com.fae.sell.exception.SellException;
import com.fae.sell.exception.SellerAuthorizeException;
import com.fae.sell.utils.ResultVoUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述:  异常处理器
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

    //全局异常处理
    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handlerSellException(SellException e) {
        return ResultVoUtil.error(e.getCode(), e.getMessage());
    }
}
