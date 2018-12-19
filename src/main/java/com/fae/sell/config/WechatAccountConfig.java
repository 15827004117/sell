package com.fae.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 功能描述:
 *
 * @作者: lj
 * @创建时间: 2018/12/18 15:28
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {

    private String MpAppID; //appId

    private String MpAppSecret;

    private String mchId; //用户号

    private String mchKey;  //商户密钥

    private String keyPath; //商户证书路径

    private String notifyUrl; //微信支付通知回调地址
}
