package com.srx.discussion.Services;

import com.srx.discussion.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author srx
 * @description
 * @create 2020-08-28 21:51:29
 */
public class CommentTest extends BaseTest {
    @Autowired
    private CommentService service;
    @Test
    public void deleteSingleCommentTest() {
        boolean b = service.deleteSingleComment(3);
        System.out.println(b);
    }

    @Test
    public void deleteBatchCommentTest() {
        boolean b = service.deleteBatchComment(2);
        System.out.println(b);
    }

    @Test
    public void countTest() {
        System.out.println(service.queryCommentAndReplyCount(2));
    }
}
