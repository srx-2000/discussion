package com.srx.discussion.Services;

import com.srx.discussion.BaseTest;
import com.srx.discussion.Entities.UserToPosts;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.Oneway;

/**
 * @author srx
 * @description
 * @create 2020-08-24 23:29:10
 */
public class UserToPostsTest extends BaseTest {
    @Autowired
    private UserToPostsService service;

    @Test
    public void updateIsStarToLiveTest(){
        Integer userId=2;
        Integer postsId=5;
        boolean b = service.updateIsStarToLive(userId, postsId);
        System.out.println(b);
    }
}
