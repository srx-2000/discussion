package com.srx.discussion.Controllers;

import com.srx.discussion.Entities.base.Comment;
import com.srx.discussion.Entities.base.Post;
import com.srx.discussion.Entities.base.Posts;
import com.srx.discussion.Entities.base.User;
import com.srx.discussion.Entities.hybrid.UserToRole;
import com.srx.discussion.Services.*;
import com.srx.discussion.utils.CommonControllerUtil;
import com.srx.discussion.utils.PropertiesLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author srx
 * @description
 * @create 2020-07-29 06:47:24
 */
@Controller
public class CommentController {

    private PropertiesLoader propertiesLoader = new PropertiesLoader("message.properties");
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private UserToRoleService userToRoleService;
    @Autowired
    private PostsService postsService;




    @GetMapping(value = "/p")
    @ResponseBody
    public Map<String, Object> getCommentAndPostContextWithPagination(Integer postId, Integer currentPage, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        if (postService.queryPostById(postId) != null) {
            Post post = postService.queryPostById(postId);
            Posts posts = postsService.queryPostsById(post.getPostsId());
            String PostNickname = userService.queryUserNikeName(post.getPostMan());
            map.put("postContext", post.getPostContext());
            map.put("postTitle", post.getPostTitle());
            map.put("postId",post.getPostId());
            map.put("belongPostsId",posts.getPostsId());
            map.put("belongPostsName",posts.getPostsTitle());
            map.put("postManNickname",PostNickname);
            map.put("postManId",post.getPostMan());
            List<Comment> comments = commentService.paginationQueryCommentList(postId, currentPage, pageSize);
            for (Comment comment :comments) {
                int commentManId = comment.getCommentMan();
                String commentNickname = userService.queryUserNikeName(commentManId);
                comment.setCommentManUsername(commentNickname);
            }
            map.put("commentList", comments);
        } else {
            map.put("errorMessage.nofound.post", propertiesLoader.getValue("errorMessage.nofound.post"));
        }
        return map;
    }

    @PostMapping("/insertComment")
    @ResponseBody
    public Map<String, Object> insertComment(@RequestParam String commentContext, @RequestParam Integer targetPost, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        Post post = postService.queryPostById(targetPost);
        if (post != null) {
            if (user != null) {
                int commentMan = user.getUserId();
                Comment comment = new Comment(commentContext, commentMan, targetPost);
                boolean flag = commentService.insertComment(comment);
                if (flag) {
                    Comment resultComment = commentService.queryCommentById(comment.getCommentId());
                    User commentUser = userService.queryUserById(commentMan);
                    map.put("commentContext", resultComment.getCommentContext());
                    map.put("targetPostId", resultComment.getTargetPost());
                    map.put("targetPostTitle", postService.queryPostById(resultComment.getTargetPost()).getPostTitle());
                    map.put("targetManId", post.getPostMan());
                    map.put("targetManNickname", userService.queryUserNikeName(post.getPostMan()));
                    map.put("commentManId", commentUser.getUserId());
                    map.put("commentManNickname", userService.queryUserNikeName(commentUser.getUserId()));
                    map.put("commentTime", resultComment.getCreateTime());
                } else {
                    map.put("errorMessage.fail.comment", propertiesLoader.getValue("errorMessage.fail.comment"));
                }
            } else {
                map.put("errorMessage.login", propertiesLoader.getValue("errorMessage.login"));
            }
        } else {
            map.put("errorMessage.nofound.post", propertiesLoader.getValue("errorMessage.nofound.post"));
        }
        return map;

    }

    /**
     * 这里的postsId是用来判断权限的，而非用于定位post的，/dSR也一样
     * @param postsId
     * @param commentId
     * @param request
     * @return
     */
    @GetMapping(value = "/dSC")
    @ResponseBody
    public Map<String, Object> deleteSingleComment(@RequestParam Integer postsId, @RequestParam Integer commentId, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            Integer loginUserId = user.getUserId();
            Integer commentUserId = commentService.queryCommentManId(commentId);
            if (loginUserId == commentUserId) {
                map = CommonControllerUtil.CommonController(commentService, "deleteSingleComment", commentId);
            } else {
                if (userToRoleService.queryStatus(new UserToRole(loginUserId, postsId)) != null) {
                    map=CommonControllerUtil.CommonController(commentService,"deleteSingleComment",commentId);
                } else {
                    map.put("errorMessage.authority.short", propertiesLoader.getValue("errorMessage.authority.short"));
                }
            }
        } else {
            map.put("errorMessage.login", propertiesLoader.getValue("errorMessage.login"));
        }
        return map;
    }

    @GetMapping("/getCommentAndReplyCount")
    @ResponseBody
    public Map<String,Object> getCommentAndReplyCount(@RequestParam Integer postId){
        Map<String,Object> map=new HashMap<>();
        if (postService.queryPostById(postId)!=null){
            map=CommonControllerUtil.CommonController(commentService,"queryCommentAndReplyCount",postId);
        }else {
            map.put("errorMessage.nofound.post",propertiesLoader.getValue("errorMessage.nofound.post"));
        }
        return map;
    }
}
