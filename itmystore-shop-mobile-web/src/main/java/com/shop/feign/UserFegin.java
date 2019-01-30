package com.shop.feign;

import com.shop.api.service.UserService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ClassName UserFegin
 * @Description UserFegin 客户端封装
 * @Author Jason Biao
 * @Date 2018\12\20 0020 16:16
 * @Version 1.0
 **/

/**
 * 通过@ FeignClient（“服务名”），来指定调用哪个服务。
 * 比如在代码中调用了service-hi服务的“/hi”接口，还可以使用url参数指定一个URL
 * fallback  出现错误回调类
 */
@FeignClient("member")
public interface UserFegin extends UserService {
}
