package com.srx.discussion.Services;

import com.srx.discussion.Entities.Posts;
import com.srx.discussion.Entities.User;
import com.srx.discussion.Entities.UserToRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author srx
 * @description
 * @create 2020-08-11 22:46:09
 */

public interface UserToRoleService {
    /**
     * 添加一条关系记录,同时也肩负着把普通用户变为管理员用户的使命
     * @param userToRole
     * @return
     */
    boolean insertRole(UserToRole userToRole);

    /**
     * 删除一条关系记录，同时也肩负着把管理员用户变为普通用户的使命
     * @param userToRole
     * @return
     */
    boolean deleteRole(UserToRole userToRole);

    /**
     * 这里主要是传入userId和postsId
     * 主要作用为对单个用户在单个贴吧中的权限的查询
     * 这里查到的权限用于判断posts，post，comment，reply的删除权限
     * 如果返回的为null，则证明该用户在该贴吧中的权限为普通用户
     * @param userToRole
     * @return
     */
     UserToRole queryStatus(UserToRole userToRole);

    /**
     * 该方法主要用来提供给用户查询自己在各个贴吧中的权限
     * 一般用不到
     * @param userId
     * @return
     */
    List<Posts> queryPostsList(Integer userId) ;

    /**
     * 该方法主要用来提供给每个贴吧查询管理员一共都有谁
     * 一般用不到
     * @param postsId
     * @return
     */
    List<User> queryRoleList(Integer postsId);

}
