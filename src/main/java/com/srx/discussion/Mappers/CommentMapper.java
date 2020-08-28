package com.srx.discussion.Mappers;

import com.srx.discussion.Entities.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author srx
 * @description
 * @create 2020-07-29 06:43:14
 */

public interface CommentMapper {
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
    Comment queryCommentById(@Param(value = "commentId") Integer commentId);

    /**
     * 通过给定的postId分页查询Comment，并返回list对象
     * @param postId
     * @return
     */
    List<Comment> paginationQueryCommentList(@Param(value = "postId") Integer postId,@Param(value = "begin") Integer begin,@Param(value = "pageSize") Integer pageSize);

    /**
     * 根据传入的commentId查询该回复的发出人Id，用于与从session中取出的UserId做比较。两者相等或该用户拥有管理员权限才可以进行删除，
     * @param commentId
     * @return
     */
    Integer queryCommentManId(@Param(value = "commentId") Integer commentId);

}
