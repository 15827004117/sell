package com.fae.sell.controller;

import com.fae.sell.entity.ProductCategory;
import com.fae.sell.entity.ProductInfo;
import com.fae.sell.enums.ResultEnum;
import com.fae.sell.service.ProductCategoryService;
import com.fae.sell.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 功能描述: 卖家端商品controller
 *
 * @作者: lj
 * @创建时间: 2018/12/24 10:44
 */
@Controller
@RequestMapping("/seller/product")
@Slf4j
public class SellerProductController {

    @Autowired
    private ProductInfoService productInfoService; //商品

    @Autowired
    private ProductCategoryService categoryService; //类目

    /**
     * 功能描述: 订单列表
     * @param page 从第几页开始
     * @param size 每页显示条数
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "5") Integer size,
                             Map<String, Object> map) {
        // 查询商品
        Page<ProductInfo> productInfoPage = productInfoService.findAll(PageRequest.of(page - 1, size));
        // 封装数据
        map.put("productInfoPage", productInfoPage);
        map.put("curPage",page);
        // 返回
        return new ModelAndView("product/list", map);
    }

    /**
     * 功能描述: 商品上架
     * @参数:
     * @返回: 
     * @作者: lj
     * @创建时间: 2018/12/24 13:56
     */
    @GetMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId,
                               Map<String, Object> map) {
        // 商品上架
        try {
            productInfoService.onSale(productId);
        } catch (Exception e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }
        // 返回
        map.put("msg", ResultEnum.PRODUCT_STATUS_ON_SUCCESS.getMessage());
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }

    /**
     * 功能描述: 商品下架
     * @参数:
     * @返回:
     * @作者: lj
     * @创建时间: 2018/12/24 13:56
     */
    @GetMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId,
                               Map<String, Object> map) {
        // 商品下架
        try {
            productInfoService.offSale(productId);
        } catch (Exception e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }
        // 返回
        map.put("msg", ResultEnum.PRODUCT_STATUS_OFF_SUCCESS.getMessage());
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success");
    }

    /**
     * 功能描述:修改订单详情
     * @参数:
     * @返回:
     * @作者: lj
     * @创建时间: 2018/12/24 16:37
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false)  String productId,
                              Map<String, Object> map) {
        // 判断传值是否为空
        if (!StringUtils.isEmpty(productId)) {
            Optional<ProductInfo> productInfo = productInfoService.findById(productId);
            map.put("productInfo", productInfo);
        }

        // 查询所有类目
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList", categoryList);

        return new ModelAndView("product/index", map);

    }
}
