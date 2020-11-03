package com.srx.discussion.Controllers;

import com.srx.discussion.Entities.base.Pyq;
import com.srx.discussion.Entities.base.User;
import com.srx.discussion.Entities.hybrid.UserToInfo;
import com.srx.discussion.Exceptions.ErrorStringException;
import com.srx.discussion.Services.PostService;
import com.srx.discussion.Services.PostsService;
import com.srx.discussion.Services.UserService;
import com.srx.discussion.utils.CommonControllerUtil;
import com.srx.discussion.utils.PropertiesLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author srx
 * @description
 * @create 2020-07-29 06:46:11
 */
@Controller
public class UserController {
    @Autowired
    private UserService service;

    @Autowired
    private PostsService postsService;

    @Autowired
    private PostService postService;

    PropertiesLoader propertiesLoader = new PropertiesLoader("message.properties");

    @PostMapping(value = "/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpServletRequest request) throws IOException {
        User user = service.login(username, password);
        if (user != null && user.getAuthority().equals("1")) {
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(60 * 60 * 24);
            session.setAttribute("user", user);
            return "userSuccess";
        } else if (user != null && user.getAuthority().equals("0")) {
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(60 * 60 * 24);
            session.setAttribute("user", user);
            return "adminSuccess";
        } else {
            return "index";
        }
    }

    @PostMapping(value = "/loginFail")
    @ResponseBody
    public Map<String, Object> loginFail(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Map<String, Object> resultMap = new HashMap<>();
        if (user == null) {
            resultMap.put("errorMessage.login.nofound", propertiesLoader.getValue("errorMessage.login.nofound"));
        } else {
            resultMap.put("errorMessage.login", propertiesLoader.getValue("errorMessage.login"));
        }
        return resultMap;
    }

    /**
     * 在调用该api之前需要先调用下面的验证api对username和email分别查询数据库，如果数据库中没有该数据才能进行api调用
     *
     * @param username
     * @param password
     * @param email
     * @param authority
     * @return
     */
    @PostMapping(value = "/register")
    @ResponseBody
    public Map<String, Object> registered(@RequestParam String username, @RequestParam String password, @RequestParam String email, @RequestParam String authority, HttpServletRequest request, HttpServletResponse response) {
        User user = new User(username, password, email, authority); //通过下面方法insertUser方法的调用此时user中应该已经包含了userId的值
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        try {
            flag = service.insertUser(user);//在该方法的mapper中已经将useGeneratedKeys设置为true
        } catch (ErrorStringException e) {
            try {
                response.sendError(488, e.getMessage());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if (flag) {
            //暂时不提供注册完了就直接登录好了的功能
//            HttpSession session = request.getSession();
//            session.setMaxInactiveInterval(60 * 60 * 24);
//            session.setAttribute("user", user);
            map.put("verificationMsg.success", propertiesLoader.getValue("verificationMsg.success"));
        } else {
            map.put("verificationMsg.fail", propertiesLoader.getValue("verificationMsg.fail"));
        }
        return map;
    }

    @PostMapping(value = "/verification")
    @ResponseBody
    public Map<String, Object> verification(@RequestParam String uniqueId) {
        User verificationUser = service.verification(uniqueId);
        Map<String, Object> msgMap = new HashMap<>();
        if (verificationUser != null) {
            if (verificationUser.getEmail().equals(uniqueId)) {
                msgMap.put("verificationMsg.email", propertiesLoader.getValue("verificationMsg.email"));
            } else if (verificationUser.getUsername().equals(uniqueId)) {
                msgMap.put("verificationMsg.username", propertiesLoader.getValue("verificationMsg.username"));
            }
        } else {
            msgMap.put("verificationMsg.success", propertiesLoader.getValue("verificationMsg.success"));
        }
        return msgMap;
    }

    @PostMapping(value = "/updatePassword")
    @ResponseBody
    public Map<String, Object> updatePassword(@RequestParam String username, @RequestParam String oldPassword, @RequestParam String newPassword) {
        Map<String, Object> map = new HashMap<>();
        User verificationUser = service.login(username, oldPassword);
        if (verificationUser != null) {
            User newUser = new User(verificationUser.getUserId(), username, newPassword, verificationUser.getEmail(), verificationUser.getAuthority());
            map = CommonControllerUtil.CommonController(service, "updateUserPassword", newUser);
        }
        return map;
    }

    @GetMapping("/starPosts")
    @ResponseBody
    public Map<String, Object> starPosts(@RequestParam Integer postsId, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            int userId = user.getUserId();
            if (postsService.queryPostsById(postsId) != null) {
                if (service.queryPostsIsStarStatus(userId, postsId) == null) {
                    map = CommonControllerUtil.CommonController(service, "insertStarPosts", userId, postsId);
                } else if (service.queryPostsIsStarStatus(userId, postsId).equals("1")) {
                    map.put("errorMessage.star.posts.insert", propertiesLoader.getValue("errorMessage.star.posts.insert"));
                } else if (service.queryPostsIsStarStatus(userId, postsId).equals("0")) {
                    map = CommonControllerUtil.CommonController(service, "updatePostsIsStarToLive", userId, postsId);
                }
            } else {
                map.put("errorMessage.nofound.posts", propertiesLoader.getValue("errorMessage.nofound.posts"));
            }
        } else {
            map.put("errorMessage.login", propertiesLoader.getValue("errorMessage.login"));
        }
        return map;
    }

    @GetMapping("/unStarPosts")
    @ResponseBody
    public Map<String, Object> unStarPosts(@RequestParam Integer postsId, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            int userId = user.getUserId();
            if (postsService.queryPostsById(postsId) != null) {
                if (service.queryPostsIsStarStatus(userId, postsId) == null) {
                    map.put("errorMessage.star.posts.delete", propertiesLoader.getValue("errorMessage.star.posts.delete"));
                } else if (service.queryPostsIsStarStatus(userId, postsId).equals("0")) {
                    map.put("errorMessage.star.posts.delete", propertiesLoader.getValue("errorMessage.star.posts.delete"));
                } else if (service.queryPostsIsStarStatus(userId, postsId).equals("1")) {
                    map = CommonControllerUtil.CommonController(service, "updatePostsIsStarToDelete", userId, postsId);
                }
            } else {
                map.put("errorMessage.nofound.posts", propertiesLoader.getValue("errorMessage.nofound.posts"));
            }
        } else {
            map.put("errorMessage.login", propertiesLoader.getValue("errorMessage.login"));
        }
        return map;
    }

    /**
     * 该接口之所以提供UserId的参数，主要是考虑到用户查看其他用户的收藏贴吧的情况，一般的话只需要从session中取到user，并取出userId即可
     *
     * @param userId
     * @return
     */
    @GetMapping("/showUserStarPosts")
    @ResponseBody
    public Map<String, Object> showUserStarPosts(@RequestParam Integer userId) {
        Map<String, Object> map = new HashMap<>();
        if (service.queryUserById(userId) != null)
            map = CommonControllerUtil.CommonController(service, "queryUserStarPostsForAndroid", userId);
        else
            map.put("errorMessage.nofound.user", propertiesLoader.getValue("errorMessage.nofound.user"));
        return map;
    }

    @GetMapping("/starPost")
    @ResponseBody
    public Map<String, Object> starPost(@RequestParam Integer postId, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            int userId = user.getUserId();
            if (postService.queryPostById(postId) != null) {
                if (service.queryPostIsStarStatus(userId, postId) == null) {
                    map = CommonControllerUtil.CommonController(service, "insertStarPost", userId, postId);
                } else if (service.queryPostIsStarStatus(userId, postId).equals("1")) {
                    map.put("errorMessage.star.post.insert", propertiesLoader.getValue("errorMessage.star.post.insert"));
                } else if (service.queryPostIsStarStatus(userId, postId).equals("0")) {
                    map = CommonControllerUtil.CommonController(service, "updatePostIsStarToLive", userId, postId);
                }
            } else {
                map.put("errorMessage.nofound.post", propertiesLoader.getValue("errorMessage.nofound.post"));
            }
        } else {
            map.put("errorMessage.login", propertiesLoader.getValue("errorMessage.login"));
        }
        return map;
    }

    @GetMapping("/unStarPost")
    @ResponseBody
    public Map<String, Object> unStarPost(@RequestParam Integer postId, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            int userId = user.getUserId();
            if (postService.queryPostById(postId) != null) {
                if (service.queryPostIsStarStatus(userId, postId) == null) {
                    map.put("errorMessage.star.post.delete", propertiesLoader.getValue("errorMessage.star.post.delete"));
                } else if (service.queryPostIsStarStatus(userId, postId).equals("0")) {
                    map.put("errorMessage.star.post.delete", propertiesLoader.getValue("errorMessage.star.post.delete"));
                } else if (service.queryPostIsStarStatus(userId, postId).equals("1")) {
                    map = CommonControllerUtil.CommonController(service, "updatePostIsStarToDelete", userId, postId);
                }
            } else {
                map.put("errorMessage.nofound.post", propertiesLoader.getValue("errorMessage.nofound.post"));
            }
        } else {
            map.put("errorMessage.login", propertiesLoader.getValue("errorMessage.login"));
        }
        return map;
    }

    @GetMapping("/showUserStarPost")
    @ResponseBody
    public Map<String, Object> showUserStarPost(@RequestParam Integer userId) {
        Map<String, Object> map = new HashMap<>();
        if (service.queryUserById(userId) != null)
            map = CommonControllerUtil.CommonController(service, "queryUserStarPostForAndroid", userId);
        else
            map.put("errorMessage.nofound.user", propertiesLoader.getValue("errorMessage.nofound.user"));
        return map;
    }


    @GetMapping("/showUserInfo")
    @ResponseBody
    public Map<String, Object> showUserInfo(@RequestParam Integer userId) {
        Map<String, Object> map = new HashMap<>();
        if (service.queryUserInfoById(userId) != null) {
            map = CommonControllerUtil.CommonController(service, "queryUserInfoById", userId);
        } else
            map.put("errorMessage.nofound.user", propertiesLoader.getValue("errorMessage.nofound.user"));
        return map;
    }

    @GetMapping("/insertUserInfo")
    @ResponseBody
    public Map<String, Object> insertUserInfo(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            int userId = user.getUserId();
            UserToInfo userToInfo = new UserToInfo(userId, null, null, null, null, null, null);
            map = CommonControllerUtil.CommonController(service, "insertUserInfo", userToInfo);
        } else {
            map.put("errorMessage.login", propertiesLoader.getValue("errorMessage.login"));
        }
        return map;
    }

    @GetMapping("/updateUserInfo")
    @ResponseBody
    public Map<String, Object> updateUserInfo(HttpServletRequest request, UserToInfo userInfo) {
        Map<String, Object> map = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            int userId1 = userInfo.getUserId();
            int userId = user.getUserId();
            if (userId == userId1) {
                UserToInfo userToInfo = new UserToInfo(userId, userInfo.getAddress(), userInfo.getSelfSignature(), userInfo.getAge(), userInfo.getSex(), userInfo.getAvatar(), userInfo.getNickname());
                map = CommonControllerUtil.CommonController(service, "updateUserInfo", userToInfo);
            } else {
                map.put("errorMessage.authority.short", propertiesLoader.getValue("errorMessage.authority.short"));
            }
        } else {
            map.put("errorMessage.login", propertiesLoader.getValue("errorMessage.login"));
        }
        return map;
    }

    @GetMapping("/showUserNickName")
    @ResponseBody
    public Map<String, Object> showUserNickName(@RequestParam Integer userId) {
        Map<String, Object> map = new HashMap<>();
        if (service.queryUserNikeName(userId) != null) {
            map = CommonControllerUtil.CommonController(service, "queryUserNikeName", userId);
        } else
            map.put("errorMessage.nofound.user", propertiesLoader.getValue("errorMessage.nofound.user"));
        return map;
    }

    @GetMapping("/showPyqList")
    @ResponseBody
    public Map<String, Object> showPyqList(@RequestParam Integer userId) {
        Map<String, Object> map = new HashMap<>();
        if (service.queryPyqListById(userId) != null) {
            List<Pyq> pyqList = service.queryPyqListById(userId);
            for (Pyq pyq :pyqList) {
                pyq.setNickname(service.queryUserNikeName(pyq.getUserId()));
            }
            map.put("pyqList",pyqList);
        } else
            map.put("errorMessage.nofound.user", propertiesLoader.getValue("errorMessage.nofound.user"));
        return map;
    }

    @GetMapping("/deletePyq")
    @ResponseBody
    public Map<String, Object> deletePyq(HttpServletRequest request, @RequestParam Integer userId, @RequestParam String createTime) {
        Map<String, Object> map = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            if (service.queryPyqListById(userId) != null) {
                map = CommonControllerUtil.CommonController(service, "deletePyq", userId, createTime);
            } else
                map.put("errorMessage.nofound.user", propertiesLoader.getValue("errorMessage.nofound.user"));
        } else {
            map.put("errorMessage.login", propertiesLoader.getValue("errorMessage.login"));
        }
        return map;
    }

    /**
     * 这里第一次尝试在请求参数中使用一个object的操作，据说这样可以动态的获取参数，
     * 不用死板的必须有什么参数，具体参数的个数由请求发起者自己定
     * 同时因为传入的是一个对象，所以不用担心内容过长而导致get请求不够用
     *
     * @param request
     * @param pyqContext
     * @return
     */
    @GetMapping("/insertPyq")
    @ResponseBody
    public Map<String, Object> insertPyq(HttpServletRequest request,@RequestParam String pyqContext) {
        Map<String, Object> map = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            if (service.queryUserById(user.getUserId()) != null) {
                //确保插入的用户昵称和用户的id是对应的一个人
                Pyq pyq = new Pyq();
                pyq.setPyqContext(pyqContext);
                pyq.setUserId(user.getUserId());
                map = CommonControllerUtil.CommonController(service, "insertPyq", pyq);
            } else
                map.put("errorMessage.nofound.user", propertiesLoader.getValue("errorMessage.nofound.user"));
        } else {
            map.put("errorMessage.login", propertiesLoader.getValue("errorMessage.login"));
        }
        return map;
    }

}
