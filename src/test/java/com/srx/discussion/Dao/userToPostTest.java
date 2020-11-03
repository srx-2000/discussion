package com.srx.discussion.Dao;

import com.srx.discussion.BaseTest;
import com.srx.discussion.Entities.base.User;
import com.srx.discussion.Entities.hybrid.UserToPost;
import com.srx.discussion.Mappers.UserToPostMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author srx
 * @description
 * @create 2020-08-24 13:08:07
 */
public class userToPostTest extends BaseTest {

    @Autowired
    private UserToPostMapper mapper;
    @Test
    public void insertStarPostTest(){
        boolean b = mapper.insertStarPost(1, 8);
        System.out.println(b);
    }

    @Test
    public void queryUserStarPostTest(){
        List<User> users = mapper.queryUserStarPost(2);
        for (User u:
             users) {
            System.out.println(u);
        }
    }

    @Test
    public void queryUserToPostListByUserIdTest(){
        List<UserToPost> userToPost = mapper.queryUserToPostListByUserId(2);
        for (UserToPost p:
             userToPost) {
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
