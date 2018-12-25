package com.fae.sell.form;

import lombok.Data;

/**
 * 功能描述:
 *
 * @作者: lj
 * @创建时间: 2018/12/25 17:22
 */
@Data
public class CategoryForm {

    private Integer categoryId; //类目id

    private String categoryName;    //类目名称

    private Integer categoryType; //类目编号
}
