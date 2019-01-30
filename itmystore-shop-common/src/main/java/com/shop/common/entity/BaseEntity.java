package com.shop.common.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;

/**
 * @ClassName BaseEntity
 * @Description 基础实体类的封装(基类:封装一些相同的字段和属性 )
 * @Author Jason Biao
 * @Date 2018\12\5 0005 16:07
 * @Version 1.0
 **/
@Getter
@Setter
public class BaseEntity {

    /**
     * 主键
     */
    private Long id;
    /**
     * 创建时间
     */
    private Timestamp created;
    /**
     * 更新时间
     */
    private Timestamp updated;

}
