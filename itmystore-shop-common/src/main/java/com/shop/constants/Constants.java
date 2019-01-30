package com.shop.constants;

/**
 * @ClassName Constants
 * @Description 常量类
 * @Author Jason Biao
 * @Date 2018\12\13 0013 15:57
 * @Version 1.0
 **/
public interface Constants {

    //用户会话Token保持90天
    Long USER_TOKEN_TERMVALIDITY = 60 * 60 * 24 * 90l;

    //USER_TOKEN的有效期
    int WEBUSER_COOKIE_TOKEN_TERMVALIDITY = 1000 * 60 * 60 * 24 * 90;

    //token
    String USER_TOKEN = "token";
}
