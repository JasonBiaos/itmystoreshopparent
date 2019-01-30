package com.shop.api.service.impl;

import com.shop.api.service.DemoApiService;
import com.shop.common.api.BaseApiService;
import com.shop.common.redis.BaseRedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName DemoApiServiceImpl
 * @Description TODO
 * @Author Jason Biao
 * @Date 2018\11\30 0030 13:37
 * @Version 1.0
 **/
@Slf4j
@RestController
public class DemoApiServiceImpl extends BaseApiService implements DemoApiService {

    @Autowired
    private BaseRedisService baseRedisService;

    @Override
    public Map<String, Object> demo() {
        log.info("this is demo");
      return setResultSuccess();
    }

    @Override
    public Map<String, Object> setKey(String key, String data) {
        baseRedisService.setString(key, data);
        return setResultSuccess();
    }

    @Override
    public Map<String, Object> getKey(String key) {
        String data = baseRedisService.get(key);
        System.out.println("----------------" +data);
        return setResultSuccessData(data);
    }
}
