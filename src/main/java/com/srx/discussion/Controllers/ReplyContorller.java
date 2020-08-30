package com.srx.discussion.Controllers;

import com.srx.discussion.Entities.*;
import com.srx.discussion.Services.CommentService;
import com.srx.discussion.Services.ReplyService;
import com.srx.discussion.Services.UserService;
import com.srx.discussion.Services.UserToRoleService;
import com.srx.discussion.Services.impl.ReplyServiceImpl;
import com.srx.discussion.Services.impl.UserToRoleServiceImpl;
import com.srx.discussion.utils.CommonControllerUtil;
import com.srx.discussion.utils.PropertiesLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author srx
 * @description
 * @create 2020-07-29 06:47:04
 */
@Controller
public class ReplyContorller {

    private PropertiesLoader propertiesLoader = new PropertiesLoader("message.properties");
    @Autowired
    private ReplyService replyService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserToRoleService userToRoleService;


    @PostMapping(value = "/ICReply")
    @ResponseBody
    public Map<String, Object> insertReply(@RequestParam String replyContext, @RequestParam Integer targetComment, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        User user = (User) request.getSession().getAttribute("User");
        Comment comment = commentService.queryCommentById(targetComment);
        if (user != null) {
            int replyMan = user.getUserId();
            if (comment != null) {
                Reply replyToComment = new Reply(replyMan, targetComment, replyContext);
                boolean flag = replyService.insertReply(replyToComment);
                if (flag) {
                    Reply replyResult = replyService.queryReplyById(replyToComment.getReplyId());
                    User replyUser = userService.queryUserById(replyMan);
                    map.put("replyContext", replyResult.getReplyContext());
                    map.put("targetComment", replyResult.getTargetComment());
                    map.put("targetMan", comment.getCommentMan());
                    map.put("replyMan", replyUser.getUsername());
                    map.put("createTime", replyResult.getCreateTime());
                } else {
                    map.put("errorMessage.fail.reply", propertiesLoader.getValue("errorMessage.fail.reply"));
                }
            } else {
                map.put("errorMessage.nofound.comment", propertiesLoader.getValue("errorMessage.nofound.comment"));
            }
        } else {
            map.put("errorMessage.login", propertiesLoader.getValue("errorMessage.login"));
        }
        return map;
    }

    /**
     * 对上面insert targetComment接口的重载。用于插入回复的回复
     *
     * @param replyContext
     * @param targetComment
     * @param targetReply
     * @param request
     * @return
     */
    @PostMapping(value = "/IRReply")
    @ResponseBody
    public Map<String, Object> insertReply(@RequestParam String replyContext, @RequestParam Integer targetComment, @RequestParam Integer targetReply, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        User user = (User) request.getSession().getAttribute("User");
        Comment comment = commentService.queryCommentById(targetComment);
        if (user != null) {
            int replyMan = user.getUserId();
            if (comment != null) {
                Reply replyToReply = new Reply(replyMan, targetComment, targetReply, replyContext);
                if (replyService.queryReplyById(targetReply) != null) {
                    boolean flag = replyService.insertReply(replyToReply);
                    if (flag) {
                        Reply replyResult = replyService.queryReplyById(replyToReply.getReplyId());
                        User replyUser = userService.queryUserById(replyMan);
                        map.put("replyContext", replyResult.getReplyContext());
                        map.put("targetComment", replyResult.getTargetComment());
                        map.put("targetReply", replyResult.getTargetReply());
                        map.put("targetMan", comment.getCommentMan());
                        map.put("replyMan", replyUser.getUsername());
                        map.put("replyTime", replyResult.getCreateTime());
                    } else {
                        map.put("errorMessage.fail.reply", propertiesLoader.getValue("errorMessage.fail.reply"));
                    }
                } else {
                    map.put("errorMessage.nofound.reply", propertiesLoader.getValue("errorMessage.nofound.reply"));
                }
            } else {
                map.put("errorMessage.nofound.comment", propertiesLoader.getValue("errorMessage.nofound.comment"));
            }
        } else {
            map.put("errorMessage.login", propertiesLoader.getValue("errorMessage.login"));
        }
        return map;
    }

    @GetMapping(value = "/c")
    @ResponseBody
    public Map<String, Object> getReplyList(Integer targetComment, Integer currentPage, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        Comment comment = commentService.queryCommentById(targetComment);
        if (comment != null) {
            List<Reply> replyList = replyService.paginationQueryReplyListWithComment(targetComment, currentPage, pageSize);
            map.put("replyList", replyList);
        } else {
            map.put("errorMessage.nofound.comment", propertiesLoader.getValue("errorMessage.nofound.comment"));
        }
        return map;
    }
    @GetMapping(value = "/getReplyCount")
    @ResponseBody
    public Map<String,Object> getReplyCount(@RequestParam Integer commentId){
        Map<String,Object> map=new HashMap<>();
        if (commentService.queryCommentById(commentId)!=null){
            map=CommonControllerUtil.CommonController(replyService,"queryReplyCount",commentId);
        }else {
            map.put("errorMessage.nofound.comment",propertiesLoader.getValue("errorMessage.nofound.comment"));
        }
        return map;
    }
    @GetMapping(value = "/dSR")
    @ResponseBody
    public Map<String,Object> deleteSingleReply(@RequestParam Integer postsId,@RequestParam Integer replyId,HttpServletRequest request){
        Map<String,Object> map=new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        if (user!=null){
            int loginUserId = user.getUserId();
            Integer replyManId = replyService.queryReplyManId(replyId);
            if(loginUserId==replyManId){
                map=CommonControllerUtil.CommonController(replyService,"deleteSingleReply",replyId);
            }else {
                if(userToRoleService.queryStatus(new UserToRole(loginUserId, postsId))!=null){
                    map=CommonControllerUtil.CommonController(replyService,"deleteSingleReply",replyId);
                }else {
                    map.put("errorMessage.authority.short",propertiesLoader.getValue("errorMessage.authority.short"));
                }
            }
        }else {
            map.put("errorMessage.login", propertiesLoader.getValue("errorMessage.login"));
        }
        return map;
    }
}
