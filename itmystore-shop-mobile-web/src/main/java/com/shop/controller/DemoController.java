package com.shop.controller;

import com.shop.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName DemoController
 * @Description 跳转页面案例
 * @Author Jason Biao
 * @Date 2018\12\14 0014 14:27
 * @Version 1.0
 **/
@Slf4j
@Controller
public class DemoController extends BaseController {

    //定义index页面的常量
    private static final String INDEX = "index";

    @RequestMapping("/index")
    public String index(HttpServletRequest request,String token){

        log.info("H5端搭建成功！！！！***userName:{}" + getUserEntity(token).getUserName());
        return INDEX;
    }
}
