package com.fae.sell.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * 功能描述: 用于接受前台表单验证实体类
 *
 * @作者 lj
 * @日期 2018/12/14 0014 20:41
 */
@Data
public class OrderForm {

    @NotBlank(message = "姓名必填")
    private String name; //买家姓名

    @NotEmpty(message = "联系电话必填")
    private String phone; //买家电话

    @NotBlank(message = "地址必填")
    private String address; //买家地址

    @NotEmpty(message = "openid必填")
    private String openid; //买家微信openid

    @NotEmpty(message = "购物车不能为空")
    private String items; //购物车

}
