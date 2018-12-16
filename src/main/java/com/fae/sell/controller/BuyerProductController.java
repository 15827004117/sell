package com.fae.sell.controller;

import com.fae.sell.VO.ProductInfoVO;
import com.fae.sell.VO.ProductVO;
import com.fae.sell.VO.ResultVO;
import com.fae.sell.entity.ProductCategory;
import com.fae.sell.entity.ProductInfo;
import com.fae.sell.service.ProductCategoryService;
import com.fae.sell.service.ProductInfoService;
import com.fae.sell.utils.ResultVoUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 功能描述: 买家商品controller
 *
 * @作者 lj
 * @日期 2018/12/4 0004 19:35
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoService productInfoService;  //商品

    @Autowired
    private ProductCategoryService productCategoryService; //类目

    /*查看上架商品及其类目*/
    @GetMapping("/list")
    public ResultVO list(){
        // 1.查询所有上架商品
        List<ProductInfo> productInfoList = productInfoService.findUpAll();
        // 2.查询商品类目
        List<Integer> categoryTypeList = productInfoList
                .stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(categoryTypeList);
        // 3.数据封装
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return ResultVoUtil.success(productVOList);

    }
}
