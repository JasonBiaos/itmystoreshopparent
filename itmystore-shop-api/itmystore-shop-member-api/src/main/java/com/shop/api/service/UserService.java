package com.shop.api.service;

import com.shop.entity.UserEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @InterfaceName UserService
 * @Description 注册服务API
 * @Author Jason Biao
 * @Date 2018\12\6 0006 16:16
 * @Version 1.0
 **/
@RequestMapping("/member")
public interface UserService {

    /**
     * 注册服务
     * @RequestBody 传值以json的形式
     * @param userEntity
     * @return
     */
    @PostMapping("/regist")
    public Map<String,Object> regist(@RequestBody UserEntity userEntity);

    /**
     * 用户登录服务
     * 功能步骤描述：用户登录成功后，生成对应的token（令牌）作为key值，并将用户的userId作为value值；
     *               两者以键值对的形式存放在redis数据库中，返回对应的token信息给客户端！
     * @RequestBody 传值以json的形式
     * @param userEntity
     * @return
     */
    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody UserEntity userEntity);

    /**
     * 根据token查询用户信息
     * @param token
     * @return
     */
    @PostMapping("/getUser")
    public Map<String,Object> getUser(String token);
}
