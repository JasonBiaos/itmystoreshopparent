package com.shop.common.mybatis;

import com.shop.utils.ReflectionUtils;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * @ClassName BaseProvider
 * @Description 封装自定义sql语句类
 * @Author Jason Biao
 * @Date 2018\12\6 0006 11:18
 * @Version 1.0
 **/
public class BaseProvider {

    /**
     *新增数据
     * @param map
     * @return
     */
    public String save(Map<String,Object> map){
        //获取实体类
        Object oj = map.get("oj");
        //获取表的名称
        String table = (String)map.get("table");
        /**
         * 生成添加的SQL语句，使用反射机制!
         * 步骤：
         */
        //使用反射机制加载这个类的所有属性和值并将他们拼装成sql
        SQL sql = new SQL(){
            {
                INSERT_INTO(table);
                VALUES(ReflectionUtils.fatherAndSonField(oj),ReflectionUtils.fatherAndSonFieldValue(oj));
            }
        };

        return sql.toString();
    }
}
