package com.srx.discussion.Mappers;

import com.srx.discussion.Entities.User;
import com.srx.discussion.Entities.UserToInfo;
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


    /**
     * 以下代码为后添加的代码，主要与其对应的实体为UserToInfo
     * 主要作用为：对用户的一些信息的增删改查进行操作。
     */

    /**
     * 通过用户提供的用户id对用户的信息进行查询
     * @param userId
     * @return
     */
    UserToInfo queryUserInfoById(@Param(value = "userId") Integer userId);

    /**
     * 更新用户信息
     * @param userInfo
     * @return
     */
    boolean updateUserInfo(@Param(value = "userInfo") UserToInfo userInfo);

    /**
     * 插入用户信息
     * @param userInfo
     * @return
     */
    boolean insertUserInfo(@Param(value = "userInfo") UserToInfo userInfo);

    /**
     * 用于删除该用户的信息，主要与删除用户配合进行事务操作，而本系统暂时没有注销用户的功能，所以该方法暂时用不到
     * @param userId
     * @return
     */
    boolean deleteUserInfo(@Param(value = "userId") Integer userId);

    /**
     * 通过用户id获取用户昵称
     * @param userId
     * @return
     */
    String queryUserNikeName(@Param(value = "userId") Integer userId);

}
