package com.srx.discussion.Controllers;

import com.srx.discussion.Services.PostsService;
import com.srx.discussion.Services.impl.PostServiceImpl;
import com.srx.discussion.utils.CommonControllerUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

/**
 * @author srx
 * @description
 * @create 2020-08-17 15:07:46
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:/spring/applicationContext.xml")
public class commonController {
    @Autowired
    PostsService service;

    @Test
    public void ControllerTest() {
        int postsId = 1;
        String postsTitle = "首页";
        try {
            Map<String, Object> map = CommonControllerUtil.CommonController(service, "blurryQueryPostsList", postsTitle);
            System.out.println(map.toString());
        } catch (Exception e) {
            e.getMessage();
        }
    }

}
