package com.shop.service;

import com.alibaba.fastjson.JSONObject;
import com.shop.adapter.MessageAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @ClassName SMSMailboxService
 * @Description 发送邮件的实现类
 * @Author Jason Biao
 * @Date 2018\12\12 0012 14:07
 * @Version 1.0
 **/
@Slf4j
@Service
public class SMSMailboxService implements MessageAdapter {

    @Autowired
    private JavaMailSender javaMailSender;//自动注入发送邮件的bean

    @Override
    public void distribute(JSONObject jsonObject) {
        //获取邮件账号信息
        String mail = jsonObject.getString("mail");
        //获取邮件用户名信息
        String userName = jsonObject.getString("userName");
        log.info("###消费者收到消息... mail:{},userName{}", mail,userName);

        /**
         *  发送邮件
         *  SimpleMailMessage是值对象，封装了一些简单的属性，如from、to、subject、text等等，
         *  SimpleMailMessage只能用来发送text格式的邮件
         */
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        //发送邮件的格式
        simpleMailMessage.setFrom(mail);
        //发送邮件给哪个用户
        simpleMailMessage.setTo(mail);
        //发送邮件的标题
        simpleMailMessage.setSubject("恭喜你成为豪彪大酒店新用户......");
        //发送邮件内容
        simpleMailMessage.setText("非常感谢您的支持和关注，" +
                "有关豪彪大酒店最新活动请上官网http://www.33eee.com关注");
        log.info("###发送短信邮箱 mail:{}", mail);
        javaMailSender.send(simpleMailMessage);
    }
}
