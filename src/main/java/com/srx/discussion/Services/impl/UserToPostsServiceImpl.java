package com.srx.discussion.Services.impl;

import com.srx.discussion.Entities.User;
import com.srx.discussion.Entities.UserToPosts;
import com.srx.discussion.Mappers.UserToPostsMapper;
import com.srx.discussion.Services.UserToPostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author srx
 * @description
 * @create 2020-08-11 22:46:38
 */
@Service
public class UserToPostsServiceImpl implements UserToPostsService {
    @Autowired
    private UserToPostsMapper mapper;

    @Override
    public boolean insertStarPosts(Integer userId, Integer postsId) {
        return mapper.insertStarPosts(userId,postsId);
    }

    @Override
    public boolean updateIsStarToLive(Integer userId, Integer postsId) {
        return mapper.updateIsStarToLive(userId,postsId);
    }

    @Override
    public boolean updateIsStarToDelete(Integer userId, Integer postsId) {
        return mapper.updateIsStarToDelete(userId,postsId);
    }

    @Override
    public String queryIsStarStatus(Integer userId, Integer postsId) {
        return mapper.queryIsStarStatus(userId,postsId);
    }

    @Override
    public List<User> queryUserStarPosts(Integer userId) {
        return mapper.queryUserStarPosts(userId);
    }

    @Override
    public List<UserToPosts> queryUserToPostsListByUserId(Integer userId) {
        return mapper.queryUserToPostsListByUserId(userId);
    }
}
