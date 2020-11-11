package com.srx.discussion.Mappers;

import com.srx.discussion.Entities.base.Comment;
import com.srx.discussion.Entities.base.Post;
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
    Comment queryCommentById(@Param(value = "commentId") Integer commentId);

    /**
     * 通过给定的postId分页查询Comment，并返回list对象
     *
     * @param postId
     * @return
     */
    List<Comment> paginationQueryCommentList(@Param(value = "postId") Integer postId, @Param(value = "begin") Integer begin, @Param(value = "pageSize") Integer pageSize);

    /**
     * 根据传入的commentId查询该回复的发出人Id，用于与从session中取出的UserId做比较。两者相等或该用户拥有管理员权限才可以进行删除，
     *
     * @param commentId
     * @return
     */
    Integer queryCommentManId(@Param(value = "commentId") Integer commentId);

    /**
     * 通过传入的postId，对reply表和comment表进行连接查询，获取该post中的reply个数，用于Service层的queryCommentAndReplyCount方法
     *
     * @param postId
     * @return
     */
    Integer queryReplyCount(@Param(value = "postId") Integer postId);

    /**
     * 通过传入的postId，获取该post中的comment个数，用于Service层的queryCommentAndReplyCount方法
     *
     * @param postId
     * @return
     */
    Integer queryCommentCount(@Param(value = "postId") Integer postId);

    /**
     * 返回所有target_post=postId and is_live=1的记录，用于批量删除时对子删除的遍历
     * @param postId
     * @return
     */
    List<Comment> queryCommentListByPostId(@Param(value = "postId") Integer postId);

    /**
     * 通过用户id查找该用户发送的所有的评论，用于信息通知
     * @param userId
     * @return
     */
    List<Comment> queryCommentListByUserId(@Param(value = "userId") Integer userId);


}
