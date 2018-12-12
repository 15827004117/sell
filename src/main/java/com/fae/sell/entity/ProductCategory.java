package com.fae.sell.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 功能描述:商品类目实体类
 *
 * @作者 lj
 * @日期 $2018/12/1 0001 13:58
 */
@Entity //自动扫描实体类注解
@DynamicUpdate  //自动更新时间
@Data   //自动创建get/set/toString方法
public class ProductCategory {

    @Id //主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增主键
    private Integer categoryId; //类目id

    private String categoryName;    //类目名称

    private Integer categoryType; //类目编号

    private Date createTime;    //创建时间

    private Date updateTime;    //更新时间

    public ProductCategory(){

    }


    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
