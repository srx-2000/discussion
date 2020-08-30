package com.srx.discussion.Dao;

import com.srx.discussion.Entities.User;
import com.srx.discussion.Mappers.UserMapper;
import com.srx.discussion.utils.ExceptionUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author srx
 * @description
 * @create 2020-07-30 02:36:01
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:spring/applicationContext.xml")
public class userTest {
    @Autowired
    private UserMapper mapper;

    @Test
    public void queryUserByEmailTest() {
        User user = mapper.queryUserByEmail("160168462@qq.com");
        if (user != null)
            System.out.println(user.toString());
        else
            System.out.println(user);
    }

    @Test
    public void insertUserTest() {
        boolean b = mapper.insertUser(new User("srx1", "srx62600", "srx-2000@qq.com", "1"));
        boolean b1 = mapper.insertUser(new User("srx", "srx62600", "1601684622@qq.com", "0"));
        System.out.println(b);
        System.out.println(b1);
    }

    @Test
    public void UserGetIdTest() {
        String username = "srx1221";
        String password = "srx62600";
        String email = "160168462@qq.com";
        String authority = "1";
        User user = new User(username, password, email, authority);
        System.out.println(user.toString());
        boolean info = mapper.insertUser(user);
        System.out.println(info);
        System.out.println(user.toString());
    }

    @Test
    public void verificationTest() {
        User user1 = mapper.verification("222@qq.com");
        if (user1 != null)
            System.out.println(user1.toString());
        else
            System.out.println(user1);
    }

    @Test
    public void queryUserAuthorityByEmailTest() {
        String authority = mapper.queryUserAuthorityByEmail("160168462@qq.com");
        System.out.println(authority);
    }

    @Test
    public void updateUserPasswordTest() {
        boolean b = mapper.updateUserPassword(new User(1, "srx", "new password", "srx-2000@qq.com", "1"));
        System.out.println(b);
    }

    @Test
    public void insertUserPostsTerst(){
        Integer userId=2;
        Integer postsId=5;
    }

    @Test
    public void Test() {
        Integer count=-1;
        ExceptionUtil.NumberLessThanZeroException(count);
        System.out.println();
    }
}
