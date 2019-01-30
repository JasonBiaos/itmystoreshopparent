package com.shop.api.service.impl;

import com.shop.api.service.UserService;
import com.shop.common.api.BaseApiService;
import com.shop.entity.UserEntity;
import com.shop.manage.UserServiceManage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName UserServiceImpl
 * @Description UserServiceImpl服务层(用户服务)
 * @Author Jason Biao
 * @Date 2018\12\6 0006 16:31
 * @Version 1.0
 **/
@Slf4j
@RestController
public class UserServiceImpl extends BaseApiService implements UserService{

    @Autowired
    private UserServiceManage userServiceManage;

    /**
     * 注册服务
     * @param userEntity
     * @return
     */
    @Override
    public Map<String, Object> regist(@RequestBody UserEntity userEntity) {

        //判断字段是否为空
        try {
            userServiceManage.regist(userEntity);
            return setResultSuccess();
        }catch (Exception e){
            log.error("***regist() error:",e);
            return setResultError("注册失败");
        }

    }

    /**
     * 用户登录服务
     * @param userEntity
     * @return
     */
    @Override
    public Map<String, Object> login(@RequestBody UserEntity userEntity) {
        return userServiceManage.login(userEntity);
    }

    /**
     * 根据token查询用户信息
     * @param token
     * @return
     */
    @Override
    public Map<String, Object> getUser(@RequestParam("token")String token) {
        if (StringUtils.isEmpty(token)){
            return setResultError("token不能为空");
        }
        return userServiceManage.getUser(token);
    }
}
