package com.shop.entity;

import com.shop.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName UserEntity
 * @Description 用户实体类
 * @Author Jason Biao
 * @Date 2018\12\6 0006 16:16
 * @Version 1.0
 **/
@Getter
@Setter
public class UserEntity extends BaseEntity{
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
}
