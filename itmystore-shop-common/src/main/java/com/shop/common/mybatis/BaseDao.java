package com.shop.common.mybatis;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;

public interface BaseDao {

    /**
     *@InsertProvider :自定义新增sql语句
     */
    @InsertProvider(type =BaseProvider.class ,method = "save")
    public void save(@Param("oj") Object oj,@Param("table") String table);
}
