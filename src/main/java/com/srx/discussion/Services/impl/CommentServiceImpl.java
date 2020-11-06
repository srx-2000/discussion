package com.srx.discussion.Services.impl;

import com.srx.discussion.Entities.base.Comment;
import com.srx.discussion.Entities.base.Post;
import com.srx.discussion.Mappers.CommentMapper;
import com.srx.discussion.Mappers.PostMapper;
import com.srx.discussion.Services.CommentService;
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
 * @create 2020-07-29 06:44:50
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper mapper;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private ReplyService replyService;

    @Override
    public boolean insertComment(Comment comment) {
        return mapper.insertComment(comment);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean deleteSingleComment(Integer commentId) {
        boolean flag = replyService.deleteBatchReply(commentId);
        boolean result = mapper.deleteSingleComment(commentId);
        return result && flag;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean deleteBatchComment(Integer postId) {
        List<Comment> comments = mapper.queryCommentListByPostId(postId);
        for (Comment comment : comments) {
            replyService.deleteBatchReply(comment.getCommentId());
        }
        boolean result = mapper.deleteBatchComment(postId);
        Integer count = this.queryCommentAndReplyCount(postId);
        return (count == 0) && result;
    }


    @Override
    public Comment queryCommentById(Integer commentId) {
        return mapper.queryCommentById(commentId);
    }

    @Override
    public List<Comment> paginationQueryCommentList(Integer postId, Integer currentPage, Integer pageSize) {
        Post post = postMapper.queryPostById(postId);
        int begin = (currentPage - 1) * pageSize;
        if (post == null)
            ExceptionUtil.NullObjectException(post);
        List<Comment> comments = mapper.paginationQueryCommentList(postId, begin, pageSize);
        for (Comment c :comments) {
            c.setReplyCount(replyService.queryReplyCount(c.getCommentId()));
        }
        return comments;
    }

    @Override
    public Integer queryCommentManId(Integer commentId) {
        return mapper.queryCommentManId(commentId);
    }

    @Override
    public Integer queryCommentAndReplyCount(Integer postId) {
        Integer replyCount = mapper.queryReplyCount(postId);
        Integer commentCount = mapper.queryCommentCount(postId);
        return replyCount + commentCount;
    }
}
