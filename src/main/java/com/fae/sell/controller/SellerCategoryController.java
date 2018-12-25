package com.fae.sell.controller;

import com.fae.sell.entity.ProductCategory;
import com.fae.sell.enums.ResultEnum;
import com.fae.sell.form.CategoryForm;
import com.fae.sell.service.ProductCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 功能描述: 卖家类目controller
 *
 * @作者: lj
 * @创建时间: 2018/12/25 14:28
 */
@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {

    @Autowired
    private ProductCategoryService categoryService;

    /**
     * 功能描述: 类目列表
     * @参数:
     * @返回:
     * @作者: lj
     * @创建时间: 2018/12/25 17:15
     */
    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> map) {
        // 查询订单
        List<ProductCategory> categoryList = categoryService.findAll();
        // 封装数据
        map.put("categoryList", categoryList);

        // 返回
        return new ModelAndView("category/list", map);
    }

    /**
     * 功能描述: 修改/查看类目详情
     * @参数:
     * @返回:
     * @作者: lj
     * @创建时间: 2018/12/25 17:15
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId", required = false)Integer categoryId,
                              Map<String, Object> map) {
        if (categoryId != null) {
            ProductCategory productCategory = categoryService.findById(categoryId);
            map.put("productCategory", productCategory);
        }

        return new ModelAndView("category/index", map);
    }

    /**
     * 功能描述: 新增/修改类目
     * @参数:
     * @返回:
     * @作者: lj
     * @创建时间: 2018/12/25 17:16
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm categoryForm,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        // 判断，参数不正确抛异常
        if(bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/category/index");
            return new ModelAndView("common/error");
        }

        ProductCategory productCategory = new ProductCategory();
        try {
            //如果categoryId不为空，表示是修改操作
            if(categoryForm.getCategoryId() != null) {
                // 查询类目信息
                productCategory = categoryService.findById(categoryForm.getCategoryId());
            }
            // 拷贝
            BeanUtils.copyProperties(categoryForm, productCategory);
            // 执行更新操作
            categoryService.save(productCategory);
        } catch (Exception e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/category/index");
            return new ModelAndView("common/error");
        }
        // 成功返回
        map.put("msg", ResultEnum.SUCCESS.getMessage());
        map.put("url", "/sell/seller/category/list");
        return new ModelAndView("common/success");
    }
}
