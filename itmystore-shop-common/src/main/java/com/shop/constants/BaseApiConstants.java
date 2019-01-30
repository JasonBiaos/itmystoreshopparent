package com.shop.constants;

/**
 * @ClassName BaseApiConstants
 * @Description 返回状态响应类
 * @Author Jason Biao
 * @Date 2018\11\30 0030 14:34
 * @Version 1.0
 **/
public interface BaseApiConstants {

    // 响应code
    String HTTP_RES_CODE_NAME = "code";

    // 响应msg
    String HTTP_RES_CODE_MSG = "msg";

    // 响应data
    String HTTP_RES_CODE_DATA = "data";

    // 响应请求成功
    String HTTP_RES_CODE_200_VALUE = "success";

    // 系统错误
    String HTTP_RES_CODE_500_VALUE = "fail";

    // 响应请求成功code
    Integer HTTP_RES_CODE_200 = 200;

    // 系统错误
    Integer HTTP_RES_CODE_500 = 500;

    //参数错误
    Integer HTTP_RES_CODE_400 = 400;

}
