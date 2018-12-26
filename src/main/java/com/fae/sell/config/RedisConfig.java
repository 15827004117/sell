package com.fae.sell.config;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 功能描述: redis配置
 *
 * @作者: lj
 * @创建时间: 2018/12/26 15:38
 */
@Data
@Component
public class RedisConfig {

    private String TOKEN_PREFIX = "token_%s";

    private int expire = 7200; //两小时
}
