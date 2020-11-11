package com.srx.discussion.Services.impl;

import com.srx.discussion.Entities.base.Post;
import com.srx.discussion.Entities.base.Posts;
import com.srx.discussion.Entities.hybrid.HybridPost;
import com.srx.discussion.Enums.Regex;
import com.srx.discussion.Mappers.PostMapper;
import com.srx.discussion.Mappers.PostsMapper;
import com.srx.discussion.Services.CommentService;
import com.srx.discussion.Services.PostService;
import com.srx.discussion.Services.PostsService;
import com.srx.discussion.Services.UserService;
import com.srx.discussion.utils.ExceptionUtil;
import com.srx.discussion.utils.RegexUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author srx
 * @description
 * @create 2020-07-29 06:45:32
 */
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostMapper postMapper;

    @Autowired
    private PostsMapper postsMapper;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private PostsService postsService;

    @Override
    public boolean insertPost(Post post) {
        if (RegexUtil.commonRegex(Regex.ENTITY_CONTEXT, post.getPostTitle()))
            ExceptionUtil.ErrorStringException(Regex.ENTITY_CONTEXT, post.getPostTitle(), "insertPost_title");
        else {
            return postMapper.insertPost(post);
        }
        return false;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean deleteBatchPost(int postsId) {
        List<Post> posts = postMapper.queryPostListByPostsId(postsId);
        int count = 0;
        for (Post post : posts) {
            commentService.deleteBatchComment(post.getPostId());
            count += commentService.queryCommentAndReplyCount(post.getPostId());
        }
        boolean result = postMapper.deleteBatchPost(postsId);
        int counts = this.queryPostCount(postsId) + count;
        return (counts == 0) && result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean deleteSinglePost(int postId) {
        boolean flag = commentService.deleteBatchComment(postId);
        boolean result = postMapper.deleteSinglePost(postId);
        return flag && result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<HybridPost> paginationQueryPostList(Integer postsId, Integer currentPage, Integer pageSize) {
        int begin = (currentPage - 1) * pageSize;
        ArrayList<HybridPost> hybridPostList = new ArrayList<>();
        List<Post> postList = postMapper.paginationQueryPostList(postsId,begin, pageSize);
        for (Post post : postList) {
            String nickname = userService.queryUserNikeName(post.getPostMan());
            String postsTitle = postsService.queryPostsById(post.getPostsId()).getPostsTitle();
            Integer commentAndReplyCount = commentService.queryCommentAndReplyCount(post.getPostId());
            HybridPost hybridPost = new HybridPost(post.getPostId(), post.getPostMan(), post.getPostsId(), post.getPostContext(), post.getCreateTime(), post.getPostTitle(), nickname, postsTitle, commentAndReplyCount, post.getIsLive());
            hybridPostList.add(hybridPost);
        }
        return hybridPostList;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<HybridPost> paginationQueryAllPostList(Integer currentPage, Integer pageSize) {
        int begin = (currentPage - 1) * pageSize;
        ArrayList<HybridPost> hybridPostList = new ArrayList<>();
        List<Post> postList = postMapper.paginationQueryAllPostList(begin, pageSize);
        for (Post post : postList) {
            String nickname = userService.queryUserNikeName(post.getPostMan());
            String postsTitle = postsService.queryPostsById(post.getPostsId()).getPostsTitle();
            Integer commentAndReplyCount = commentService.queryCommentAndReplyCount(post.getPostId());
            HybridPost hybridPost = new HybridPost(post.getPostId(), post.getPostMan(), post.getPostsId(), post.getPostContext(), post.getCreateTime(), post.getPostTitle(), nickname, postsTitle, commentAndReplyCount, post.getIsLive());
            hybridPostList.add(hybridPost);
        }
        return hybridPostList;
    }


    @Override
    public Post queryPostById(Integer postId) {
        return postMapper.queryPostById(postId);
    }

    @Override
    public Integer queryPostManId(Integer postId) {
        return postMapper.queryPostManId(postId);
    }

    @Override
    public Integer queryPostCount(Integer postsId) {
        return postMapper.queryPostCount(postsId);
    }

    @Override
    public List<Post> queryPostListByUserId(Integer userId) {
        return postMapper.queryPostListByUserId(userId);
    }
}
