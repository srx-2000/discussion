package com.srx.discussion.Services;

import com.srx.discussion.Entities.base.Comment;
import com.srx.discussion.Entities.base.Reply;

import java.util.List;

/**
 * @author srx
 * @description
 * @create 2020-07-29 06:44:14
 */
public interface ReplyService {
    /**
     * 插入一条回复
     * @param reply
     * @return
     */
    Integer insertReply(Reply reply);

    /**
     * 根据传入的commentId查询一条评论下的所有回复，包括回复的回复
     * @param targetComment
     * @param currentPage
     * @param pageSize
     * @return
     */
    List<Reply> paginationQueryReplyListWithComment(Integer targetComment, Integer currentPage,Integer pageSize);

    /**
     * 用于Android展示所有回复
     * @param targetComment
     * @return
     */
    List<Reply> queryReplyListWithComment(Integer targetComment);

    /**
     * 根据id查询指定的reply
     * @param replyId
     * @return
     */
    Reply queryReplyById(Integer replyId);

    /**
     * 根据传入的replyId查询该回复下所有回复（不一定用得到）
     * @param targetReply
     * @param currentPage
     * @param pageSize
     * @return
     */
    List<Reply> paginationQueryReplyListWithReply(Integer targetReply,Integer currentPage,Integer pageSize);

    /**
     * 通过传入的replyId软删除单个reply
     * @param replyId
     * @return
     */
    boolean deleteSingleReply(Integer replyId);

    /**
     * 通过传入的commentId批量软删除所有与该comment相关的reply
     * @param targetComment
     * @return
     */
    boolean deleteBatchReply(Integer targetComment);

    /**
     * 根据传入的replyId查询该回复的发出人Id，用于与从session中取出的UserId做比较。两者相等或该用户拥有管理员权限才可以进行删除，
     * @param replyId
     * @return
     */
    Integer queryReplyManId(Integer replyId);

    /**
     * 用于查询一条评论下的回复数目
     * @param commentId
     * @return
     */
    Integer queryReplyCount(Integer commentId);

    List<Reply> queryReplyListByUserId(Integer userId);

}
