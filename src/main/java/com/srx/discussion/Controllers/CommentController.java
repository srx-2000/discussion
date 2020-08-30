package com.srx.discussion.Controllers;

import com.srx.discussion.Entities.Comment;
import com.srx.discussion.Entities.Post;
import com.srx.discussion.Entities.User;
import com.srx.discussion.Entities.UserToRole;
import com.srx.discussion.Services.CommentService;
import com.srx.discussion.Services.PostService;
import com.srx.discussion.Services.UserService;
import com.srx.discussion.Services.UserToRoleService;
import com.srx.discussion.Services.impl.UserToRoleServiceImpl;
import com.srx.discussion.utils.CommonControllerUtil;
import com.srx.discussion.utils.PropertiesLoader;
import org.omg.PortableInterceptor.INACTIVE;
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


    @GetMapping(value = "/p")
    @ResponseBody
    public Map<String, Object> getCommentAndPostContextWithPagination(Integer postId, Integer currentPage, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        if (postService.queryPostById(postId) != null) {
            Post post = postService.queryPostById(postId);
            map.put("postContext", post.getPostContext());
            map.put("postTitle", post.getPostTitle());
            List<Comment> comments = commentService.paginationQueryCommentList(postId, currentPage, pageSize);
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
                    map.put("targetPost", resultComment.getTargetPost());
                    map.put("targetMan", post.getPostMan());
                    map.put("commentMan", commentUser.getUsername());
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
