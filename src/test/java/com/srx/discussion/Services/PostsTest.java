package com.srx.discussion.Services;

import com.srx.discussion.Entities.Posts;
import com.srx.discussion.Entities.UserToRole;
import com.srx.discussion.Enums.Regex;
import com.srx.discussion.Exceptions.ErrorStringException;
import com.srx.discussion.Mappers.PostsMapper;
import com.srx.discussion.utils.ExceptionUtil;
import com.srx.discussion.utils.RegexUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author srx
 * @description
 * @create 2020-08-09 15:59:24
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class PostsTest {
    @Autowired
    private PostsService postsService;

    @Autowired
    private PostsMapper postsMapper;

    @Autowired
    private UserToRoleService userToRoleService;

    @Test
    public void updateIsLiveToDeleteTest() {
        boolean flag = postsService.updateIsLiveToDelete("石荣兴2号吧");
        System.out.println(flag);
    }

    @Test
    public void insertPostsTest() {
        Posts posts = new Posts();
        posts.setPostsMan(2);
        posts.setPostsTitle("");
        try {
            boolean b = postsService.insertPosts(posts);
            System.out.println(b);
        } catch (ErrorStringException e) {
            e.printStackTrace();
        }
    }


    @Test
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertPosts() {
        Posts posts=new Posts("test7",2);
        if (posts != null) {
            if (RegexUtil.commonRegex(Regex.ENTITY_CONTEXT, posts.getPostsTitle()))
                ExceptionUtil.ErrorStringException(Regex.ENTITY_CONTEXT, posts.getPostsTitle(), "insertPosts");
            Posts flagPosts = postsMapper.queryPostsByTitle(posts.getPostsTitle());
            if (flagPosts != null)
                ExceptionUtil.RepeatInsertException(flagPosts);
            else {
                boolean flag = postsMapper.insertPosts(posts);//这里先调用postsMapper中的insertPosts将posts插入表中之后，再通过事务传播在user_to_role表中插入status记录
                int postsId = posts.getPostsId();
                int postsMan = posts.getPostsMan();
                if (flag)
                    System.out.println(userToRoleService.insertRole(new UserToRole(postsMan, postsId, "2")));
            }
        }
    }
    @Test
    public void queryStatusTest(){
        Integer userId=1;
        Integer postsId=4;
        String s = postsService.queryStatus(userId, postsId);
        System.out.println(s);
    }
    @Test
    public void queryPostsListWithRoleTest(){
        List<Posts> posts = postsService.queryPostsListWithRole(2);
        for(Posts p:posts){
            System.out.println(p);
        }

    }

}
