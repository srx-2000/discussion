package com.srx.discussion.Services.impl;

import com.srx.discussion.Entities.Post;
import com.srx.discussion.Entities.Posts;
import com.srx.discussion.Enums.Regex;
import com.srx.discussion.Mappers.PostMapper;
import com.srx.discussion.Mappers.PostsMapper;
import com.srx.discussion.Services.PostService;
import com.srx.discussion.utils.ExceptionUtil;
import com.srx.discussion.utils.RegexUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public boolean updateIsLiveToDeleteByPostsId(Integer postsId) {
        return false;
    }

    @Override
    public List<Post> paginationQueryPostList(String postsName, Integer currentPage, Integer pageSize) {
        Posts posts = postsMapper.queryPostsByTitle(postsName);
        if (posts==null)
            ExceptionUtil.NullObjectException(posts);
        int postsId = posts.getPostsId();
        int begin = (currentPage - 1) * pageSize;
        return postMapper.paginationQueryPostList(postsId, begin, pageSize);
    }
    @Override
    public Post queryPostById(Integer postId) {
        return postMapper.queryPostById(postId);
    }
}
