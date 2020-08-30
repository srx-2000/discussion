package com.srx.discussion.Services;

import com.srx.discussion.Entities.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author srx
 * @description
 * @create 2020-07-29 06:44:03
 */
public interface CommentService {
    /**
     * 插入一条评论
     *
     * @param comment
     * @return
     */
    boolean insertComment(Comment comment);

    /**
     * 通过传入的commentId软删除单个comment
     *
     * @param commentId
     * @return
     */
    boolean deleteSingleComment(Integer commentId);

    /**
     * 通过传入的postId软删除旗下的所有comment
     *
     * @param postId
     * @return
     */
    boolean deleteBatchComment(Integer postId);

    /**
     * 通过id查询指定存在的comment
     *
     * @param commentId
     * @return
     */
    Comment queryCommentById(Integer commentId);

    /**
     * 通过给定的postId分页查询Comment，并返回list对象
     *
     * @param postId
     * @return
     */
    List<Comment> paginationQueryCommentList(Integer postId, Integer currentPage, Integer pageSize);

    /**
     * 通过传入的commentId检索，返回评论人的id，用于在对comment进行删除的时候做权限认证
     *
     * @param commentId
     * @return
     */
    Integer queryCommentManId(Integer commentId);

    /**
     * 通过传入的postId，查询该帖子中所有的回复以及评论的个数
     * @param postId
     * @return
     */
    Integer queryCommentAndReplyCount(Integer postId);
}
