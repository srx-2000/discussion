package com.srx.discussion.Entities.hybrid;

import java.io.Serializable;

public class HybridPost implements Serializable {
    private static final long serialVersionUID = -7428671583330263692L;
    private int postId;
    private int postMan;
    private int postsId;
    private String postContext;
    private String createTime;
    private String postTitle;
    private String postManNickname;
    private String belongPostsName;
    private Integer commentCount;
    private String isLive;

    public int getPostsId() {
        return postsId;
    }

    public void setPostsId(int postsId) {
        this.postsId = postsId;
    }


    public HybridPost(int postId, int postMan, int postsId, String postContext, String createTime, String postTitle, String postManNickname, String belongPostsName, Integer commentCount, String isLive) {
        this.postId = postId;
        this.postMan = postMan;
        this.postsId = postsId;
        this.postContext = postContext;
        this.createTime = createTime;
        this.postTitle = postTitle;
        this.postManNickname = postManNickname;
        this.belongPostsName = belongPostsName;
        this.commentCount = commentCount;
        this.isLive = isLive;
    }


    public HybridPost() {
    }


    public String getPostManNickname() {
        return postManNickname;
    }

    public void setPostManNickname(String postManNickname) {
        this.postManNickname = postManNickname;
    }

    public String getBelongPostsName() {
        return belongPostsName;
    }

    public void setBelongPostsName(String belongPostsName) {
        this.belongPostsName = belongPostsName;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
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
        return "HybridPost{" +
                "postId=" + postId +
                ", postMan=" + postMan +
                ", postsId=" + postsId +
                ", postContext='" + postContext + '\'' +
                ", createTime='" + createTime + '\'' +
                ", postTitle='" + postTitle + '\'' +
                ", postManNickname='" + postManNickname + '\'' +
                ", belongPostsName='" + belongPostsName + '\'' +
                ", commentCount=" + commentCount +
                ", isLive='" + isLive + '\'' +
                '}';
    }
}
