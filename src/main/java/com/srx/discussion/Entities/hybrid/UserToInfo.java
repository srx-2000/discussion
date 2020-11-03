package com.srx.discussion.Entities.hybrid;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.UUID;

@Component
public class UserToInfo implements Serializable {
    private static final long serialVersionUID = 1313955573178371713L;
    private int userId;
    private String address;
    private String selfSignature;
    private Integer age;
    private String sex;
    private String nickname;
    private Byte avatar;

    public UserToInfo(int userId, String address, String selfSignature, Integer age, String sex, Byte avatar, String nickname) {
        if (address == null) {
            this.address = "无";
        } else {
            this.address = address;
        }
        if (selfSignature == null) {
            this.selfSignature = "这个人很懒还没有个性签名.....";
        } else {
            this.selfSignature = selfSignature;
        }
        if (age == null) {
            this.age = 0;
        } else {
            this.age = age;
        }
        if (sex == null) {
            this.sex = "秘密";
        } else {
            this.sex = sex;
        }
        if (nickname == null) {
            UUID uuid = UUID.randomUUID();
            String id = uuid.toString();
            this.nickname = "用户" + id;
        } else {
            this.nickname = nickname;
        }
        if (avatar == null) {
            this.avatar = avatar;
        }
        this.userId = userId;
    }


    public UserToInfo() {
    }

    public UserToInfo(int userId) {
        this.userId = userId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSelfSignature() {
        return selfSignature;
    }

    public void setSelfSignature(String selfSignature) {
        this.selfSignature = selfSignature;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Byte getAvatar() {
        return avatar;
    }

    public void setAvatar(byte avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "UserToInfo{" +
                "userId=" + userId +
                ", address='" + address + '\'' +
                ", selfSignature='" + selfSignature + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", nickname='" + nickname + '\'' +
                ", avatar=" + avatar +
                '}';
    }
}
