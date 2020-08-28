package com.srx.discussion.Services.impl;

import com.srx.discussion.Entities.Posts;
import com.srx.discussion.Entities.User;
import com.srx.discussion.Entities.UserToRole;
import com.srx.discussion.Mappers.UserToRoleMapper;
import com.srx.discussion.Services.UserToRoleService;
import jdk.nashorn.internal.runtime.logging.DebugLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author srx
 * @description
 * @create 2020-08-11 22:47:28
 */
@Service
public class UserToRoleServiceImpl implements UserToRoleService {
    @Autowired
    private UserToRoleMapper mapper;

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public boolean insertRole(UserToRole userToRole) {
        boolean b = mapper.insertRole(userToRole);
        return b;

    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public boolean deleteRole(UserToRole userToRole) {
        boolean b = mapper.deleteRole(userToRole);
        return b;
    }

    @Override
    public UserToRole queryStatus(UserToRole userToRole) {
        UserToRole result = mapper.queryStatus(userToRole);
        return result;

    }

    @Override
    public List<Posts> queryPostsList(Integer userId) {
        List<Posts> userToRoles = mapper.queryPostsList(userId);
        return userToRoles;
    }

    @Override
    public List<User> queryRoleList(Integer postsId) {
        List<User> userToRoles = mapper.queryRoleList(postsId);
        return userToRoles;
    }
}
