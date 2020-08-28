package com.srx.discussion.Entities;

import com.srx.discussion.Enums.UserRole;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author srx
 * @description 用户在每个贴吧的角色
 * @create 2020-08-11 22:15:10
 */
@Component
public class UserToRole implements Serializable {
    private static final long serialVersionUID = -7928946420239362496L;
    private int userId;
    private int postsId;
    // 1 为管理员用户 2 为创建者用户
    private String status;

    public UserToRole() {
    }

    public UserToRole(int userId, int postsId) {
        this.userId = userId;
        this.postsId = postsId;
    }

    public UserToRole(int userId, int postsId, String status) {
        this.userId = userId;
        this.postsId = postsId;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserToRole{" +
                "userId=" + userId +
                ", postsId=" + postsId +
                ", status='" + status + '\'' +
                '}';
    }
}
