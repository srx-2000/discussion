package com.srx.discussion.Services;

import com.srx.discussion.BaseTest;
import com.srx.discussion.Entities.Reply;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author srx
 * @description
 * @create 2020-08-28 17:04:04
 */
public class ReplyTest extends BaseTest {
    @Autowired
    private ReplyService replyService;
    @Test
    public void queryReplyByIdTest() {
        Reply reply = replyService.queryReplyById(3);
        System.out.println(reply);
    }
}
