package com.shop.adapter;

import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName MessageAdapter
 * @Description 消息适配器(所有消息都会交给MessageAdapter进行转发)
 * @Author Jason Biao
 * @Date 2018\12\12 0012 13:58
 * @Version 1.0
 **/
public interface MessageAdapter {

    /**
     * 接受消息
     */
    public void distribute(JSONObject jsonObject);
}
