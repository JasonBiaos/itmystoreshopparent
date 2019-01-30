package com.shop.mq.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

/**
 * @ClassName RegisterMailboxProducer
 * @Description 功能描述:(使用MQ调用消息服务,发送注册邮件) ----消息生产者
 * @Author Jason Biao
 * @Date 2018\12\11 0011 11:18
 * @Version 1.0
 **/
@Service("registerMailboxProducer")
public class RegisterMailboxProducer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    /**
     * 发送邮件服务
     * @param destination
     * @param json
     */
    public void send(Destination destination, String json){
        jmsMessagingTemplate.convertAndSend(destination,json);
    }
}
