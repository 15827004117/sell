package com.fae.sell.enums;

import lombok.Getter;

/**
 * 功能描述:返回前端提示信息枚举
 *
 * @作者: lj
 * @创建时间: 2018/12/14 9:42
 */
@Getter
public enum ResultEnum {

    SUCCESS(0,"成功"),

    PARAM_ERROR(1,"参数不正确"),

    PRODUCT_NOT_EXIST(10,"商品不存在"),

    PRODUCT_STOCK_ERROR(11,"库存不足"),

    ORDER_NOT_EXIST(12,"订单不存在"),

    ORDERDETAIL_NOT_EXIST(13,"订单详情信息不存在"),

    ORDER_STATUS_ERROR(14,"订单状态错误"),

    ORDER_UPDATE_FAIL(15,"订单更新失败"),

    ORDER_DETAIL_EMPTY(16,"订单详情为空"),

    ORDER_PAY_STATUS_ERROR(17,"订单支付状态错误"),

    CART_EMPTY(18,"购物车为空"),

    ORDER_OWNER_ERROR(19,"该订单不属于当前用户"),

    WX_MP_ERROR(20,"微信公众账号错误"),

    WX_NOTIFY_MONEY_VERIFY_ERROR(21,"微信支付异步通知金额校验不通过"),

    ORDER_CANCEL_SUCCESS(22,"取消订单成功"),

    ORDER_FINISH_SUCCESS(23,"完结订单成功"),

    PRODUCT_STATUS_ERROR(24,"商品状态不正确"),

    PRODUCT_STATUS_ON_SUCCESS(25,"商品上架成功"),

    PRODUCT_STATUS_OFF_SUCCESS(26,"商品下架成功"),

    USER_LOGIN_ERROR(27, "用户登陆错误"),

    USER_LOGOUT_SUCCESS(28,"用户登出成功"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
