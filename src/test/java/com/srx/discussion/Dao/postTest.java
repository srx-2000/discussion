package com.srx.discussion.Dao;

import com.srx.discussion.Entities.Post;
import com.srx.discussion.Mappers.PostMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author srx
 * @description
 * @create 2020-08-09 14:43:51
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:spring/applicationContext.xml")
public class postTest {
    @Autowired
    PostMapper mapper;

    @Test
    public void insertPostTest(){
        Post post=new Post();
        post.setPostContext("石荣兴好帅啊啊啊啊！！！！！！！！！！");
        post.setPostMan(1);
        post.setPostTitle("石荣兴的特征");
        post.setPostsId(1);
        System.out.println(post);
        boolean b = mapper.insertPost(post);
        System.out.println(post);

    }
    @Test
    public void queryPostListTest(){
        List<Post> posts = mapper.paginationQueryPostList(1,1,3);
        for (Post p:posts) {
            System.out.println(p);
        }
    }
    @Test
    public void updateIsLiveToDeleteByPostsIdTest(){
//        mapper.updateIsLiveToDeleteByPostsId(2);
    }

    @Test
    public void queryPostByIdTest(){
        Integer postId=9;
        Map<String,Object> map=new HashMap<>();
        Post post = mapper.queryPostById(postId);
        map.put("postId",post.getPostId());
        map.put("postTitle",post.getPostTitle());
        map.put("postContext",post.getPostContext());
        map.put("postMan",post.getPostMan());
        System.out.println(post);
        System.out.println(map);
    }
}
