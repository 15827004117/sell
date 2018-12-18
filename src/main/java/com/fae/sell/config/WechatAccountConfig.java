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

    private String MpAppID;

    private String MpAppSecret;
}
