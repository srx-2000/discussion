package com.srx.discussion.Mappers;

import com.srx.discussion.Entities.User;
import com.srx.discussion.Entities.UserToPosts;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author srx
 * @description
 * @create 2020-08-11 22:33:12
 */

public interface UserToPostsMapper {
    /**
     * 新增一条收藏记录
     * @param userId
     * @param postsId
     * @return
     */
    boolean insertStarPosts(@Param(value = "userId") Integer userId,@Param(value = "postsId") Integer postsId);

    /**
     * 将is_star为0的记录改为1
     * @param userId
     * @param postsId
     * @return
     */
    boolean updateIsStarToLive(@Param(value = "userId") Integer userId,@Param(value = "postsId") Integer postsId);

    /**
     * 软删除一个记录
     * @param userId
     * @param postsId
     * @return
     */
    boolean updateIsStarToDelete(@Param(value = "userId") Integer userId,@Param(value = "postsId") Integer postsId);

    /**
     * 查询is_star状态
     * @param userId
     * @param postsId
     * @return
     */
    String queryIsStarStatus(@Param(value = "userId") Integer userId,@Param(value = "postsId") Integer postsId);

    /**
     * 查询用户收藏的posts信息，返回的user中包含postsId,postsTitle
     * @param userId
     * @return
     */
    List<User> queryUserStarPosts(@Param(value = "userId") Integer userId);

    /**
     * 查询所有与传入UserId相关的UserToPosts记录，暂定的主要作用在于根据提供的UserId取收藏时间
     * @param userId
     * @return
     */
    List<UserToPosts> queryUserToPostsListByUserId(@Param(value = "userId") Integer userId);

}
