package com.shop.manage.impl;

import com.alibaba.fastjson.JSONObject;
import com.shop.common.api.BaseApiService;
import com.shop.common.redis.BaseRedisService;
import com.shop.common.token.TokenUtils;
import com.shop.constants.Constants;
import com.shop.constants.DBTableName;
import com.shop.constants.MQInterfaceType;
import com.shop.dao.UserDao;
import com.shop.entity.UserEntity;
import com.shop.manage.UserServiceManage;
import com.shop.mq.producer.RegisterMailboxProducer;
import com.shop.utils.DateUtils;
import com.shop.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import java.util.Map;


/**
 * @ClassName UserServiceManageImpl
 * @Description 用户服务实现类(包含注册和发送邮件) -----消息生产者
 * @Author Jason Biao
 * @Date 2018\12\6 0006 16:43
 * @Version 1.0
 **/
@Slf4j
@Service
public class UserServiceManageImpl extends BaseApiService implements UserServiceManage {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RegisterMailboxProducer registerMailboxProducer;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private BaseRedisService baseRedisService;

    //引入application.properties中的值
    @Value("${messages.queue}")
    private String MESSAGES_QUEUE;

    /**
     * 注册服务并发送邮件给用户
     * @param userEntity
     */
    @Override
    public void regist(UserEntity userEntity) {
        userEntity.setCreated(DateUtils.getTimestamp());
        userEntity.setUpdated(DateUtils.getTimestamp());
        //获取手机号码
        String phone = userEntity.getPhone();
        //获取密码
        String password = userEntity.getPassword();
        //注册时给密码加密加盐
        userEntity.setPassword(md5PassSalt(phone,password));
        //注册数据
        userDao.save(userEntity, DBTableName.TABLE_MB_USER);
        //创建队列
        Destination activeMQQueue = new ActiveMQQueue(MESSAGES_QUEUE);
        //邮件内容json的组装(报文格式的组装)
        String mailMessage = mailMessage(userEntity.getEmail(), userEntity.getUserName());

        log.info("***注册发送邮件报文格式：mailMessage=" + mailMessage);
        //发送邮件
        registerMailboxProducer.send(activeMQQueue,mailMessage);
    }

    /**
     * MD5密码加盐
     * @param phone
     * @param password
     * @return
     */
    @Override
    public String md5PassSalt(String phone, String password) {
        //通过手机号码加密码双重加密(加盐)
        String newPass = MD5Util.MD5(phone + password);
        return newPass;
    }

    /**
     * 用户登录服务
     * @param userEntity
     */
    @Override
    public Map<String,Object> login(UserEntity userEntity) {

        /**
         * 在数据库中查询对应的用户信息
         */
        String phone = userEntity.getPhone();
        String password = userEntity.getPassword();
        //根据加密加盐的密码去查询
        String newPassword = md5PassSalt(phone,password);
        UserEntity userPhoneAndPwd = userDao.getUserPhoneAndPwd(phone, newPassword);
        //判断是否查询到用户信息
        if(userPhoneAndPwd == null){
            return setResultError("账号或密码错误");
        }
        //生成对应的token(令牌)
        String token = tokenUtils.createToken();
        /**
         * key作为自定义令牌，用的userId作为value；两者以键值对的形式存放在redis中
         */
        //获取userId
        Long userId = userPhoneAndPwd.getId();
        //将token和userId存放在redis中
        baseRedisService.set(token,userId + "", Constants.USER_TOKEN_TERMVALIDITY);
        //返回token信息给客户端
        return setResultSuccessData(token);
    }

    /**
     * 根据token查询用户信息
     * @param token
     * @return
     */
    @Override
    public Map<String, Object> getUser(String token) {
        //从redis中根据token查找到userId
       String userId = baseRedisService.get(token);
       //判断token是否失效
        if(StringUtils.isEmpty(userId)){
            return setResultError("用户已经过期");
        }
        Long newUserId = Long.parseLong(userId);
        //根据userId查询用户信息
        UserEntity userInfo = userDao.getUserInfo(newUserId);
        //返回的密码设置为null
        userInfo.setPassword(null);
        return setResultSuccessData(userInfo);
    }

    /**
     *邮件消息信息封装
     * @param email
     * @param userName
     * @return
     */
    private String mailMessage(String email, String userName){

        JSONObject root = new JSONObject();
        //创建邮件头信息
        JSONObject header = new JSONObject();
        //设置邮件接口类型
        header.put("interfaceType", MQInterfaceType.SMS_MAIL);
        //创建邮件内容信息
        JSONObject content = new JSONObject();
        /**
         * 添加邮件内容信息
         */
        content.put("mail",email);
        content.put("userName",userName);
        /**
         * 将邮件头信息和内容信息添加进root对象返回
         */
        root.put("header",header);
        root.put("content",content);
        return root.toJSONString();
    }
}
