package com.srx.discussion.Mappers;

import com.srx.discussion.Entities.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author srx
 * @description
 * @create 2020-07-29 06:42:11
 */
public interface UserMapper {
    /**
     *  通过传入的email查找是否存在该用户，用于登录
     * @param email email在数据库存储中有唯一约束
     * @return
     */
    User queryUserByEmail(String email);

    /**
     * 添加用户，用于注册
     * @param user
     * @return
     */
    boolean insertUser(User user);

    /**
     *  通关传入的email判断用户权限
     * @param email
     * @return 1为普通用户，0为管理员用户
     */
    String queryUserAuthorityByEmail(String email);

    /**
     * 用户更改密码
     * @param user
     * @return
     */
    boolean updateUserPassword(User user);

    /**
     * 登录方法
     * @param username
     * @param password
     * @return
     */
    User login(@Param(value = "username") String username,@Param(value = "password") String password);


    /**
     * 用于验证传入的用户名或者邮箱，在数据库中是佛已经存在，如果存在返回查询到的user
     * 不存在返回null
     *
     * @param uniqueId
     * @return
     */
    User verification(@Param(value = "uniqueId") String uniqueId);

    /**
     * 通过id查询用户是否存在，主要用于后面通过id验证用户有效性
     * @param userId
     * @return
     */
    User queryUserById(@Param(value = "userId") Integer userId);

}
