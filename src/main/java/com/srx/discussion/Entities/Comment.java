package com.srx.discussion.Entities;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author srx
 * @description
 * @create 2020-07-29 06:37:34
 */
@Component
public class Comment implements Serializable {
    private static final long serialVersionUID = -6139570450206118162L;
    private int commentId;
    private String commentContext;
    private int commentMan;
    private String createTime;
    private int targetPost;
    private String commentManUsername;
    private String isLive;

    public Comment(int commentId, String commentContext, int commentMan, String createTime, int targetPost, String commentManUsername, String isLive) {
        this.commentId = commentId;
        this.commentContext = commentContext;
        this.commentMan = commentMan;
        this.createTime = createTime;
        this.targetPost = targetPost;
        this.commentManUsername = commentManUsername;
        this.isLive = isLive;
    }

    public Comment(int commentId, String commentContext, int commentMan, String createTime, int targetPost, String isLive) {
        this.commentId = commentId;
        this.commentContext = commentContext;
        this.commentMan = commentMan;
        this.createTime = createTime;
        this.targetPost = targetPost;
        this.isLive = isLive;
    }

    public Comment(String commentContext, int commentMan, int targetPost) {
        this.commentContext = commentContext;
        this.commentMan = commentMan;
        this.targetPost = targetPost;
    }

    public Comment() {
    }

    public int getTargetPost() {
        return targetPost;
    }

    public void setTargetPost(int targetPost) {
        this.targetPost = targetPost;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getCommentContext() {
        return commentContext;
    }

    public void setCommentContext(String commentContext) {
        this.commentContext = commentContext;
    }

    public int getCommentMan() {
        return commentMan;
    }

    public void setCommentMan(int commentMan) {
        this.commentMan = commentMan;
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

    public String getCommentManUsername() {
        return commentManUsername;
    }

    public void setCommentManUsername(String commentManUsername) {
        this.commentManUsername = commentManUsername;
    }


    @Override
    public String toString() {
        if (commentManUsername != null)
            return "Comment{" +
                    "commentId=" + commentId +
                    ", commentContext='" + commentContext + '\'' +
                    ", commentMan=" + commentMan +
                    ", createTime='" + createTime + '\'' +
                    ", targetPost=" + targetPost +
                    ", commentManUsername='" + commentManUsername + '\'' +
                    ", isLive='" + isLive + '\'' +
                    '}';
        return "Comment{" +
                "commentId=" + commentId +
                ", commentContext='" + commentContext + '\'' +
                ", commentMan=" + commentMan +
                ", createTime='" + createTime + '\'' +
                ", targetPost=" + targetPost +
                ", isLive='" + isLive + '\'' +
                '}';
    }
}
