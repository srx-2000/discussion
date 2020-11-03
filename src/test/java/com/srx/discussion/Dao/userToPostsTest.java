package com.srx.discussion.Dao;

import com.srx.discussion.BaseTest;
import com.srx.discussion.Entities.base.User;
import com.srx.discussion.Entities.hybrid.UserToPosts;
import com.srx.discussion.Mappers.UserToPostsMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author srx
 * @description
 * @create 2020-08-24 13:08:07
 */
public class userToPostsTest extends BaseTest {

    @Autowired
    private UserToPostsMapper mapper;
    @Test
    public void insertStarPostsTest(){
        boolean b = mapper.insertStarPosts(1, 11);
        System.out.println(b);
    }

    @Test
    public void queryUserStarPostsTest(){
        List<User> users = mapper.queryUserStarPosts(2);
        for (User u:
             users) {
            System.out.println(u);
        }
    }

    @Test
    public void queryUserToPostsListByUserIdTest(){
        List<UserToPosts> userToPosts = mapper.queryUserToPostsListByUserId(2);
        for (UserToPosts p:
             userToPosts) {
            System.out.println(p.getStarTime());
        }
    }

    @Test
    public void queryIsStarStatusTest(){
        String s = mapper.queryIsStarStatus(2, 1);
        System.out.println(s);
    }

    @Test
    public void updateIsStarToDeleteTest(){
        boolean b = mapper.updateIsStarToDelete(2, 1);
        System.out.println(b);
    }

    @Test
    public void updateIsStarToLiveTest(){
        boolean b = mapper.updateIsStarToLive(2, 5);
        System.out.println(b);
    }
}
