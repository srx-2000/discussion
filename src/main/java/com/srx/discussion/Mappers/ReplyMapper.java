package com.srx.discussion.Mappers;

import com.srx.discussion.Entities.Reply;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author srx
 * @description
 * @create 2020-07-29 06:42:58
 */
public interface ReplyMapper {

    /**
     * 插入一条回复
     * @param reply
     * @return
     */
    boolean insertReply(Reply reply);

    /**
     * 根据传入的commentId查询一条评论下的所有回复，包括回复的回复
     * @param targetComment
     * @param begin
     * @param pageSize
     * @return
     */
    List<Reply> paginationQueryReplyListWithComment(@Param(value = "targetComment") Integer targetComment,@Param(value = "begin") Integer begin,@Param(value = "pageSize") Integer pageSize);

    /**
     * 根据id查询指定的reply
     * @param replyId
     * @return
     */
    Reply queryReplyById(@Param("replyId") Integer replyId);

    /**
     * 根据传入的replyId查询该回复下所有回复（不一定用得到）
     * @param targetReply
     * @param begin
     * @param pageSize
     * @return
     */
    List<Reply> paginationQueryReplyListWithReply(@Param(value = "targetReply") Integer targetReply,@Param(value = "begin") Integer begin,@Param(value = "pageSize") Integer pageSize);

    /**
     * 通过传入的replyId软删除单个reply
     * @param replyId
     * @return
     */
    boolean deleteSingleReply(@Param(value = "replyId") Integer replyId);

    /**
     * 通过传入的commentId批量软删除所有与该comment相关的reply
     * @param targetComment
     * @return
     */
    boolean deleteBatchReply(@Param(value = "targetComment") Integer targetComment);

    /**
     * 根据传入的replyId查询该回复的发出人Id，用于与从session中取出的UserId做比较。两者相等或该用户拥有管理员权限才可以进行删除，
     * @param replyId
     * @return
     */
    Integer queryReplyManId(@Param(value = "replyId") Integer replyId);

}
