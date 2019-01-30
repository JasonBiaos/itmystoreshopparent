package com.shop.common.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName BaseRedisService
 * @Description redis服务封装类
 * @Author Jason Biao
 * @Date 2018\11\30 0030 15:42
 * @Version 1.0
 **/
@Component
public class BaseRedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 插入数据到redis
     * @param key
     * @param data
     */
    public void setString(String key,String data){
        set(key,data,null);
    }

    public void setString(String key,String data,Long timeout){
        set(key, data, timeout);
    }

    public void set(String key,Object data,Long timeout){
        if(data != null){
            if(data instanceof String){
                String setData = (String) data;
                stringRedisTemplate.opsForValue().set(key,setData);
            }
            //设置有效期
            if (timeout != null){
                stringRedisTemplate.expire(key,timeout, TimeUnit.SECONDS);
            }
        }
    }

    /**
     * 根据key获取redis数据库的value值
     * @param key
     * @return
     */
    public String get(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 根据key删除redis数据库的value值
     * @param key
     */
    public void delete(String key){
        stringRedisTemplate.delete(key);
    }
}
