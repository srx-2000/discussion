package com.srx.discussion.Entities.hybrid;

import java.io.Serializable;

public class StartPosts implements Serializable {
    private static final long serialVersionUID = 1001226037662883262L;
    private Integer userId;
    private String userNickname;
    private String postsName;
    private String postsId;

    public StartPosts(Integer userId, String userNickname, String postsName, String postsId) {
        this.userId = userId;
        this.userNickname = userNickname;
        this.postsName = postsName;
        this.postsId = postsId;
    }

    public StartPosts() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getPostsName() {
        return postsName;
    }

    public void setPostsName(String postsName) {
        this.postsName = postsName;
    }

    public String getPostsId() {
        return postsId;
    }

    public void setPostsId(String postsId) {
        this.postsId = postsId;
    }

    @Override
    public String toString() {
        return "startPosts{" +
                "userId=" + userId +
                ", userNickname='" + userNickname + '\'' +
                ", postsName='" + postsName + '\'' +
                ", postsId='" + postsId + '\'' +
                '}';
    }
}
