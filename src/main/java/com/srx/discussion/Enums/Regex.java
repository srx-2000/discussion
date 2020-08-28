package com.srx.discussion.Enums;

/**
 * @author srx
 * @description
 * @create 2020-08-15 12:16:31
 */
public enum Regex {
    EMAIL("\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}"),
    USERNAME("[A-Za-z0-9_\\-\\u4e00-\\u9fa5]+"),
    PASSWORD("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$"),
    ENTITY_CONTEXT("\\S+")
    ;

    private String pattern;

    Regex(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }
}
