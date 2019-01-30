package com.shop.controller;

import com.shop.base.BaseController;
import com.shop.constants.BaseApiConstants;
import com.shop.entity.UserEntity;
import com.shop.feign.UserFegin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @ClassName RegistController
 * @Description 用户注册前端页面调用
 * @Author Jason Biao
 * @Date 2018\12\21 0021 9:52
 * @Version 1.0
 **/
@Controller
public class RegistController extends BaseController {

    //注册页面
    private static final String LOCALREGIST = "localRegist";

    //登录页面
    private static final String LOGIN = "login";

    @Autowired
    private UserFegin userFegin;

    /**
     * 跳转到注册页面
     * @return
     */
    @RequestMapping("/localRegist")
    public String localRegist(){
        return LOCALREGIST;
    }

    /**
     *用户注册实现:注册失败返回注册页面；注册成功进入登录页面
     * @param userEntity
     * @param request
     * @return
     */
    @RequestMapping("/regist")
    public String regist(UserEntity userEntity, HttpServletRequest request){
        try {
            //调用注册服务
            Map<String, Object> registMap = userFegin.regist(userEntity);
            //获取调用注册服务后返回编码
            Integer code = (Integer) registMap.get(BaseApiConstants.HTTP_RES_CODE_NAME);
            if(!code.equals(BaseApiConstants.HTTP_RES_CODE_200)){
                //获取调用注册服务后返回信息msg
                String msg = (String) registMap.get("msg");
                //注册失败返回到注册页面
                return setError(request,msg,LOCALREGIST);
            }
                //注册成功,进入登录页面
                return LOGIN;
        }catch (Exception e){
            return setError(request,"注册失败，请重新注册！！！",LOCALREGIST);
        }
    }
}
