package com.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName MobileServer
 * @Description H5服务启动类
 * @Author Jason Biao
 * @Date 2018\12\14 0014 14:27
 * @Version 1.0
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients//开启feign服务。服务可以通过@FeignClient使用和发现服务场中的其他服务。
public class AppMobile {

    public static void main(String[] args){
        SpringApplication.run(AppMobile.class,args);
    }
}
