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
     * @param comment
     * @return
     */
    boolean insertComment(Comment comment);

    boolean deleteComment();

    /**
     * 通过id查询指定存在的comment
     * @param commentId
     * @return
     */
    Comment queryCommentById(Integer commentId);

    /**
     * 通过给定的postId分页查询Comment，并返回list对象
     * @param postId
     * @return
     */
    List<Comment> paginationQueryCommentList(Integer postId,Integer currentPage, Integer pageSize);

}
