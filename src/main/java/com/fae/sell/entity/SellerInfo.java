package com.fae.sell.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * 功能描述:
 *
 * @作者 lj
 * @日期 2018/12/25 0025 21:36
 */
@Entity
@Data
public class SellerInfo {

    @Id
    private String id;  //id

    private String username;    //用户名

    private String password;    //密码

    private String openid;  //微信openId

    private Date createTime;    //创建时间

    private Date updateTime;    //更新时间
}
