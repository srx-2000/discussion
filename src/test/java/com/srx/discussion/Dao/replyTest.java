package com.srx.discussion.Dao;

import com.srx.discussion.BaseTest;
import com.srx.discussion.Entities.base.Reply;
import com.srx.discussion.Mappers.ReplyMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author srx
 * @description
 * @create 2020-08-27 14:56:49
 */
public class replyTest extends BaseTest {

    @Autowired
    private ReplyMapper mapper;

    @Test
    public void ReplyDoMainTest(){
        Reply reply=new Reply(2,3,"我觉的也是");
        Reply reply1=new Reply(1,2,3,1,"我觉的也是","2018/5/6","1","username");
        System.out.println(reply);
        System.out.println(reply1);
    }
    @Test
    public void InsertReplyTest(){
        Reply reply=new Reply(2,3,"我觉的也是");
        Reply reply1=new Reply(2,3,2,"我觉的也是");
//        boolean b = mapper.insertReply(reply1);
//        System.out.println(b);
//        System.out.println(reply1);
    }
    @Test
    public void paginationQueryReplyListWithCommentTest(){
        Integer targetComment=3;
        Integer begin=0;
        Integer pageSize=3;
        List<Reply> replies = mapper.paginationQueryReplyListWithComment(targetComment, begin, pageSize);
        for (Reply r:replies){
            System.out.println(r);
        }
    }

    @Test
    public void paginationQueryReplyListWithReplyTest(){
        Integer targetComment=2;
        Integer begin=0;
        Integer pageSize=3;
        List<Reply> replies = mapper.paginationQueryReplyListWithReply(targetComment, begin, pageSize);
        for (Reply r:replies){
            System.out.println(r);
        }
    }
    @Test
    public void queryReplyByIdTest(){
        Reply reply = mapper.queryReplyById(2);
        System.out.println(reply);
    }

    @Test
    public void deleteSingleReplyTest() {
        Integer replyId=2;
        boolean b = mapper.deleteSingleReply(replyId);
        System.out.println(b);
    }

    @Test
    public void deleteBatchReplyTest() {
        boolean b = mapper.deleteBatchReply(3);
        System.out.println(b);
    }

    @Test
    public void queryReplyManIdTest() {
        Integer manId = mapper.queryReplyManId(2);
        System.out.println(manId);
    }

    @Test
    public void queryReplyCountTest() {
        Integer integer = mapper.queryReplyCount(3);
        System.out.println(integer);
    }
}
