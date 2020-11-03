package com.srx.discussion.Entities.hybrid;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author srx
 * @description 用户收藏帖吧
 * @create 2020-08-11 22:14:56
 */
@Component
public class UserToPosts implements Serializable {
    private static final long serialVersionUID = -3548958636857525624L;
    private int userId;
    private int postsId;
    private String starTime;
    private String isStar;

    public UserToPosts() {
    }

    public UserToPosts(int userId, int postsId, String starTime, String isStar) {
        this.userId = userId;
        this.postsId = postsId;
        this.starTime = starTime;
        this.isStar = isStar;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPostsId() {
        return postsId;
    }

    public void setPostsId(int postsId) {
        this.postsId = postsId;
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
        return "UserToPosts{" +
                "userId=" + userId +
                ", postsId=" + postsId +
                ", starTime='" + starTime + '\'' +
                ", isStar='" + isStar + '\'' +
                '}';
    }
}
