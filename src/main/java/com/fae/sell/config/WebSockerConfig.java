package com.fae.sell.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * 功能描述:
 *
 * @作者: lj
 * @创建时间: 2018/12/27 16:28
 */
@Component
public class WebSockerConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
