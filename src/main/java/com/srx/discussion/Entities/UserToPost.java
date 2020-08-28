package com.srx.discussion.Entities;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author srx
 * @description 用户收藏帖子类
 * @create 2020-08-11 22:15:27
 */
@Component
public class UserToPost implements Serializable {
    private static final long serialVersionUID = 141409597719806832L;
    private int userId;
    private  int postId;
    private String starTime;
    private String isStar;

    public UserToPost(int userId, int postId, String starTime, String isStar) {
        this.userId = userId;
        this.postId = postId;
        this.starTime = starTime;
        this.isStar = isStar;
    }

    public UserToPost() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getStarTime() {
        return starTime;
    }

    public void setStarTime(String starTime) {
        this.starTime = starTime;
    }

    public String getIsStar() {
        return isStar;
    }

    public void setIsStar(String isStar) {
        this.isStar = isStar;
    }

    @Override
    public String toString() {
        return "UserToPost{" +
                "userId=" + userId +
                ", postId=" + postId +
                ", starTime='" + starTime + '\'' +
                ", isStar='" + isStar + '\'' +
                '}';
    }
}
