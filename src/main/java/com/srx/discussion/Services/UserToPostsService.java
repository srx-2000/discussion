package com.srx.discussion.Services;

import com.srx.discussion.Entities.base.User;
import com.srx.discussion.Entities.hybrid.UserToPosts;

import java.util.List;

/**
 * @author srx
 * @description
 * @create 2020-08-11 22:45:14
 */
public interface UserToPostsService {
    /**
     * 新增一条收藏记录
     *
     * @param userId
     * @param postsId
     * @return
     */
    boolean insertStarPosts(Integer userId, Integer postsId);

    /**
     * 将is_star为0的记录改为1
     *
     * @param userId
     * @param postsId
     * @return
     */
    boolean updateIsStarToLive(Integer userId, Integer postsId);

    /**
     * 软删除一个记录
     *
     * @param userId
     * @param postsId
     * @return
     */
    boolean updateIsStarToDelete(Integer userId, Integer postsId);

    /**
     * 查询is_star状态
     *
     * @param userId
     * @param postsId
     * @return
     */
    String queryIsStarStatus(Integer userId, Integer postsId);

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

}
