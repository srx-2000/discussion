package com.srx.discussion.Services.impl;

import com.srx.discussion.Entities.User;
import com.srx.discussion.Entities.UserToPost;
import com.srx.discussion.Mappers.UserToPostMapper;
import com.srx.discussion.Services.UserToPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author srx
 * @description
 * @create 2020-08-11 22:47:03
 */
@Service
public class UserToPostServiceImpl implements UserToPostService {
    @Autowired
    private UserToPostMapper mapper;

    @Override
    public boolean insertStarPost(Integer userId, Integer postId) {
        return mapper.insertStarPost(userId, postId);
    }

    @Override
    public boolean updateIsStarToLive(Integer userId, Integer postId) {
        return mapper.updateIsStarToLive(userId, postId);
    }

    @Override
    public boolean updateIsStarToDelete(Integer userId, Integer postId) {
        return mapper.updateIsStarToDelete(userId, postId);
    }

    @Override
    public String queryIsStarStatus(Integer userId, Integer postId) {
        return mapper.queryIsStarStatus(userId, postId);
    }

    @Override
    public List<User> queryUserStarPost(Integer userId) {
        return mapper.queryUserStarPost(userId);
    }

    @Override
    public List<UserToPost> queryUserToPostListByUserId(Integer userId) {
        return mapper.queryUserToPostListByUserId(userId);
    }
}
