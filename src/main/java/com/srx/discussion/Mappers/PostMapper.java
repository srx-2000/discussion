package com.srx.discussion.Mappers;

import com.srx.discussion.Entities.Post;
import javafx.geometry.Pos;
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
     * @param post
     * @return
     */
    boolean insertPost(Post post);

    /**
     * 删除贴吧的同时删除贴吧下的所有帖子
     *
     * @param postsId
     * @return
     */
    boolean updateIsLiveToDeleteByPostsId(@Param(value = "postsId") int postsId);

    /**
     * 查询帖子列表，用于显示所有帖子,注意这里使用了分页查询
     * @param postsId
     * @return
     */
    List<Post> paginationQueryPostList(@Param(value = "postsId") int postsId,@Param(value = "begin") int begin,@Param(value = "pageSize") int pageSize);

    /**
     * 通过postId对帖子进行删除，该操作与上面的updateIsLiveToDeleteByPostsId为不同级操作。
     * 改操作为普通用户或管理员用户，对个别帖子进行管理时的操作，与贴吧的删除无关
     * @param postId
     * @return
     */
    boolean updateIsLiveToDeleteByPostId(@Param(value = "postId") int postId);

    /**
     * 根据给出的id查询并返回post
     * @param postId
     * @return
     */
    Post queryPostById(@Param(value = "postId") Integer postId);
    /**
     * 根据传入的postId查询该回复的发出人Id，用于与从session中取出的UserId做比较。两者相等或该用户拥有管理员权限才可以进行删除，
     * @param postId
     * @return
     */
    Integer queryPostManId(@Param(value = "postId") Integer postId);
}
