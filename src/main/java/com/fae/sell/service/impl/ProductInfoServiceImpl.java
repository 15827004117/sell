package com.fae.sell.service.impl;

import com.fae.sell.dao.ProductInfoDao;
import com.fae.sell.dto.CartDTO;
import com.fae.sell.entity.ProductInfo;
import com.fae.sell.enums.ProductSatatsEnum;
import com.fae.sell.enums.ResultEnum;
import com.fae.sell.exception.SellException;
import com.fae.sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 功能描述: 商品实现类
 *
 * @作者 lj
 * @日期 2018/12/2 0002 21:07
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoDao dao;

    @Override
    public Optional<ProductInfo> findById(String productId) {
        return dao.findById(productId);
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return dao.findAll(pageable);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return dao.findByProductStatus(ProductSatatsEnum.UP.getCode());
    }

    @Override
    public ProductInfo save(ProductInfo productCategory) {
        return dao.save(productCategory);
    }

    /**
     * 功能描述: 加库存
     * @参数:
     * @返回:
     * @作者: lj
     * @创建时间: 2018/12/14 14:19
     */
    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO: cartDTOList){
            Optional<ProductInfo> productInfo = dao.findById(cartDTO.getProductId());
            // 判断是否有商品
            if(productInfo.isPresent() == false){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);   //没有商品
            }
            // 库存 + 购物车订单
            Integer result = productInfo.get().getProductStock() + cartDTO.getProductQuantity();
            // 更新库存
            productInfo.get().setProductStock(result);
            dao.save(productInfo.orElse(null)) ;
        }
    }

    /**
     * 功能描述: 减库存
     * @参数:
     * @返回:
     * @作者: lj
     * @创建时间: 2018/12/14 14:19
     */
    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO: cartDTOList){
            Optional<ProductInfo> productInfo = dao.findById(cartDTO.getProductId());
            // 判断是否有商品
            if(productInfo.isPresent() == false){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);   //没有商品
            }
            // 库存 - 购物车订单
            Integer result = productInfo.get().getProductStock() - cartDTO.getProductQuantity();
            // 判断库存是否小于0
            if(result < 0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);    //库存不足
            }
            // 更新库存
            productInfo.get().setProductStock(result);
            dao.save(productInfo.orElse(null)) ;
        }
    }

    /**
     * 功能描述: 上架商品
     * @参数:
     * @返回:
     * @作者: lj
     * @创建时间: 2018/12/24 11:46
     */
    @Override
    @Transactional
    public ProductInfo onSale(String productId) {

        // 查询商品
        Optional<ProductInfo> productInfo = dao.findById(productId);

        // 判断是否有该订单
        if(!productInfo.isPresent()) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if(productInfo.get().getProductSatatsEnum() == ProductSatatsEnum.UP) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        // 修改商品状态(上架)
        productInfo.get().setProductStatus(ProductSatatsEnum.UP.getCode());

        return dao.save(productInfo.orElse(null));

    }

    /**
     * 功能描述: 下架商品
     * @参数:
     * @返回:
     * @作者: lj
     * @创建时间: 2018/12/24 11:46
     */
    @Override
    @Transactional
    public ProductInfo offSale(String productId) {

        // 查询商品
        Optional<ProductInfo> productInfo = dao.findById(productId);

        // 判断是否有该订单
        if(!productInfo.isPresent()) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if(productInfo.get().getProductSatatsEnum() == ProductSatatsEnum.DOWN) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        // 修改商品状态(上架)
        productInfo.get().setProductStatus(ProductSatatsEnum.DOWN.getCode());

        return dao.save(productInfo.orElse(null));
    }
}
