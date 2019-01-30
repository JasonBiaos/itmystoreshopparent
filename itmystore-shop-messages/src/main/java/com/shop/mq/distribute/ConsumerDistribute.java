package com.shop.mq.distribute;

import com.alibaba.fastjson.JSONObject;
import com.shop.adapter.MessageAdapter;
import com.shop.constants.MQInterfaceType;
import com.shop.service.SMSMailboxService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName ConsumerDistribute
 * @Description 接受生产者消息：消费消息(ActiveMQ分发消息，从队列获取最新消息)
 * @Author Jason Biao
 * @Date 2018\12\12 0012 14:15
 * @Version 1.0
 **/
@Slf4j
@Component
public class ConsumerDistribute {

    @Autowired
    private SMSMailboxService smsMailboxService;

    /**
     * 接受生产者消费
     * @param json
     */
    @JmsListener(destination = "mail_queue")//@JmsListener:实现对特定队列的监听消费
    public void distribute(String json){
        log.info("###接受到消息内容json:{}", json);
        //判断消息是否为空
        if (StringUtils.isEmpty(json)){
            return;
        }
        JSONObject jsonObject = new JSONObject().parseObject(json);
        //获取头信息
        JSONObject header = jsonObject.getJSONObject("header");
        //获取邮件的接口类型
        String interfaceType = header.getString("interfaceType");
        MessageAdapter messageAdapter = null;
        /**
         * 判断是发送什么(邮件或者其他...... 便于以后集成其他......)
         */
        switch (interfaceType){
            //发送邮件
            case MQInterfaceType
                    .SMS_MAIL:
                messageAdapter = smsMailboxService;
                break;
            default:
        }

        //执行消息服务
        JSONObject content = jsonObject.getJSONObject("content");
        messageAdapter.distribute(content);
    }
}
