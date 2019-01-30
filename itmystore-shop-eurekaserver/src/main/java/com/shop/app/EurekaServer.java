package com.shop.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @ClassName EurekaServer
 * @Description SpringCloud 服务注册中心
 * @Author Jason Biao
 * @Date 2018\11\30 0030 11:08
 * @Version 1.0
 **/
@SpringBootApplication
@EnableEurekaServer //@EnableEurekaServer 这个注解表明注册服务端
public class EurekaServer {

    public static void main(String[] args){
        SpringApplication.run(EurekaServer.class,args);
    }
}
