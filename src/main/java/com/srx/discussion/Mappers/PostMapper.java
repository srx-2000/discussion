package com.srx.discussion.Mappers;

import com.srx.discussion.Entities.base.Post;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author srx
 * @description
 * @create 2020-07-29 06:42:21
 */
public interface PostMapper {
    /**
     * 添加帖子
     *
     * @param post
     * @return
     */
    boolean insertPost(Post post);

    /**
     * 对传入贴吧id下的所有帖子进行批量软删除
     *
     * @param postsId
     * @return
     */
    boolean deleteBatchPost(@Param(value = "postsId") int postsId);

    /**
     * 对单个帖子进行软删除
     *
     * @param postId
     * @return
     */
    boolean deleteSinglePost(@Param(value = "postId") int postId);

    /**
     * 查询帖子列表，用于显示所有帖子,注意这里使用了分页查询
     *
     * @param postsId
     * @return
     */
    List<Post> paginationQueryPostList(@Param(value = "postsId") Integer postsId, @Param(value = "begin") int begin, @Param(value = "pageSize") int pageSize);

    /**
     * 根据给出的id查询并返回post
     *
     * @param postId
     * @return
     */
    Post queryPostById(@Param(value = "postId") Integer postId);

    /**
     * 根据传入的postId查询该帖子的发出人Id，用于与从session中取出的UserId做比较。两者相等或该用户拥有管理员权限才可以进行删除，
     *
     * @param postId
     * @return
     */
    Integer queryPostManId(@Param(value = "postId") Integer postId);

    /**
     * 返回所有posts_id=postsId and is_live=1的记录，用于批量删除时对子删除的遍历
     *
     * @param postsId
     * @return
     */
    List<Post> queryPostListByPostsId(@Param(value = "postsId") Integer postsId);

    /**
     * 返回一个贴吧中的所有帖子的数目
     *
     * @param postsId
     * @return
     */
    Integer queryPostCount(@Param(value = "postsId") Integer postsId);

    /**
     * 查询所有帖子，用于首页刷新的操作。
     * @param begin
     * @param pageSize
     * @return
     */
    List<Post> paginationQueryAllPostList(@Param(value = "begin") int begin, @Param(value = "pageSize") int pageSize);

    /**
     * 通过用户id查找该用户发送的所有的帖子，用于信息通知
     * @param userId
     * @return
     */
    List<Post> queryPostListByUserId(@Param(value = "userId") Integer userId);
}
