package com.fae.sell.config;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 功能描述: 微信支付配置
 *
 * @作者: lj
 * @创建时间: 2018/12/19 11:02
 */
@Component
public class WechatPayConfig {

    @Autowired
    private WechatAccountConfig wechatAccountConfig;

    @Bean
    public BestPayServiceImpl bestPayService() {
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayH5Config(wxPayH5Config());

        return bestPayService;
    }

    @Bean
    public WxPayH5Config wxPayH5Config() {
        WxPayH5Config wxPayH5Config = new WxPayH5Config();
        wxPayH5Config.setAppId(wechatAccountConfig.getMpAppID());
        wxPayH5Config.setAppSecret(wechatAccountConfig.getMpAppSecret());
        wxPayH5Config.setMchId(wechatAccountConfig.getMchId());
        wxPayH5Config.setKeyPath(wechatAccountConfig.getKeyPath());
        wxPayH5Config.setMchKey(wechatAccountConfig.getMchKey());
        wxPayH5Config.setNotifyUrl(wechatAccountConfig.getNotifyUrl());

        return wxPayH5Config;
    }
}
