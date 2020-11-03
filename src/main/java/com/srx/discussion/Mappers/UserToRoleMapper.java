package com.srx.discussion.Mappers;

import com.srx.discussion.Entities.base.Posts;
import com.srx.discussion.Entities.base.User;
import com.srx.discussion.Entities.hybrid.UserToRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author srx
 * @description 由于这里的删除，定为真删除，并且普通用户不分配status，所以status的更新可以直接改为对记录的增删
 * @create 2020-08-11 22:33:52
 */
public interface UserToRoleMapper {

    /**
     * 添加一条关系记录,同时也肩负着把普通用户变为管理员用户，或创建者用户的使命
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
    List<Posts> queryPostsList(@Param(value = "userId") Integer userId) ;

    /**
     * 该方法主要用来提供给每个贴吧查询管理员一共都有谁
     * 一般用不到
     * @param postsId
     * @return
     */
    List<User> queryRoleList(@Param(value = "postsId") Integer postsId) ;



}
