package com.srx.discussion.Services.impl;

import com.srx.discussion.Entities.base.Posts;
import com.srx.discussion.Entities.base.User;
import com.srx.discussion.Entities.hybrid.UserToInfo;
import com.srx.discussion.Entities.hybrid.UserToRole;
import com.srx.discussion.Enums.Regex;
import com.srx.discussion.Enums.UserRole;
import com.srx.discussion.Mappers.PostsMapper;
import com.srx.discussion.Services.PostService;
import com.srx.discussion.Services.PostsService;
import com.srx.discussion.Services.UserService;
import com.srx.discussion.Services.UserToRoleService;
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
 * @create 2020-08-02 13:18:00
 */
@Service
public class PostsServiceImpl implements PostsService {
    @Autowired
    private PostsMapper postsMapper;

    @Autowired
    private PostService postService;

    /**
     * 做事务的传播用,以及权限认证
     */
    @Autowired
    private UserToRoleService userToRoleService;

    @Autowired
    private UserService userService;

    @Override
    public Posts queryPostsById(Integer postsId) {
        return postsMapper.queryPostsById(postsId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insertPosts(Posts posts) {
        if (posts != null) {
            if (RegexUtil.commonRegex(Regex.ENTITY_CONTEXT, posts.getPostsTitle()))
                ExceptionUtil.ErrorStringException(Regex.ENTITY_CONTEXT, posts.getPostsTitle(), "insertPosts");
            Posts flagPosts = postsMapper.queryPostsByTitle(posts.getPostsTitle());
            if (flagPosts != null)
                ExceptionUtil.RepeatInsertException(flagPosts);
            else {
                boolean flag = postsMapper.insertPosts(posts);//这里先调用postsMapper中的insertPosts将posts插入表中之后，再通过事务传播在user_to_role表中插入status记录
                int postsId = posts.getPostsId();
                int postsMan = posts.getPostsMan();
                if (flag)
                    return userToRoleService.insertRole(new UserToRole(postsMan, postsId, "2"));//创建posts的人的权限为 2，即：创建者
            }
        }
        return false;
    }

    /**
     * 此下的两个方法主要是对userToRole表的修改操作，但对于一个用户的角色修改应该基于posts，所以，暂时放到postsService中处理
     * 在写接口时，同样是加在postsController中的
     */

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean upUserRole(Integer userId, Integer postsId) {
        if (postsMapper.queryPostsById(postsId) == null)
            ExceptionUtil.NullObjectException(postsMapper.queryPostsById(postsId));
        else if (userService.queryUserById(userId) == null)
            ExceptionUtil.NullObjectException(userService.queryUserById(userId));
        return userToRoleService.insertRole(new UserToRole(userId, postsId, "1"));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean downUserRole(Integer userId, Integer postsId) {
        if (postsMapper.queryPostsById(postsId) == null)
            ExceptionUtil.NullObjectException(postsMapper.queryPostsById(postsId));
        else if (userService.queryUserById(userId) == null)
            ExceptionUtil.NullObjectException(userService.queryUserById(userId));
        return userToRoleService.deleteRole(new UserToRole(userId, postsId));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean deleteSinglePosts(int postsId) {
        boolean flag = postService.deleteBatchPost(postsId);
        boolean result = postsMapper.deleteSinglePosts(postsId);
        return flag && result;
    }


    @Override
    public String queryStatus(Integer userId, Integer postsId) {
        if (postsMapper.queryPostsById(postsId) == null)
            ExceptionUtil.NullObjectException(postsMapper.queryPostsById(postsId));
        else if (userService.queryUserById(userId) == null)
            ExceptionUtil.NullObjectException(userService.queryUserById(userId));
        else {
            UserToRole userToRole = userToRoleService.queryStatus(new UserToRole(userId, postsId));
            if (userToRole != null)
                return userToRole.getStatus();
        }
        return null;
    }

    @Override
    public Integer queryPostManId(Integer postsId) {
        return postsMapper.queryPostManId(postsId);
    }

    @Override
    public Integer queryPostsCount() {
        return postsMapper.queryPostsCount();
    }


    @Override
    public Posts queryPostsByTitle(String postsTitle) {
        if (RegexUtil.commonRegex(Regex.ENTITY_CONTEXT, postsTitle))
            ExceptionUtil.ErrorStringException(Regex.ENTITY_CONTEXT, postsTitle, "queryPostsByTitle");
        return postsMapper.queryPostsByTitle(postsTitle);
    }

    @Override
    public boolean updatePostsTitle(Integer postsId, String newPostsTitle) {
        if (RegexUtil.commonRegex(Regex.ENTITY_CONTEXT, newPostsTitle))
            ExceptionUtil.ErrorStringException(Regex.ENTITY_CONTEXT, newPostsTitle, "updatePostsTitle");
        else {
            Posts posts = postsMapper.queryPostsById(postsId);
            UserToRole userToRole = userToRoleService.queryStatus(new UserToRole(posts.getPostsMan(), postsId));
            if (userToRole.getStatus().equals(UserRole.CREATE_USER.getCode()))
                return postsMapper.updatePostsTitle(postsId, newPostsTitle);
        }
        return false;
    }


    @Override
    public List<Posts> blurryQueryPostsList(String blurryString, Integer currentPage, Integer pageSize) {
        int begin = (currentPage - 1) * pageSize;
        if (RegexUtil.commonRegex(Regex.ENTITY_CONTEXT, blurryString))
            ExceptionUtil.ErrorStringException(Regex.ENTITY_CONTEXT, blurryString, "blurryQueryPostsList");
        return postsMapper.blurryQueryPostsList(blurryString, begin, pageSize);
    }

    @Override
    public List<UserToInfo> queryRoleList(Integer postsId) {
        Posts posts = postsMapper.queryPostsById(postsId);
        List<UserToInfo> userToInfoList = new ArrayList<>();
        if (posts != null) {
            List<User> users = userToRoleService.queryRoleList(postsId);
            for (User u :users) {
                UserToInfo userToInfo = userService.queryUserInfoById(u.getUserId());
                userToInfoList.add(userToInfo);
            }

        } else
            ExceptionUtil.NullObjectException(posts);
        return userToInfoList;
    }

    @Override
    public List<Posts> queryPostsListWithRole(Integer userId) {
        List<Posts> posts = userToRoleService.queryPostsList(userId);
        return posts;
    }

}
