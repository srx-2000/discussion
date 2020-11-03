package com.srx.discussion.Entities.base;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;

/**
 * @author srx
 * @description
 * @create 2020-07-29 06:39:40
 */
@Component
public class Reply implements Serializable {
    private static final long serialVersionUID = -494473321362255517L;
    //该属性主要用于在消息列表中对用户进行检索
    private int replyId;
    private int replyMan;
    private int targetComment;
    private int targetReply;
    private String replyContext;
    private String createTime;
    private String isLive;
    //该属性主要用作显示，也就是view层
    private String replyManNickname;

    public Reply(int replyMan, int targetComment, String replyContext) {
        this.replyMan = replyMan;
        this.targetComment = targetComment;
        this.replyContext = replyContext;
    }

    public Reply(int replyMan, int targetComment, int targetReply, String replyContext) {
        this.replyMan = replyMan;
        this.targetComment = targetComment;
        this.targetReply = targetReply;
        this.replyContext = replyContext;
    }

    public Reply(int replyId, int replyMan, int targetComment, int targetReply, String replyContext, String createTime, String isLive, String replyManNickname) {
        this.replyId = replyId;
        this.replyMan = replyMan;
        this.targetComment = targetComment;
        this.targetReply = targetReply;
        this.replyContext = replyContext;
        this.createTime = createTime;
        this.isLive = isLive;
        this.replyManNickname = replyManNickname;
    }

    public Reply(int replyId, String replyContext, int replyMan, String createTime, String isLive, int targetComment, int targetReply) {
        this.replyId = replyId;
        this.replyContext = replyContext;
        this.replyMan = replyMan;
        this.createTime = createTime;
        this.isLive = isLive;
        this.targetComment = targetComment;
        this.targetReply = targetReply;
    }

    public Reply() {
    }

    public void setTargetComment(int targetComment) {
        this.targetComment = targetComment;
    }

    public int getTargetComment() {
        return targetComment;
    }

    public int getTargetReply() {
        return targetReply;
    }

    public void setTargetReply(int targetReply) {
        this.targetReply = targetReply;
    }

    public String getreplyManNickname() {
        return replyManNickname;
    }

    public void setreplyManNickname(String replyManNickname) {
        this.replyManNickname = replyManNickname;
    }

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public String getReplyContext() {
        return replyContext;
    }

    public void setReplyContext(String replyContext) {
        this.replyContext = replyContext;
    }

    public int getReplyMan() {
        return replyMan;
    }

    public void setReplyMan(int replyMan) {
        this.replyMan = replyMan;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getIsLive() {
        return isLive;
    }

    public void setIsLive(String isLive) {
        this.isLive = isLive;
    }

    @Override
    public String toString() {
        if (replyManNickname != null)
            return "Reply{" +
                    "replyId=" + replyId +
                    ", replyMan=" + replyMan +
                    ", targetComment=" + targetComment +
                    ", targetReply=" + targetReply +
                    ", replyContext='" + replyContext + '\'' +
                    ", createTime='" + createTime + '\'' +
                    ", isLive='" + isLive + '\'' +
                    ", replyManNickname='" + replyManNickname + '\'' +
                    '}';
        return "Reply{" +
                "replyId=" + replyId +
                ", replyContext='" + replyContext + '\'' +
                ", replyMan=" + replyMan +
                ", createTime='" + createTime + '\'' +
                ", isLive='" + isLive + '\'' +
                ", targetComment=" + targetComment +
                ", targetReply=" + targetReply +
                '}';
    }
}
