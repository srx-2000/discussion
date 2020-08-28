package com.srx.discussion.Enums;

/**
 * @author srx
 * @description 该枚举类中的所有权限，皆是以贴吧为单位的
 * @create 2020-08-13 17:25:00
 */
public enum UserRole {

    ADMIN_USER("1","管理员用户"),CREATE_USER("2","创建者用户");
    private final String code;
    private final String info;

    UserRole(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
