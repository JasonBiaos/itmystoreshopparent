package com.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName MemberServer
 * @Description 会员微服务启动主程序
 * @Author Jason Biao
 * @Date 2018\11\30 0030 13:37
 * @Version 1.0
 **/
@SpringBootApplication
@EnableEurekaClient
public class MemberServer {

    public static void main(String[] args){
        SpringApplication.run(MemberServer.class,args);
    }
}
