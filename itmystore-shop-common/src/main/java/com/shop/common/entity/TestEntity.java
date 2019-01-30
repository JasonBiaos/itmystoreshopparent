package com.shop.common.entity;

import lombok.Getter;
import lombok.Setter;
/**
 * @ClassName TestEntity
 * @Description TODO
 * @Author Jason Biao
 * @Date 2018\12\6 0006 11:59
 * @Version 1.0
 **/
@Getter
@Setter
public class TestEntity extends BaseEntity{

    private String userName;

    private String password;

    private String phone;

    private String email;
}
