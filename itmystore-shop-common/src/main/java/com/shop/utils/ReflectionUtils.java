package com.shop.utils;

import com.shop.common.entity.TestEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.SQL;

import java.lang.reflect.Field;
import java.sql.Timestamp;

/**
 * @ClassName ReflectionUtils
 * @Description 反射工具类
 * @Author Jason Biao
 * @Date 2018\12\6 0006 11:34
 * @Version 1.0
 **/
@Slf4j
public class ReflectionUtils {

//    /**
//     * 获取类的属性，将之拼接为字符串sql
//     * @return
//     */
//    public static String getInsertFields(Object oj){
//        //判断是否为空
//        if (oj == null){
//            return null;
//        }
//        //获取class文件
//        Class classInfo = oj.getClass();
//        //获取当前类的父类的属性
//        classInfo.getSuperclass().getDeclaredFields();
//        //获取当前类的所有属性集合
//        Field[] declaredFields = classInfo.getDeclaredFields();
//        return getFiled(declaredFields);
//    }

    /**
     *遍历类的属性,并将其拼接成字符串
     * @param declaredFields
     * @return
     */
    public static String getFiled(Field[] declaredFields){
        //创建StringBuffer对象
        StringBuffer sf = new StringBuffer();
        //遍历当前类的所有属性
        for (int i = 0;i < declaredFields.length;i++){
            sf.append(declaredFields[i].getName());
            //字段之间用逗号分隔
            if (i < declaredFields.length - 1){
                sf.append(",");
            }
        }
        return sf.toString();
    }

    /**
     * 封装当前类和父类的属性，并将其拼接成字符串
     * @param oj
     * @return
     */
    public static String fatherAndSonField(Object oj){
        if (oj == null){
            return null;
        }
        //获取class文件
        Class classInfo = oj.getClass();
        //获取当前类的属性(子类)
        Field[] sonField = classInfo.getDeclaredFields();
        //将当前类的属性拼装为字符串
        String son = getFiled(sonField);
        //获取当前类的父类的属性(父类)
        Field[] fatherField = classInfo.getSuperclass().getDeclaredFields();
        //将父类的属性拼装成字符串
        String father = getFiled(fatherField);

        return son + "," + father;
    }

    /**
     *遍历类的属性的值,并将其拼接成字符串
     * @param declaredFields
     * @return
     */
    public static String getFiledValue(Object oj,Field[] declaredFields){
        //创建StringBuffer对象
        StringBuffer sf = new StringBuffer();
        //遍历当前类的所有属性的值
        for (int i = 0;i < declaredFields.length;i++){
            try {
                //获取当前类的属性
                Field field = declaredFields[i];
                //添加权限，允许操作私有属性！如不加将抛出该异常
                field.setAccessible(true);
                //获取到当前属性的值
                Object value = field.get(oj);
                //判断标识获取的值的类型是否为String类型
                boolean flag = false;
                //判断获取的值是否为null并且是否为String类型或者Timestamp类型
                if(value != null && (value instanceof String || value instanceof Timestamp)) {
                    flag = true;
                }
                //如果不为null并且为String类型或者Timestamp类型就加‘’
                if (flag){
                    sf.append("'");
                    sf.append(value);
                    sf.append("'");
                }else {
                    sf.append(value);
                }

               // sf.append("'" + declaredFields[i].get(oj) + "'");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            //字段(属性)的值之间用逗号分隔
            if (i < declaredFields.length - 1){
                sf.append(",");
            }
        }
        return sf.toString();
    }

    /**
     * 封装当前类和父类的属性的值，并将其拼接成字符串
     * @param oj
     * @return
     */
    public static String fatherAndSonFieldValue(Object oj){
        if (oj == null){
            return null;
        }
        //获取class文件
        Class classInfo = oj.getClass();
        //获取当前类的属性(子类)
        Field[] sonField = classInfo.getDeclaredFields();
        //将当前类的属性的值拼装成字符串
        String son = getFiledValue(oj,sonField);
        //获取当前类的父类的属性(父类)
        Field[] fatherField = classInfo.getSuperclass().getDeclaredFields();
        //将父类的属性的值并拼装成字符串
        String father = getFiledValue(oj,fatherField);

        return son + "," + father;
    }


    public static void main(String[] args){
        TestEntity te = new TestEntity();
        String field = fatherAndSonField(te);
        te.setUserName("zhangsan");
        te.setPassword("123456");
        te.setPhone("13381810527");
        te.setEmail("767237807@qq.com");
        te.setCreated(DateUtils.getTimestamp());
        te.setUpdated(DateUtils.getTimestamp());
        String value = fatherAndSonFieldValue(te);
        System.out.println(field);
        System.out.println(value);

        SQL sql = new SQL(){{

            INSERT_INTO("");
            VALUES(field,value);

        }};
        System.out.println(sql.toString());
    }
}
