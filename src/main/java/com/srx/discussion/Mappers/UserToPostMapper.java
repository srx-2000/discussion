package com.srx.discussion.Mappers;

import com.srx.discussion.Entities.base.User;
import com.srx.discussion.Entities.hybrid.UserToPost;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author srx
 * @description
 * @create 2020-08-11 22:33:33
 */
public interface UserToPostMapper {
    /**
     * 新增一条收藏记录
     * @param userId
     * @param postId
     * @return
     */
    boolean insertStarPost(@Param(value = "userId") Integer userId, @Param(value = "postId") Integer postId);

    /**
     * 将is_star为0的记录改为1
     * @param userId
     * @param postId
     * @return
     */
    boolean updateIsStarToLive(@Param(value = "userId") Integer userId,@Param(value = "postId") Integer postId);

    /**
     * 软删除一个记录
     * @param userId
     * @param postId
     * @return
     */
    boolean updateIsStarToDelete(@Param(value = "userId") Integer userId,@Param(value = "postId") Integer postId);

    /**
     * 查询is_star状态
     * @param userId
     * @param postId
     * @return
     */
    String queryIsStarStatus(@Param(value = "userId") Integer userId,@Param(value = "postId") Integer postId);

    /**
     * 查询用户收藏的post信息，返回的user中包含postId,postTitle
     * @param userId
     * @return
     */
    List<User> queryUserStarPost(@Param(value = "userId") Integer userId);

    /**
     * 查询所有与传入UserId相关的UserTopost记录，暂定的主要作用在于根据提供的UserId取收藏时间
     * @param userId
     * @return
     */
    List<UserToPost> queryUserToPostListByUserId(@Param(value = "userId") Integer userId);
}
