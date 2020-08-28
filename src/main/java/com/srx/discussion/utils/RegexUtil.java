package com.srx.discussion.utils;

import com.srx.discussion.Entities.User;
import com.srx.discussion.Enums.Regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author srx
 * @description
 * @create 2020-08-14 23:12:16
 */
public class RegexUtil {

    /**
     * 注意这个方法：为了方便调用，所以在返回的时候，我就做了取反处理
     * 所以结果应为：
     * 如果传入的正则表达式和传入的字符串不同的话，那么就返回true
     * 如果传入的正则表达式和传入的字符串匹配的话，就返回false
     * @param pattern
     * @param matcher
     * @return
     */
    public static boolean commonRegex(Regex pattern, String matcher){
        String p = pattern.getPattern();
        Pattern r = Pattern.compile(p);
        Matcher m = r.matcher(matcher);
        return !m.matches();
    }



}
