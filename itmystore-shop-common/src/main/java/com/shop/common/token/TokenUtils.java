package com.shop.common.token;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @ClassName TokenUtils
 * @Description Token(令牌)工具类封装
 * @Author Jason Biao
 * @Date 2018\12\13 0013 15:31
 * @Version 1.0
 **/
@Component
public class TokenUtils {

    /**
     * 生成Token
     * @return
     */
    public String createToken(){
        return UUID.randomUUID().toString();
    }
}
