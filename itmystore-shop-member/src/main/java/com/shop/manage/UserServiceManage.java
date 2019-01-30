package com.shop.manage;

import com.shop.entity.UserEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @ClassName UserServiceManage
 * @Description 用户服务(业务逻辑类)
 * @Author Jason Biao
 * @Date 2018\12\6 0006 16:38
 * @Version 1.0
 **/
public interface UserServiceManage {

    /**
     * 注册服务
     * @param userEntity
     */
    public void regist(UserEntity userEntity);

    /**
     * MD5密码加盐
     * @param phone
     * @param password
     * @return
     */
    public String md5PassSalt(String phone,String password);

    /**
     * 用户登录服务
     * @param userEntity
     */
    public Map<String,Object> login(UserEntity userEntity);

    /**
     * 根据token查询用户信息
     * @param token
     * @return
     */
    public Map<String,Object> getUser(@RequestParam("token") String token);
}
