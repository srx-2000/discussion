package com.srx.discussion.Services;

import com.srx.discussion.Entities.User;
import com.srx.discussion.Entities.UserToPost;

import java.util.List;

/**
 * @author srx
 * @description
 * @create 2020-08-11 22:45:49
 */
public interface UserToPostService {
    /**
     * 新增一条收藏记录
     *
     * @param userId
     * @param postId
     * @return
     */
    boolean insertStarPost(Integer userId, Integer postId);

    /**
     * 将is_star为0的记录改为1
     *
     * @param userId
     * @param postId
     * @return
     */
    boolean updateIsStarToLive(Integer userId, Integer postId);

    /**
     * 软删除一个记录
     *
     * @param userId
     * @param postId
     * @return
     */
    boolean updateIsStarToDelete(Integer userId, Integer postId);

    /**
     * 查询is_star状态
     *
     * @param userId
     * @param postId
     * @return
     */
    String queryIsStarStatus(Integer userId, Integer postId);

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

}
