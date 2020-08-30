package com.srx.discussion.Services;

import com.srx.discussion.Entities.Posts;
import com.srx.discussion.Entities.User;
import com.srx.discussion.Entities.UserToRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author srx
 * @description
 * @create 2020-08-02 13:17:27
 */
public interface PostsService {

    /**
     * 通过Id查询贴吧信息
     *
     * @param postsId
     * @return
     */
    Posts queryPostsById(Integer postsId);

    /**
     * 创建贴吧，并将自增主键封装到传入的posts中返回，但是并不能将数据库中的isLive和createTime也封装进该对象
     * 所以需要通过返回的Id进行查询
     *
     * @param posts
     * @return
     */
    boolean insertPosts(Posts posts);

    /**
     * 该方法主要用来将每个posts下的普通用户更新为管理员用户（即插入status=1的UserToRole）
     *
     * @param userId
     * @param postsId
     * @return
     */
    boolean upUserRole(Integer userId, Integer postsId);

    /**
     * 该方法主要用来将每个posts下的管理员用户更新为普通用户（即删除status=1的UserToRole）
     *
     * @param userId
     * @param postsId
     * @return
     */
    boolean downUserRole(Integer userId, Integer postsId);

    /**
     * 对单个帖子进行软删除
     *
     * @param postsId
     * @return
     */
    boolean deleteSinglePosts(int postsId);


    /**
     * 通过title查询切吧
     *
     * @param postsTitle
     * @return
     */
    Posts queryPostsByTitle(String postsTitle);

    /**
     * 根据给出的id和新的贴吧名，对贴吧进行改名
     *
     * @param postsId
     * @param newPostsTitle
     * @return
     */
    boolean updatePostsTitle(Integer postsId, String newPostsTitle);

    /**
     * 通过传入的模糊查询字符串，查询所有包含该字符串的贴吧，并以列表的形式返回所有结果
     *
     * @param blurryString
     * @return
     */
    List<Posts> blurryQueryPostsList(String blurryString, Integer currentPage, Integer pageSize);

    /**
     * 该方法主要是提供用户查询一个贴吧中的所有管理员,创建者。返回的对象为一个User的list
     *
     * @param postsId
     * @return
     */
    List<User> queryRoleList(Integer postsId);

    /**
     * 该方法主要是提供用户查询一个某用户（一般是自己）所有拥有特殊角色的贴吧。返回的对象为一个posts和userToRole联合查询的结果的list
     *
     * @param userId
     * @return
     */
    List<Posts> queryPostsListWithRole(Integer userId);

    /**
     * 查询指定贴吧某用户的权限
     *
     * @param userId
     * @param postsId
     * @return
     */
    String queryStatus(Integer userId, Integer postsId);

    /**
     * 根据传入的postsId查询该贴吧的创建者Id，用于与从session中取出的UserId做比较。只有创建者用户才可以删除贴吧，
     *
     * @param postsId
     * @return
     */
    Integer queryPostManId(Integer postsId);

    /**
     * 统计贴吧数目
     *
     * @return
     */
    Integer queryPostsCount();

}
