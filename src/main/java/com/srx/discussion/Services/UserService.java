package com.srx.discussion.Services;

import com.srx.discussion.Entities.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author srx
 * @description
 * @create 2020-07-29 06:43:37
 */
public interface UserService {
    /**
     * 通过传入的email查找是否存在该用户，用于登录
     *
     * @param email email在数据库存储中有唯一约束
     * @return
     */
    User queryUserByEmail(String email);

    /**
     * 添加用户，用于注册
     *
     * @param user
     * @return
     */
    boolean insertUser(User user);

    /**
     * 通关传入的email判断用户权限
     *
     * @param email
     * @return 1为普通用户，0为管理员用户
     */
    String queryUserAuthorityByEmail(String email);

    /**
     * 用户更改密码
     *
     * @param user
     * @return
     */
    boolean updateUserPassword(User user);

    /**
     * 登录方法
     *
     * @param username
     * @param password
     * @return
     */
    User login(String username, String password);

    /**
     * 用于验证传入的用户名或者邮箱，在数据库中是佛已经存在，如果存在返回查询到的user
     * 不存在返回null
     *
     * @param uniqueId
     * @return
     */
    User verification(String uniqueId);

    /**
     * 通过id查询用户所有信息，主要用于内部函数调用
     *
     * @param userId
     * @return
     */
    User queryUserById(Integer userId);

    /**
     * 新增一条posts的收藏记录
     *
     * @param userId
     * @param postsId
     * @return
     */
    boolean insertStarPosts(Integer userId, Integer postsId);

    /**
     * 将软删除的posts恢复
     *
     * @param userId
     * @param postsId
     * @return
     */
    boolean updatePostsIsStarToLive(Integer userId, Integer postsId);

    /**
     * 软删除一个posts的记录
     *
     * @param userId
     * @param postsId
     * @return
     */
    boolean updatePostsIsStarToDelete(Integer userId, Integer postsId);

    /**
     * 查询posts的is_star状态
     *
     * @param userId
     * @param postsId
     * @return
     */
    String queryPostsIsStarStatus(Integer userId, Integer postsId);

    /**
     * 查询用户收藏的posts信息，返回的user中包含postsId,postsTitle
     *
     * @param userId
     * @return
     */
    List<User> queryUserStarPosts(Integer userId);

    /**
     * 查询所有与传入UserId相关的UserToPosts记录，暂定的主要作用在于根据提供的UserId取收藏时间
     *
     * @param userId
     * @return
     */
    List<UserToPosts> queryUserToPostsListByUserId(Integer userId);

    /**
     * 新增一条post的收藏记录
     *
     * @param userId
     * @param postsId
     * @return
     */
    boolean insertStarPost(Integer userId, Integer postsId);

    /**
     * 将软删除的post恢复
     *
     * @param userId
     * @param postsId
     * @return
     */
    boolean updatePostIsStarToLive(Integer userId, Integer postsId);

    /**
     * 软删除一个post的记录
     *
     * @param userId
     * @param postsId
     * @return
     */
    boolean updatePostIsStarToDelete(Integer userId, Integer postsId);

    /**
     * 查询post的is_star状态
     *
     * @param userId
     * @param postsId
     * @return
     */
    String queryPostIsStarStatus(Integer userId, Integer postsId);

    /**
     * 查询用户收藏的post信息，返回的user中包含postId,postTitle
     *
     * @param userId
     * @return
     */
    List<User> queryUserStarPost(Integer userId);

    /**
     * 查询所有与传入UserId相关的UserToPost记录，暂定的主要作用在于根据提供的UserId取收藏时间
     *
     * @param userId
     * @return
     */
    List<UserToPost> queryUserToPostListByUserId(Integer userId);

    /**
     * 以下代码为后添加的代码，主要与其对应的实体为UserToInfo
     * 主要作用为：对用户的一些信息的增删改查进行操作。
     */

    /**
     * 通过用户提供的用户id对用户的信息进行查询
     *
     * @param userId
     * @return
     */
    UserToInfo queryUserInfoById(Integer userId);

    /**
     * 更新用户信息
     *
     * @param userInfo
     * @return
     */
    boolean updateUserInfo(UserToInfo userInfo);

    /**
     * 插入用户信息
     *
     * @param userInfo
     * @return
     */
    boolean insertUserInfo(UserToInfo userInfo);

    /**
     * 用于删除该用户的信息，主要与删除用户配合进行事务操作，而本系统暂时没有注销用户的功能，所以该方法暂时用不到
     *
     * @param userId
     * @return
     */
    boolean deleteUserInfo(Integer userId);

    /**
     * 通过用户id获取用户昵称
     *
     * @param userId
     * @return
     */
    String queryUserNikeName(Integer userId);


    /**
     * 插入一个朋友圈
     *
     * @param pyq
     * @return
     */
    boolean insertPyq(Pyq pyq);

    //不提供更改朋友圈的功能

    /**
     * 这里由于并没有设置动态的主键id，所以对动态的删除主要是靠他的用户id和发布时间，
     * 理论上如果没有受到攻击的话，同一个用户不可能出现在同一时间发布多条动态，所以可以确定动态的唯一性
     * 依旧使用软删除
     *
     * @param userId
     * @param createTime
     * @return
     */
    boolean deletePyq(Integer userId,String createTime);

    /**
     * 通过用户id对一个用户的所有动态进行查询，返回一个list
     *
     * @param userId
     * @return
     */
    List<Pyq> queryPyqListById(int userId);


}
