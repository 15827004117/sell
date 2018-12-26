package com.fae.sell.controller;

import com.fae.sell.config.RedisConfig;
import com.fae.sell.entity.SellerInfo;
import com.fae.sell.enums.ResultEnum;
import com.fae.sell.service.SellerService;
import com.fae.sell.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述: 卖家登陆controller
 *
 * @作者: lj
 * @创建时间: 2018/12/26 13:43
 */
@Controller
@RequestMapping("/seller")
public class SellerUserController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisConfig redisConfig;

    /**
     * 功能描述: 用户登陆
     * @参数:
     * @返回:
     * @作者: lj
     * @创建时间: 2018/12/26 13:46
     */
    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openid,
                              Map<String, Object> map,
                              HttpServletResponse response) {
        // 1.openid去和数据里比对
        SellerInfo info = sellerService.findSellerInfoByOpenid(openid);
        // 查找不到该openid
        if(info == null) {
            map.put("msg", ResultEnum.USER_LOGIN_ERROR.getMessage());
            return new ModelAndView("common/login");
        }

        // 2.设置token至redis
        String token = UUID.randomUUID().toString();
        Integer expire = redisConfig.getExpire();
        stringRedisTemplate.opsForValue().set(String.format(redisConfig.getTOKEN_PREFIX(), token), //key
                openid, //value
                expire, //过期时间
                TimeUnit.SECONDS); //时间单位

        // 3.设置token至cookie
        CookieUtil.set(response,"token", token, expire);

        return new ModelAndView("redirect:"+ "http://localhost:8080/sell/seller/order/list");
    }

    /**
     * 功能描述: 用户登出
     * @参数:
     * @返回:
     * @作者: lj
     * @创建时间: 2018/12/26 13:46
     */
    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Map<String, Object> map) {
        // 1.从cookie里查询
        Cookie cookie = CookieUtil.get(request,"token");
        if(cookie != null) {
            // 2.清除redis
            stringRedisTemplate.opsForValue().getOperations()
                    .delete(String.format(redisConfig.getTOKEN_PREFIX(), cookie.getValue()));
            // 3.清除cookie
            CookieUtil.set(response,"token",null,0);
        }

        map.put("msg", ResultEnum.USER_LOGOUT_SUCCESS.getMessage());
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("common/success",map);
    }
}
