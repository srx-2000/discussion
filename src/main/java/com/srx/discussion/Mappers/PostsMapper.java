package com.srx.discussion.Mappers;

import com.srx.discussion.Entities.Posts;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author srx
 * @description
 * @create 2020-08-02 13:16:58
 */
public interface PostsMapper {

    /**
     * 通过Id查询贴吧信息
     * @param postsId
     * @return
     */
    Posts queryPostsById(@Param(value = "postsId") int postsId);
    /**
     * 创建贴吧，并将自增主键封装到传入的posts中返回，但是并不能将数据库中的isLive和createTime也封装进该对象
     * 所以需要通过返回的Id进行查询
     * @param posts
     * @return
     */
    boolean insertPosts(Posts posts);

    /**
     * 通过贴吧名称对贴吧进行假删除,该操作可能会涉及事务，因为在删除贴吧的同时，也要同时删除该贴吧下的所有帖子，评论，回复等等。
     * @param postsTitle
     * @return
     */
    boolean updateIsLiveToDelete(@Param(value = "postsTitle") String postsTitle);

    /**
     * 通过title查询贴吧
     * @param postsTitle
     * @return
     */
    Posts queryPostsByTitle(@Param(value = "postsTitle") String postsTitle);

    /**
     * 根据给出的id和新的贴吧名，对贴吧进行改名
     * @param postsId
     * @param newPostsTitle
     * @return
     */
    boolean updatePostsTitle(@Param(value = "postsId") int postsId,@Param(value = "newPostsTitle") String newPostsTitle);

    /**
     * 通过传入的模糊查询字符串，查询所有包含该字符串的贴吧，并以列表的形式返回所有结果
     * @param blurryString
     * @return
     */
    List<Posts> blurryQueryPostsList(@Param(value = "blurryString") String blurryString,@Param(value = "begin") int begin,@Param(value = "pageSize") int pageSize);

}
