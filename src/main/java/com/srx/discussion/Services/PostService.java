package com.srx.discussion.Services;

import com.srx.discussion.Entities.base.Post;
import com.srx.discussion.Entities.hybrid.HybridPost;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author srx
 * @description
 * @create 2020-07-29 06:43:52
 */
public interface PostService {
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
    boolean deleteBatchPost(int postsId);

    /**
     * 对单个帖子进行软删除
     *
     * @param postId
     * @return
     */
    boolean deleteSinglePost(int postId);


    /**
     * 查询帖子列表，用于显示所有帖子
     *
     * @param postsId
     * @return
     */
    List<HybridPost> paginationQueryPostList(Integer postsId, Integer currentPage, Integer pageSize);


    @Transactional(propagation = Propagation.REQUIRED)
    List<HybridPost> paginationQueryAllPostList(Integer currentPage, Integer pageSize);

    /**
     * 通过传入的id查找单个post
     *
     * @param postId
     * @return
     */
    Post queryPostById(Integer postId);

    /**
     * 根据传入的postId查询该回复的发出人Id，用于与从session中取出的UserId做比较。两者相等或该用户拥有管理员权限才可以进行删除，
     *
     * @param postId
     * @return
     */
    Integer queryPostManId(Integer postId);

    /**
     * 返回一个贴吧中的所有帖子的数目
     *
     * @param postsId
     * @return
     */
    Integer queryPostCount(Integer postsId);

}
