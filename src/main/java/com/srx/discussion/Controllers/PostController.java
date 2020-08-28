package com.srx.discussion.Controllers;

import com.srx.discussion.Entities.Post;
import com.srx.discussion.Entities.User;
import com.srx.discussion.Enums.StatusCode;
import com.srx.discussion.Exceptions.ErrorStringException;
import com.srx.discussion.Services.PostService;
import com.srx.discussion.Services.PostsService;
import com.srx.discussion.utils.CommonControllerUtil;
import com.srx.discussion.utils.ExceptionUtil;
import com.srx.discussion.utils.PropertiesLoader;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author srx
 * @description
 * @create 2020-07-29 06:46:25
 */
@Controller
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private PostsService postsService;

    private PropertiesLoader propertiesLoader = new PropertiesLoader("message.properties");

    /**
     * 该接口用于查询贴吧，并返回该贴吧的所有帖子，已经包含了分页查询
     *
     * @param postsName
     * @return
     */
    @GetMapping(value = "/f")
    @ResponseBody
    public Map<String, Object> showPostLst(@RequestParam String postsName, @RequestParam int currentPage, @RequestParam int pageSize, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        try {
            if (postsService.queryPostsByTitle(postsName) != null) {
                map = CommonControllerUtil.CommonController(postService, "paginationQueryPostList", postsName, currentPage, pageSize);
            } else {
                map.put("errorMessage.nofound.posts",propertiesLoader.getValue("errorMessage.nofound.posts"));
            }
        } catch (ErrorStringException e) {
            ExceptionUtil.ResponseCatcher(response, StatusCode.NO_CONTENT, e.getMessage());
        }
        return map;
    }

    @PostMapping(value = "/insertPost")
    @ResponseBody
    public Map<String, Object> insertPost(@RequestParam Integer postMan, @RequestParam String postTitle, @RequestParam String postContext, @RequestParam Integer postsId, HttpServletResponse response, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        Post post = new Post(postContext, postMan, postTitle, postsId);
        try {
            User user = (User) request.getSession().getAttribute("user");
            if (user != null)
                map = CommonControllerUtil.CommonController(postService, "insertPost", post);
            else
                map.put("errorMessage.login", propertiesLoader.getValue("errorMessage.login"));
        } catch (ErrorStringException e) {
            ExceptionUtil.ResponseCatcher(response, StatusCode.NO_CONTENT, e.getMessage());
        }
        return map;
    }


}
