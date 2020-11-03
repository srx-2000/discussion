package com.srx.discussion.Controllers;

import com.srx.discussion.BaseTest;
import com.srx.discussion.Entities.base.User;
import com.srx.discussion.Services.PostsService;
import com.srx.discussion.Services.UserService;
import com.srx.discussion.utils.CommonControllerUtil;
import com.srx.discussion.utils.PropertiesLoader;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * @author srx
 * @description
 * @create 2020-08-24 22:58:52
 */
public class userController extends BaseTest {
    @Autowired
    private UserService service;

    @Autowired
    private PostsService postsService;

    PropertiesLoader propertiesLoader = new PropertiesLoader("message.properties");

    @Test
    public void starPosts() {
        Integer postsId=5;
        Map<String, Object> map = new HashMap<>();
        User user = new User(2,"srx","srx62600","1601684622@qq.com","1");
        if (user != null) {
            int userId = user.getUserId();
            if(postsService.queryPostsById(postsId)!=null){
                if (service.queryPostsIsStarStatus(userId, postsId)==null) {
                    map = CommonControllerUtil.CommonController(service, "insertStarPosts", userId, postsId);
                } else if (service.queryPostsIsStarStatus(userId, postsId).equals("1")) {
                    map.put("errorMessage.star.posts.insert", propertiesLoader.getValue("errorMessage.star.posts.insert"));
                } else if(service.queryPostsIsStarStatus(userId, postsId).equals("0")){
                    map = CommonControllerUtil.CommonController(service, "updateIsStarToLive", userId, postsId);
                }
            }else {
                map.put("errorMessage.nofound.posts", propertiesLoader.getValue("errorMessage.nofound.posts"));
            }
        } else {
            map.put("errorMessage.login", propertiesLoader.getValue("errorMessage.login"));
        }
        System.out.println(map);
    }
}
