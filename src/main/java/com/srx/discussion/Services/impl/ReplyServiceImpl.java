package com.srx.discussion.Services.impl;

import com.srx.discussion.Entities.Reply;
import com.srx.discussion.Mappers.CommentMapper;
import com.srx.discussion.Mappers.ReplyMapper;
import com.srx.discussion.Services.ReplyService;
import com.srx.discussion.utils.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author srx
 * @description
 * @create 2020-07-29 06:45:58
 */
@Service
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    private ReplyMapper replyMapper;
    @Autowired
    private CommentMapper commentMapper;


    @Override
    public boolean insertReply(Reply reply) {
        return replyMapper.insertReply(reply);
    }

    @Override
    public List<Reply> paginationQueryReplyListWithComment(Integer targetComment, Integer currentPage, Integer pageSize) {
        int begin = (currentPage - 1) * pageSize;
        if (commentMapper.queryCommentById(targetComment) == null)
            ExceptionUtil.NullObjectException(commentMapper.queryCommentById(targetComment));
        return replyMapper.paginationQueryReplyListWithComment(targetComment, begin, pageSize);
    }

    @Override
    public Reply queryReplyById(Integer replyId) {
        return replyMapper.queryReplyById(replyId);
    }

    @Override
    public List<Reply> paginationQueryReplyListWithReply(Integer targetReply, Integer currentPage, Integer pageSize) {
        int begin = (currentPage - 1) * pageSize;
        if (replyMapper.queryReplyById(targetReply) == null)
            ExceptionUtil.NullObjectException(replyMapper.queryReplyById(targetReply));
        return replyMapper.paginationQueryReplyListWithComment(targetReply, begin, pageSize);
    }

    @Override
    public boolean deleteSingleReply(Integer replyId) {
        return deleteSingleReply(replyId);
    }

    /**
     * 该方法主要用在commentService中进行事务的传递，用来从上到下的批量删除
     * @param targetComment
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean deleteBatchReply(Integer targetComment) {
        return replyMapper.deleteBatchReply(targetComment);
    }

    @Override
    public Integer queryReplyManId(Integer replyId) {
        return replyMapper.queryReplyManId(replyId);
    }

    @Override
    public Integer queryReplyCount(Integer commentId) {
        if (commentMapper.queryCommentById(commentId) == null) {
            ExceptionUtil.NullObjectException(commentMapper.queryCommentById(commentId));
        }
        Integer replyCount = replyMapper.queryReplyCount(commentId);
        if (replyCount <= 0)
            ExceptionUtil.NumberLessThanZeroException(replyCount);
        return replyCount;
    }
}
