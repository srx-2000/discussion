package com.srx.discussion.Services.impl;

import com.srx.discussion.Entities.User;
import com.srx.discussion.Entities.UserToPost;
import com.srx.discussion.Entities.UserToPosts;
import com.srx.discussion.Enums.Regex;
import com.srx.discussion.Mappers.PostMapper;
import com.srx.discussion.Mappers.PostsMapper;
import com.srx.discussion.Mappers.UserMapper;
import com.srx.discussion.Services.UserService;
import com.srx.discussion.Services.UserToPostService;
import com.srx.discussion.Services.UserToPostsService;
import com.srx.discussion.utils.ExceptionUtil;
import com.srx.discussion.utils.RegexUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author srx
 * @description
 * @create 2020-07-29 06:44:40
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PostsMapper postsMapper;

    @Autowired
    private UserToPostsService userToPostsService;

    @Autowired
    private UserToPostService userToPostService;

    @Autowired
    private PostMapper postMapper;


    @Override
    public User queryUserByEmail(String email) {
        if (email != null && email != "")
            return userMapper.queryUserByEmail(email);
        return null;
    }

    @Override
    public boolean insertUser(User user) {
        if (user != null) {
            if (RegexUtil.commonRegex(Regex.USERNAME, user.getUsername()))
                ExceptionUtil.ErrorStringException(Regex.USERNAME, user.getUsername(), "insertUser");
            else if (RegexUtil.commonRegex(Regex.EMAIL, user.getEmail()))
                ExceptionUtil.ErrorStringException(Regex.EMAIL, user.getEmail(), "insertUser");
            return userMapper.insertUser(user);
        }
        return false;
    }

    @Override
    public String queryUserAuthorityByEmail(String email) {
        return userMapper.queryUserAuthorityByEmail(email);
    }

    @Override
    public boolean updateUserPassword(User user) {
        if (user != null) {
            if (RegexUtil.commonRegex(Regex.PASSWORD, user.getPassword()))
                ExceptionUtil.ErrorStringException(Regex.PASSWORD, user.getPassword(), "updateUserPassword");
        }
        return userMapper.updateUserPassword(user);
    }

    /**
     * @param username email 或者 username
     * @param password
     * @return
     */
    @Override
    public User login(String username, String password) {
        return userMapper.login(username, password);
    }

    /**
     * 用来查询数据库中是否含有该用户名或者email
     *
     * @param uniqueId username 或者 email
     * @return
     */
    @Override
    public User verification(String uniqueId) {
        return userMapper.verification(uniqueId);
    }

    @Override
    public User queryUserById(Integer userId) {
        return userMapper.queryUserById(userId);
    }

    @Override
    public boolean insertStarPosts(Integer userId, Integer postsId) {
        if (userMapper.queryUserById(userId) != null && postsMapper.queryPostsById(postsId) != null) {
            boolean b = userToPostsService.insertStarPosts(userId, postsId);
            return b;
        } else if (userMapper.queryUserById(userId) == null) {
            ExceptionUtil.NullObjectException(userMapper.queryUserById(userId));
        } else {
            ExceptionUtil.NullObjectException(postsMapper.queryPostsById(postsId));
        }
        return false;
    }

    @Override
    public boolean updatePostsIsStarToLive(Integer userId, Integer postsId) {
        if (userMapper.queryUserById(userId) != null && postsMapper.queryPostsById(postsId) != null) {
            boolean b = userToPostsService.updateIsStarToLive(userId, postsId);
            return b;
        } else if (userMapper.queryUserById(userId) == null) {
            ExceptionUtil.NullObjectException(userMapper.queryUserById(userId));
        } else {
            ExceptionUtil.NullObjectException(postsMapper.queryPostsById(postsId));
        }
        return false;
    }

    @Override
    public boolean updatePostsIsStarToDelete(Integer userId, Integer postsId) {
        if (userMapper.queryUserById(userId) != null && postsMapper.queryPostsById(postsId) != null) {
            boolean b = userToPostsService.updateIsStarToDelete(userId, postsId);
            return b;
        } else if (userMapper.queryUserById(userId) == null) {
            ExceptionUtil.NullObjectException(userMapper.queryUserById(userId));
        } else {
            ExceptionUtil.NullObjectException(postsMapper.queryPostsById(postsId));
        }
        return false;
    }

    @Override
    public String queryPostsIsStarStatus(Integer userId, Integer postsId) {
        if (userMapper.queryUserById(userId) != null && postsMapper.queryPostsById(postsId) != null) {
            String s = userToPostsService.queryIsStarStatus(userId, postsId);
            return s;
        } else if (userMapper.queryUserById(userId) == null) {
            ExceptionUtil.NullObjectException(userMapper.queryUserById(userId));
        } else {
            ExceptionUtil.NullObjectException(postsMapper.queryPostsById(postsId));
        }
        return null;
    }

    @Override
    public List<User> queryUserStarPosts(Integer userId) {
        if (userMapper.queryUserById(userId) != null) {
            List<User> list = userToPostsService.queryUserStarPosts(userId);
            return list;
        } else {
            ExceptionUtil.NullObjectException(userMapper.queryUserById(userId));
        }
        return null;
    }

    @Override
    public List<UserToPosts> queryUserToPostsListByUserId(Integer userId) {
        if (userMapper.queryUserById(userId) != null) {
            List<UserToPosts> list = userToPostsService.queryUserToPostsListByUserId(userId);
            return list;
        } else {
            ExceptionUtil.NullObjectException(userMapper.queryUserById(userId));
        }
        return null;
    }


    @Override
    public boolean insertStarPost(Integer userId, Integer postId) {
        if (userMapper.queryUserById(userId) != null && postMapper.queryPostById(postId) != null) {
            boolean b = userToPostService.insertStarPost(userId, postId);
            return b;
        } else if (userMapper.queryUserById(userId) == null) {
            ExceptionUtil.NullObjectException(userMapper.queryUserById(userId));
        } else {
            ExceptionUtil.NullObjectException(postMapper.queryPostById(postId));
        }
        return false;
    }

    @Override
    public boolean updatePostIsStarToLive(Integer userId, Integer postId) {
        if (userMapper.queryUserById(userId) != null && postMapper.queryPostById(postId) != null) {
            boolean b = userToPostService.updateIsStarToLive(userId, postId);
            return b;
        } else if (userMapper.queryUserById(userId) == null) {
            ExceptionUtil.NullObjectException(userMapper.queryUserById(userId));
        } else {
            ExceptionUtil.NullObjectException(postMapper.queryPostById(postId));
        }
        return false;
    }

    @Override
    public boolean updatePostIsStarToDelete(Integer userId, Integer postId) {
        if (userMapper.queryUserById(userId) != null && postMapper.queryPostById(postId) != null) {
            boolean b = userToPostService.updateIsStarToDelete(userId, postId);
            return b;
        } else if (userMapper.queryUserById(userId) == null) {
            ExceptionUtil.NullObjectException(userMapper.queryUserById(userId));
        } else {
            ExceptionUtil.NullObjectException(postMapper.queryPostById(postId));
        }
        return false;
    }

    @Override
    public String queryPostIsStarStatus(Integer userId, Integer postId) {
        if (userMapper.queryUserById(userId) != null && postMapper.queryPostById(postId) != null) {
            String s = userToPostService.queryIsStarStatus(userId, postId);
            return s;
        } else if (userMapper.queryUserById(userId) == null) {
            ExceptionUtil.NullObjectException(userMapper.queryUserById(userId));
        } else {
            ExceptionUtil.NullObjectException(postMapper.queryPostById(postId));
        }
        return null;
    }

    @Override
    public List<User> queryUserStarPost(Integer userId) {
        if (userMapper.queryUserById(userId) != null) {
            List<User> list = userToPostService.queryUserStarPost(userId);
            return list;
        } else {
            ExceptionUtil.NullObjectException(userMapper.queryUserById(userId));
        }
        return null;
    }

    @Override
    public List<UserToPost> queryUserToPostListByUserId(Integer userId) {
        if (userMapper.queryUserById(userId) != null) {
            List<UserToPost> list = userToPostService.queryUserToPostListByUserId(userId);
            return list;
        } else {
            ExceptionUtil.NullObjectException(userMapper.queryUserById(userId));
        }
        return null;
    }
}
