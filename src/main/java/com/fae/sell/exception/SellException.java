package com.fae.sell.exception;

import com.fae.sell.enums.ResultEnum;

/**
 * 功能描述:全局异常
 *
 * @作者: lj
 * @创建时间: 2018/12/14 9:44
 */
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum){

        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message){

        super(message);
        this.code = code;
    }
}
