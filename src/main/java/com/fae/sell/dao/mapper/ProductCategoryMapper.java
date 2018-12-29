package com.fae.sell.dao.mapper;

import com.fae.sell.entity.ProductCategory;
import org.apache.ibatis.annotations.*;


/**
 * 功能描述:
 *
 * @作者: lj
 * @创建时间: 2018/12/28 12:20
 */
public interface ProductCategoryMapper {

    /*添加*/
    @Insert("insert into product_category(category_name, category_type) values (#{categoryName, jdbcType=VARCHAR}, #{categoryType jdbcType=INTEGER})")
    int insertByMap(ProductCategory productCategory);

    /*根据类型查询*/
    @Select("select * from product_category where category_type = #{categoryType}")
    @Results({
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_type", property = "categoryType"),
            @Result(column = "category_name", property = "categoryName")
    })
    ProductCategory findByCategoryType(Integer categoryType);

    @Update("update product_category set category_id = ${categoryId}")
    int update(Integer categoryId);
}
