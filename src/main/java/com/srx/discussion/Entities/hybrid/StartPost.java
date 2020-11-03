package com.srx.discussion.Entities.hybrid;

import java.io.Serializable;

public class StartPost implements Serializable {
    private static final long serialVersionUID = 4445500629897798695L;
    private Integer userId;
    private Integer belongPostsId;
    private Integer postmanId;
    private String userNickname;
    private String belongPostsName;
    private String postCreatTime;
    private String postmanNickname;
    private String postContext;

    public StartPost() {
    }

    public StartPost(Integer userId, String userNickname, String belongPostsName, Integer belongPostsId, String postCreatTime, Integer postmanId, String postmanNickname, String postContext) {
        this.userId = userId;
        this.userNickname = userNickname;
        this.belongPostsName = belongPostsName;
        this.belongPostsId = belongPostsId;
        this.postCreatTime = postCreatTime;
        this.postmanId = postmanId;
        this.postmanNickname = postmanNickname;
        this.postContext = postContext;
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

    public String getBelongPostsName() {
        return belongPostsName;
    }

    public void setBelongPostsName(String belongPostsName) {
        this.belongPostsName = belongPostsName;
    }

    public Integer getBelongPostsId() {
        return belongPostsId;
    }

    public void setBelongPostsId(Integer belongPostsId) {
        this.belongPostsId = belongPostsId;
    }

    public String getPostCreatTime() {
        return postCreatTime;
    }

    public void setPostCreatTime(String postCreatTime) {
        this.postCreatTime = postCreatTime;
    }

    public Integer getPostmanId() {
        return postmanId;
    }

    public void setPostmanId(Integer postmanId) {
        this.postmanId = postmanId;
    }

    public String getPostmanNickname() {
        return postmanNickname;
    }

    public void setPostmanNickname(String postmanNickname) {
        this.postmanNickname = postmanNickname;
    }

    public String getPostContext() {
        return postContext;
    }

    public void setPostContext(String postContext) {
        this.postContext = postContext;
    }

    @Override
    public String toString() {
        return "startPost{" +
                "userId=" + userId +
                ", userNickname='" + userNickname + '\'' +
                ", belongPostsName='" + belongPostsName + '\'' +
                ", belongPostsId='" + belongPostsId + '\'' +
                ", postCreatTime='" + postCreatTime + '\'' +
                ", postmanId='" + postmanId + '\'' +
                ", postmanNickname='" + postmanNickname + '\'' +
                ", postContext='" + postContext + '\'' +
                '}';
    }
}
