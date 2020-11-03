package com.srx.discussion.Entities.base;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author srx
 * @description
 * @create 2020-07-29 06:00:11
 */
@Component
public class Post implements Serializable {
    private static final long serialVersionUID = -61802902500527042L;
    private int postId;
    private String postContext;
    private int postMan;
    private String createTime;
    private String postTitle;
    private String isLive;
    private int postsId;

    public int getPostsId() {
        return postsId;
    }

    public void setPostsId(int postsId) {
        this.postsId = postsId;
    }

    public Post(String postContext, int postMan, String postTitle, int postsId) {
        this.postContext = postContext;
        this.postMan = postMan;
        this.postTitle = postTitle;
        this.postsId = postsId;
    }

    public Post(int postId, String postContext, int postMan, String createTime, String postTitle, String isLive, int postsId) {
        this.postId = postId;
        this.postContext = postContext;
        this.postMan = postMan;
        this.createTime = createTime;
        this.postTitle = postTitle;
        this.isLive = isLive;
        this.postsId = postsId;
    }

    public Post() {
    }


    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getPostContext() {
        return postContext;
    }

    public void setPostContext(String postContext) {
        this.postContext = postContext;
    }

    public int getPostMan() {
        return postMan;
    }

    public void setPostMan(int postMan) {
        this.postMan = postMan;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getIsLive() {
        return isLive;
    }

    public void setIsLive(String isLive) {
        this.isLive = isLive;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", postContext='" + postContext + '\'' +
                ", postMan=" + postMan +
                ", createTime='" + createTime + '\'' +
                ", postTitle='" + postTitle + '\'' +
                ", isLive='" + isLive + '\'' +
                ", postsId=" + postsId +
                '}';
    }
}
