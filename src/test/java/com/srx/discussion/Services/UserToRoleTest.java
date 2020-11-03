package com.srx.discussion.Services;

import com.srx.discussion.BaseTest;
import com.srx.discussion.Entities.hybrid.UserToRole;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
