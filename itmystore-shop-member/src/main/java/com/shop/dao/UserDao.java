package com.shop.dao;

import com.shop.common.mybatis.BaseDao;
import com.shop.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 用户服务（注册、登录）
 */
@Mapper
public interface UserDao extends BaseDao {

    /**
     * 查询用户信息
     * @param phone
     * @param password
     * @return
     */
    @Select("select ID,USERNAME,PASSWORD,phone,EMAIL,created,updated from mb_user where PHONE=#{phone} and PASSWORD = #{password}")
    public UserEntity getUserPhoneAndPwd(@Param("phone") String phone,@Param("password") String password);

    /**
     * 根据userId查询用户信息
     * @param id
     * @return
     */
    @Select("select ID,USERNAME,PASSWORD,phone,EMAIL,created,updated from mb_user where id = #{id}")
    public UserEntity getUserInfo(@Param("id") Long id);
}
