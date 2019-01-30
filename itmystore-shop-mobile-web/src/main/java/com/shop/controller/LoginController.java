package com.shop.controller;

import com.shop.base.BaseController;
import com.shop.constants.BaseApiConstants;
import com.shop.constants.Constants;
import com.shop.entity.UserEntity;
import com.shop.feign.UserFegin;
import com.shop.web.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @ClassName LoginController
 * @Description 用户登录
 * @Author Jason Biao
 * @Date 2018\12\21 0021 11:25
 * @Version 1.0
 **/
@Controller
public class LoginController extends BaseController {

    //登录页面常量
    private static final String LOGIN = "login";

    //主页常量
    private static final String INDEX = "index";

    @Autowired
    private UserFegin userFegin;

    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping("/localLogin")
    public String localLogin(){
        return LOGIN;
    }

    /**
     * 用户登录实现，失败返回登录页面；成功跳转到主页
     * @param userEntity
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/login")
    public String login(UserEntity userEntity, HttpServletRequest request, HttpServletResponse response){
        //feign调用登录服务
        Map<String, Object> login = userFegin.login(userEntity);
        //获取调用登录服务后的返回编码
        Integer code = (Integer)login.get(BaseApiConstants.HTTP_RES_CODE_NAME);
        /**
         * 登录失败
         */
        if (!code.equals(BaseApiConstants.HTTP_RES_CODE_200)){
            //获取调用登录服务后的返回信息
            String msg = (String) login.get("msg");
            return setError(request,msg,LOGIN);
        }

        /**
         * 登录成功之后，获取token,将token存放到cookie中
         */
        String token = (String) login.get("data");
        CookieUtil.addCookie(response, Constants.USER_TOKEN,token,Constants.WEBUSER_COOKIE_TOKEN_TERMVALIDITY);
        return INDEX;
    }
}
