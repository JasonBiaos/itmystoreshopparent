package com.shop.base;

import com.alibaba.fastjson.JSONObject;
import com.shop.constants.BaseApiConstants;
import com.shop.entity.UserEntity;
import com.shop.feign.UserFegin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName BaseController
 * @Description BaseController 封装类
 * @Author Jason Biao
 * @Date 2018\12\20 0020 15:54
 * @Version 1.0
 **/
@Controller
public class BaseController {

    @Autowired
    private UserFegin userFegin;

    /**
     * 用feign调用---->根据token查询用户信息
     * @param token
     * @return
     */
    public UserEntity getUserEntity(String token){
        //通过feign调用根据token查询用户信息的服务
        Map<String,Object> userMap = userFegin.getUser(token);
        //获取的状态编码code
        Integer code = (Integer) userMap.get(BaseApiConstants.HTTP_RES_CODE_NAME);
        if(!code.equals(BaseApiConstants.HTTP_RES_CODE_200)){
            return null;
        }
        //如果code=200 获取data参数的值为linkedHashMap类型
        /**
         * 因为rpc远程调用在底层还是使用的HTTPClient，所以在传递参数的时候，必定要有个顺序，
         * 当你传递map的时候map里面的值也要有顺序，不然服务层在接的时候就出问题了，
         * 所以它才会从map转为linkedhashMap！
         */
        LinkedHashMap linkedHashMap = (LinkedHashMap) userMap.get(BaseApiConstants.HTTP_RES_CODE_DATA);
        String data = new JSONObject().toJSONString(linkedHashMap);
        UserEntity userEntity = new JSONObject().parseObject(data,UserEntity.class);
        return userEntity;
    }

    /**
     * 请求返回的异常错误信息
     * @param request 请求信息
     * @param msg 返回消息信息
     * @param addres 页面常量
     * @return
     */
    public String setError(HttpServletRequest request ,String msg, String addres){
        request.setAttribute("error",msg);
        return addres;
    }
}
