package com.srx.discussion.Services;

import com.srx.discussion.Entities.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author srx
 * @description
 * @create 2020-08-09 15:59:24
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class PostTest {
    @Autowired
    private PostService service;
    @Test
    public void updateIsLiveToDeleteTest(){
        List<Post> posts = service.paginationQueryPostList("首页吧", 2, 2);
        for (Post p:posts) {
            System.out.println(p);
        }
    }
}
