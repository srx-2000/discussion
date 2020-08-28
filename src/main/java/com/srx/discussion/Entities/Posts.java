package com.srx.discussion.Entities;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author srx
 * @description
 * @create 2020-08-02 13:12:18
 */
@Component
public class Posts implements Serializable {
    private static final long serialVersionUID = 4591570724806988276L;
    private int postsId;
    private String postsTitle;
    private int postsMan;
    private String createTime;
    private String isLive;
    private String roleStatus;




    public Posts(String postsTitle, int postsMan) {
        this.postsTitle = postsTitle;
        this.postsMan = postsMan;
    }

    public Posts(int postsId, String postsTitle, int postsMan, String createTime, String isLive) {
        this.postsId = postsId;
        this.postsTitle = postsTitle;
        this.postsMan = postsMan;
        this.createTime = createTime;
        this.isLive = isLive;
    }

    public String getRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(String roleStatus) {
        this.roleStatus = roleStatus;
    }
    public Posts() {
    }

    public int getPostsId() {
        return postsId;
    }

    public void setPostsId(int postsId) {
        this.postsId = postsId;
    }

    public String getPostsTitle() {
        return postsTitle;
    }

    public void setPostsTitle(String postsTitle) {
        this.postsTitle = postsTitle;
    }

    public int getPostsMan() {
        return postsMan;
    }

    public void setPostsMan(int postsMan) {
        this.postsMan = postsMan;
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
        if (this.roleStatus==null)
        return "Posts{" +
                "postsId=" + postsId +
                ", postsTitle='" + postsTitle + '\'' +
                ", postsMan=" + postsMan +
                ", createTime='" + createTime + '\'' +
                ", isLive='" + isLive + '\'' +
                '}';
        else
            return "Posts{" +
                    "postsId=" + postsId +
                    ", postsTitle='" + postsTitle + '\'' +
                    ", postsMan=" + postsMan +
                    ", createTime='" + createTime + '\'' +
                    ", isLive='" + isLive + '\'' +
                    ", roleStatus='" + roleStatus + '\'' +
                    '}';
    }
}