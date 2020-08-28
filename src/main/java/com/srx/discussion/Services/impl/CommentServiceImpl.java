package com.srx.discussion.Services.impl;

import com.srx.discussion.Entities.Comment;
import com.srx.discussion.Entities.Post;
import com.srx.discussion.Exceptions.NullObjectException;
import com.srx.discussion.Mappers.CommentMapper;
import com.srx.discussion.Mappers.PostMapper;
import com.srx.discussion.Services.CommentService;
import com.srx.discussion.utils.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

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

    @Override
    public boolean insertComment(Comment comment) {
        return mapper.insertComment(comment);
    }

    @Override
    public boolean deleteComment() {
        return false;
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
        return mapper.paginationQueryCommentList(postId, begin, pageSize);
    }
}
