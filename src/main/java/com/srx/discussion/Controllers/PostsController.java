package com.srx.discussion.Controllers;

import com.fasterxml.jackson.core.json.async.NonBlockingJsonParser;
import com.srx.discussion.Entities.Posts;
import com.srx.discussion.Entities.User;
import com.srx.discussion.Entities.UserToRole;
import com.srx.discussion.Enums.StatusCode;
import com.srx.discussion.Exceptions.ErrorStringException;
import com.srx.discussion.Services.PostsService;
import com.srx.discussion.Services.UserService;
import com.srx.discussion.Services.UserToRoleService;
import com.srx.discussion.Services.impl.UserToRoleServiceImpl;
import com.srx.discussion.utils.CommonControllerUtil;
import com.srx.discussion.utils.ExceptionUtil;
import com.srx.discussion.utils.PropertiesLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author srx
 * @description
 * @create 2020-08-02 13:15:55
 */
@Controller
public class PostsController {
    @Autowired
    private PostsService postsService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserToRoleService userToRoleService;
    PropertiesLoader propertiesLoader = new PropertiesLoader("message.properties");

    @GetMapping(value = "/search")
    @ResponseBody
    public Map<String, Object> blurryQueryPostsList(@RequestParam String blurryString, @RequestParam Integer currentPage, @RequestParam Integer pageSize, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = CommonControllerUtil.CommonController(postsService, "blurryQueryPostsList", blurryString, currentPage, pageSize);
        } catch (ErrorStringException e) {
            ExceptionUtil.ResponseCatcher(response, StatusCode.NO_CONTENT, e.getMessage());
        }
        return map;
    }

    @GetMapping(value = "/updatePostsName")
    @ResponseBody
    public Map<String, Object> updatePostsName(@RequestParam int postsId, @RequestParam String newPostsTitle, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            try {
                int loginUser = user.getUserId();
                Integer postsUserId = postsService.queryPostManId(postsId);
                if (loginUser == postsUserId)
                    map = CommonControllerUtil.CommonController(postsService, "updatePostsTitle", postsId, newPostsTitle);
                else {
                    map.put("errorMessage.authority.short", propertiesLoader.getValue("errorMessage.authority.short"));
                }
            } catch (ErrorStringException e) {
                map.put("errorMessage.errorString.postsname", propertiesLoader.getValue("errorMessage.errorString.postsname"));
            }
        } else {
            map.put("errorMessage.login", propertiesLoader.getValue("errorMessage.login"));
        }
        return map;
    }

    @GetMapping(value = "/insertPosts")
    @ResponseBody
    public Map<String, Object> insertPosts(@RequestParam String postsTitle, @RequestParam int postsMan, HttpServletResponse response, HttpServletRequest request) {
        Posts posts = new Posts(postsTitle, postsMan);
        Map<String, Object> map = new HashMap<>();
        try {
            User user = (User) request.getSession().getAttribute("user");
            if (user != null) {
                map = CommonControllerUtil.CommonController(postsService, "insertPosts", posts);
            } else {
                map.put("errorMessage.login", propertiesLoader.getValue("errorMessage.login"));
            }
        } catch (ErrorStringException e) {
            ExceptionUtil.ResponseCatcher(response, StatusCode.NO_CONTENT, e.getMessage());
        }
        return map;
    }

    @GetMapping(value = "/dSPosts")
    @ResponseBody
    public Map<String, Object> deleteSinglePosts(@RequestParam Integer postsId, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            Integer loginUserId = user.getUserId();
            Integer postsUserId = postsService.queryPostManId(postsId);
            if (loginUserId == postsUserId) {
                map = CommonControllerUtil.CommonController(postsService, "deleteSinglePosts", postsId);
            } else {
                map.put("errorMessage.authority.short", propertiesLoader.getValue("errorMessage.authority.short"));
            }
        } else {
            map.put("errorMessage.login", propertiesLoader.getValue("errorMessage.login"));
        }
        return map;
    }


    @GetMapping(value = "/queryPostsByTitle")
    @ResponseBody
    public Map<String, Object> queryPostsByTitle(@RequestParam String postsTitle, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = CommonControllerUtil.CommonController(postsService, "queryPostsByTitle", postsTitle);
        } catch (ErrorStringException e) {
            ExceptionUtil.ResponseCatcher(response, StatusCode.NO_CONTENT, e.getMessage());
        }
        return map;
    }

    @GetMapping(value = "/upUserRole")
    @ResponseBody
    public Map<String, Object> upUserRole(@RequestParam Integer upUserId, @RequestParam Integer postsId, HttpServletResponse response, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            if (postsService.queryStatus(user.getUserId(), postsId) == null || postsService.queryStatus(user.getUserId(), postsId).equals("1"))
                map.put("errorMessage.authority.short", propertiesLoader.getValue("errorMessage.authority.short"));
            else
                map = CommonControllerUtil.CommonController(postsService, "upUserRole", upUserId, postsId);
        } else {
            map.put("errorMessage.login", propertiesLoader.getValue("errorMessage.login"));
        }
        return map;
    }

    @GetMapping(value = "/downUserRole")
    @ResponseBody
    public Map<String, Object> downUserRole(@RequestParam Integer downUserId, @RequestParam Integer postsId, HttpServletResponse response, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            if (user.getUserId() == downUserId)
                map.put("errorMessage.authority.downgrade", propertiesLoader.getValue("errorMessage.authority.downgrade"));
            else if (postsService.queryStatus(user.getUserId(), postsId) == null || postsService.queryStatus(user.getUserId(), postsId).equals("1"))
                map.put("errorMessage.authority.short", propertiesLoader.getValue("errorMessage.authority.short"));
            else
                map = CommonControllerUtil.CommonController(postsService, "downUserRole", downUserId, postsId);
        } else {
            map.put("errorMessage.login", propertiesLoader.getValue("errorMessage.login"));
        }
        return map;
    }

    @GetMapping(value = "/getPostsListWithRole")
    @ResponseBody
    public Map<String, Object> getPostsListWithRole(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            int userId = user.getUserId();
            if (userService.queryUserById(userId) != null) {
                map = CommonControllerUtil.CommonController(postsService, "queryPostsListWithRole", userId);
            } else {
                map.put("errorMessage.nofound.user", propertiesLoader.getValue("errorMessage.nofound.user"));
            }
        } else {
            map.put("errorMessage.login", propertiesLoader.getValue("errorMessage.login"));
        }
        return map;
    }

    @GetMapping(value = "/getRoleList")
    @ResponseBody
    public Map<String, Object> getRoleList(@RequestParam Integer postsId) {
        Map<String, Object> map = new HashMap<>();
        if (postsService.queryPostsById(postsId) != null) {
            map = CommonControllerUtil.CommonController(postsService, "queryRoleList", postsId);
        } else {
            map.put("errorMessage.nofound.posts", propertiesLoader.getValue("errorMessage.nofound.posts"));
        }
        return map;
    }

    @GetMapping(value = "/getPostsCount")
    @ResponseBody
    public Map<String, Object> getPostsCount() {
        Map<String, Object> map = new HashMap<>();
        map = CommonControllerUtil.CommonController(postsService, "queryPostsCount");
        return map;
    }
}
