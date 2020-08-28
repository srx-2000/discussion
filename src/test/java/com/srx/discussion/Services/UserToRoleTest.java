package com.srx.discussion.Services;

import com.srx.discussion.BaseTest;
import com.srx.discussion.Entities.UserToRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author srx
 * @description
 * @create 2020-08-22 10:39:04
 */

public class UserToRoleTest extends BaseTest {
    @Autowired
    private UserToRoleService service;

    @Test
    public void insertTest(){
        int userId=2;
        int postsId=6;
        String status="2";
        UserToRole user=new UserToRole(userId,postsId,status);
        boolean b = service.insertRole(user);
        System.out.println(b);
    }


}
