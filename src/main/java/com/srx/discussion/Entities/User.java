package com.srx.discussion.Entities;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author srx
 * @description
 * @create 2020-07-29 05:54:44
 */
@Component
public class User implements Serializable {
    private static final long serialVersionUID = 3754733629782042522L;
    private int userId;
    private String username;
    private String password;
    private String email;
    private String authority;
    private String starPostsId;
    private String starPostsTitle;
    private String starPostId;
    private String starPostTitle;

    public String getStarPostsId() {
        return starPostsId;
    }

    public void setStarPostsId(String starPostsId) {
        this.starPostsId = starPostsId;
    }

    public String getStarPostsTitle() {
        return starPostsTitle;
    }

    public void setStarPostsTitle(String starPostsTitle) {
        this.starPostsTitle = starPostsTitle;
    }

    public String getStarPostId() {
        return starPostId;
    }

    public void setStarPostId(String starPostId) {
        this.starPostId = starPostId;
    }

    public String getStarPostTitle() {
        return starPostTitle;
    }

    public void setStarPostTitle(String starPostTitle) {
        this.starPostTitle = starPostTitle;
    }

    public User(int userId, String username, String password, String email, String authority, String starPostsId, String starPostsTitle, String starPostId, String starPostTitle) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.authority = authority;
        this.starPostsId = starPostsId;
        this.starPostsTitle = starPostsTitle;
        this.starPostId = starPostId;
        this.starPostTitle = starPostTitle;
    }

    public User() {
    }

    public User(int userId, String username, String password, String email, String authority, String starPostsId, String starPostsTitle) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.authority = authority;
        this.starPostsId = starPostsId;
        this.starPostsTitle = starPostsTitle;
    }

    public User(int userId, String username, String password, String email, String authority) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.authority = authority;
    }

    public User(String username, String password, String email, String authority) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.authority = authority;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        if (starPostsId != null && starPostsTitle != null)
            return "User{" +
                    "userId=" + userId +
                    ", username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    ", email='" + email + '\'' +
                    ", authority='" + authority + '\'' +
                    ", starPostsId='" + starPostsId + '\'' +
                    ", starPostsTitle='" + starPostsTitle + '\'' +
                    '}';
        else if (starPostId != null && starPostTitle != null)
            return "User{" +
                    "userId=" + userId +
                    ", username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    ", email='" + email + '\'' +
                    ", authority='" + authority + '\'' +
                    ", starPostsId='" + starPostsId + '\'' +
                    ", starPostsTitle='" + starPostsTitle + '\'' +
                    ", starPostId='" + starPostId + '\'' +
                    ", starPostTitle='" + starPostTitle + '\'' +
                    '}';
        else
            return "User{" +
                    "userId=" + userId +
                    ", username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    ", email='" + email + '\'' +
                    ", authority=" + authority +
                    '}';
    }
}
