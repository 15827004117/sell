package com.fae.sell.VO;

import lombok.Data;

/**
 * 功能描述: 返回前端数据对象
 *
 * @作者 lj
 * @日期 2018/12/4 0004 19:40
 */
@Data
public class ResultVO<T> {

    private Integer code;   //状态码

    private String msg; //提示信息

    private T data; //返回的数据
}
