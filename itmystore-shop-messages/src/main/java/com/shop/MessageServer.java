package com.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName MessageServer
 * @Description TODO
 * @Author Jason Biao
 * @Date 2018\12\12 0012 15:04
 * @Version 1.0
 **/
@SpringBootApplication
@EnableEurekaClient
public class MessageServer {

    public static void main(String[] args){
        SpringApplication.run(MessageServer.class,args);
    }
}
