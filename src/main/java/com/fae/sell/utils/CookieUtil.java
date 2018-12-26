package com.fae.sell.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述: cookie工具类
 *
 * @作者: lj
 * @创建时间: 2018/12/26 15:50
 */
public class CookieUtil {

    /**
     * 功能描述: 设置cookie
     * @参数:
     * @返回:
     * @作者: lj
     * @创建时间: 2018/12/26 15:52
     */
    public static void set(HttpServletResponse response,
                           String name,
                           String value,
                           Integer maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    /**
     * 功能描述: 获得cookie
     * @参数:
     * @返回:
     * @作者: lj
     * @创建时间: 2018/12/26 15:53
     */
    public static Cookie get(HttpServletRequest request, String name) {
        Map<String, Cookie> cookieMap = readCookieMap(request);
        if(cookieMap.containsKey(name)) {
            return cookieMap.get(name);
        }else {
            return null;
        }
    }

    /**
     * 功能描述: cookie[] 转换成 map
     * @参数:
     * @返回:
     * @作者: lj
     * @创建时间: 2018/12/26 17:10
     */
    public static Map<String,Cookie> readCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<>();
        Cookie [] cookies = request.getCookies();
        if(cookies != null) {
            for (Cookie cookie: cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }
}
