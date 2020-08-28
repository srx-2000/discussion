package com.srx.discussion.Services;

import com.srx.discussion.Entities.Post;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author srx
 * @description
 * @create 2020-07-29 06:43:52
 */
public interface PostService {
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
    boolean updateIsLiveToDeleteByPostsId(Integer postsId);

    /**
     * 查询帖子列表，用于显示所有帖子
     * @return
     * @param postsName
     */
    List<Post> paginationQueryPostList(String postsName,Integer currentPage,Integer pageSize);

    Post queryPostById(Integer postId);
}
