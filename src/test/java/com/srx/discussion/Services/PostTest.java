package com.srx.discussion.Services;

import com.srx.discussion.Entities.base.Post;
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
//        List<Post> posts = service.paginationQueryPostList(1, 2, 2);
//        for (Post p:posts) {
//            System.out.println(p);
//        }
    }

    @Test
    public void postSingleDeleteTest() {
        boolean b = service.deleteSinglePost(2);
        System.out.println(b);
    }

    @Test
    public void deleteBatchPostTest() {
        boolean b = service.deleteBatchPost(1);
        System.out.println(b);
    }
}
