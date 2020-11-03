package com.srx.discussion.Exceptions;

import com.srx.discussion.Entities.base.Posts;
import com.srx.discussion.Entities.base.User;
import com.srx.discussion.Enums.Regex;
import com.srx.discussion.Services.PostsService;
import com.srx.discussion.utils.ExceptionUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author srx
 * @description
 * @create 2020-08-14 15:57:43
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:spring/applicationContext.xml")
public class ExceptionTest {

    @Autowired
    private PostsService postsService;

    @Test
    public void ErrorStringException(HttpServletResponse response) throws IOException {
        String name = "fasdf";
        try {
            String password = "d55555555555555555555555555552";
            User user = new User("fsdf", "", name, "0");
            ExceptionUtil.ErrorStringException(Regex.PASSWORD, password, "TestForException");

        } catch (ErrorStringException e) {
            response.sendError(409, e.getMessage());
        }
    }

    @Test
    public void RepeatInsertException() {
//        postsService.insertPosts(new Posts(""))
        Posts posts = new Posts();
        posts.setPostsMan(2);
        posts.setPostsTitle("首页吧");
        boolean b = postsService.insertPosts(posts);
        System.out.println(b);
    }


}
