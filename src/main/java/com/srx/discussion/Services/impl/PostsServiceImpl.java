package com.srx.discussion.Services.impl;

import com.srx.discussion.Entities.Posts;
import com.srx.discussion.Entities.User;
import com.srx.discussion.Entities.UserToRole;
import com.srx.discussion.Enums.Regex;
import com.srx.discussion.Enums.StatusCode;
import com.srx.discussion.Enums.UserRole;
import com.srx.discussion.Mappers.PostMapper;
import com.srx.discussion.Mappers.PostsMapper;
import com.srx.discussion.Mappers.UserToRoleMapper;
import com.srx.discussion.Services.PostsService;
import com.srx.discussion.Services.UserService;
import com.srx.discussion.Services.UserToRoleService;
import com.srx.discussion.utils.ExceptionUtil;
import com.srx.discussion.utils.RegexUtil;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    private PostMapper postMapper;

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


    /**
     * 这里有待改进，首先问题是：如果在删除贴吧的时候，必须得把帖子，评论等一起删掉，但是如果这里只对贴吧和帖子进行删除，
     * 然后再在帖子的Service中开启事务删除评论的话，就会出现，在删除贴吧时候就已经把帖子删除并commit了，所以到删除评论的时候就找不到
     * 相应的帖子了，也就无法对评论和回复等继续删除。也就是说这种对数据库事务的操作无法做到级联删除。
     * 解决办法：
     * 1. 在写完comment，reply的Service之后，在该service中分别创建实例，然后通过创建的实例调用其删除方法，对其进行删除。
     * 这样在删除贴吧时就要对post，comment，reply这三个分别调用单独的删除方法【这里还需要对每个service中提供相应的单独删除的方法，
     * 这样就会导致在一个service中包含多个删除操作{单独删除，事务删除}】，然后通过posts的事务，对其进行统一删除，同样的到post，comment需要做同样的操作（不推荐）
     * 2. （还未验证可行性）给每个service中都添加事务，从reply开始，到posts结束，逐层递增事务中的删除操作。并通过spring提供的事务的传播，
     * 对事务进行由下到上的删除。即：在对posts进行删除的时候，其方法中只是对posts记录的删除，但其中同时调用了post对post本身和comment的删除的事务。
     * 并不断传递下去，直到reply中的事务，仅仅对自己删除即可。
     * <p>
     * <p>
     * <p>
     * 这里记得做权限认证
     *
     * @param postsTitle
     * @return
     */

    @Override
    @Transactional
    public boolean updateIsLiveToDelete(String postsTitle) {
        Posts deletePosts = postsMapper.queryPostsByTitle(postsTitle);
        int deletePostsId = deletePosts.getPostsId();
        boolean flag = postsMapper.updateIsLiveToDelete(postsTitle);
        if (flag) {
            boolean finish = postMapper.updateIsLiveToDeleteByPostsId(deletePostsId);
            return finish;
        }
        return false;
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
    public List<User> queryRoleList(Integer postsId) {
        Posts posts = postsMapper.queryPostsById(postsId);
        List<User> users = null;
        if (posts != null) {
            users = userToRoleService.queryRoleList(postsId);
        } else
            ExceptionUtil.NullObjectException(posts);
        return users;
    }

    @Override
    public List<Posts> queryPostsListWithRole(Integer userId) {
        List<Posts> posts = userToRoleService.queryPostsList(userId);
        return posts;
    }

}
