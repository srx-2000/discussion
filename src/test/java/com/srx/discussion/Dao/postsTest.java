package com.srx.discussion.Dao;

import com.srx.discussion.Entities.Posts;
import com.srx.discussion.Mappers.PostsMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author srx
 * @description
 * @create 2020-08-09 10:28:28
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:spring/applicationContext.xml")
public class postsTest {
    @Autowired
    PostsMapper mapper;

    @Test
    public void queryPostsById() {
        Posts posts1 = mapper.queryPostsById(2);
        System.out.println(posts1);
    }

    @Test
    public void insertPostTest() {
        Posts posts = new Posts();
        posts.setPostsMan(2);
        posts.setPostsTitle("");
        System.out.println(posts);
        boolean flag = mapper.insertPosts(posts);
        System.out.println(flag);
        System.out.println(posts);
    }

    @Test
    public void queryPostsByTitleTest() {
        String title = "石荣兴吧";
        Posts posts = mapper.queryPostsByTitle(title);
        System.out.println(posts);
    }

    @Test
    public void updateIsLiveToDeleteTest() {
        String title = "首页吧";
        Posts posts = mapper.queryPostsByTitle(title);
        System.out.println(posts);
//        boolean b = mapper.updateIsLiveToDelete(title);
//        System.out.println(b);

    }

    @Test
    public void updatePostsTitleTest(){
        String title="石荣兴2号吧";
        boolean b = mapper.updatePostsTitle(2,title);
        System.out.println(b);
    }

    @Test
    public void blurryQueryPostsListTest(){
        String blurryString=" ";
        List<Posts> posts = mapper.blurryQueryPostsList(blurryString,0,5);
        for (Posts p:posts) {
            System.out.println(p);
        }
    }

}
