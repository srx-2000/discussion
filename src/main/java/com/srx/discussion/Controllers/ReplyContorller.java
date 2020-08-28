package com.srx.discussion.Controllers;

import com.srx.discussion.Entities.Reply;
import com.srx.discussion.Entities.User;
import com.srx.discussion.Services.CommentService;
import com.srx.discussion.Services.ReplyService;
import com.srx.discussion.Services.UserService;
import com.srx.discussion.Services.impl.ReplyServiceImpl;
import com.srx.discussion.utils.PropertiesLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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

    @PostMapping(value = "")
    @ResponseBody
    public Map<String, Object> insertReply(@RequestParam String replyContext, @RequestParam Integer targetComment, @RequestParam Integer targetReply, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        User user = (User) request.getSession().getAttribute("User");
        if (user != null) {
            int replyMan = user.getUserId();
            if (commentService.queryCommentById(targetComment) != null) {
                Reply replyToComment = new Reply(replyMan, targetComment, replyContext);
                if (replyService.queryReplyById(targetReply) != null) {

                } else {
                    map.put("errorMessage.nofound.reply", propertiesLoader.getValue("errorMessage.nofound.reply"));
                }
            } else {
                map.put("errorMessage.nofound.comment", propertiesLoader.getValue("errorMessage.nofound.comment"));
            }
        } else {
            map.put("errorMessage.login", propertiesLoader.getValue("errorMessage.login"));
        }
        System.out.println("fadfa");
        return map;
    }


}
