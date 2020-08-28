package com.srx.discussion.utils;

import com.srx.discussion.Enums.Regex;
import com.srx.discussion.Enums.StatusCode;
import com.srx.discussion.Exceptions.ErrorStringException;
import com.srx.discussion.Exceptions.NullObjectException;
import com.srx.discussion.Exceptions.RepeatInsertException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author srx
 * @description
 * @create 2020-08-14 16:13:37
 */
public class ExceptionUtil {
    /**
     * 该方法用于调用ErrorStringException异常，其可以根据传入的匹配字符串，对象，原方法名，自动抛出各种字符串参数错误
     * 其中错误抛出的优先级为 null>"">matchString
     * 如果传入的为字符串，那么报错中的Object value为原方法名
     *
     * @param patternString 传入正则表达式，对传入对象进行匹配，没匹配到抛异常
     * @param matchString   对象，String类型对象
     * @param methodName    原方法名。用于定位传入String类型的对象的位置
     */
    public static void ErrorStringException(Regex patternString, String matchString, String methodName) {
        if (matchString == null) {
            throw new ErrorStringException("Incoming matchString is null", methodName);
        }
        String pattern = patternString.getPattern();
        Pattern compile = Pattern.compile(pattern);
        if (matchString.getClass() == String.class) {
            Matcher matcher = compile.matcher(matchString);
            if (matchString.equals("")) {
                throw new ErrorStringException("Incoming String is a empty String", methodName);
            } else if (!matcher.matches()) {
                throw new ErrorStringException("Incoming String is not match regex " + patternString, methodName);
            }
        }
    }

    /**
     * 对传入的Response进行sendError操作，并捕获IO异常，主要用于简化代码行数，并提升其可读性
     *
     * @param response   传入的response
     * @param StatusCode 发送的错误码，这里传入的应该为StatusCode的枚举类
     * @param message    报错信息
     */
    public static void ResponseCatcher(HttpServletResponse response, StatusCode StatusCode, String message) {
        int code = StatusCode.getCode();
        try {
            response.sendError(code, message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void RepeatInsertException(Object entity) {
        if (entity != null)
            throw new RepeatInsertException("Have a RepeatInsert ", entity);
    }

    public static void NullObjectException(Object entity) {
        if (entity == null)
            throw new NullObjectException("Don't have the object or the object has be Soft delete",null);
    }


}
