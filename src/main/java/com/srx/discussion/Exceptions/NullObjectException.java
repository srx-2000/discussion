package com.srx.discussion.Exceptions;

/**
 * @author srx
 * @description
 * @create 2020-08-20 16:03:34
 */
public class NullObjectException extends BaseException {

    private static final long serialVersionUID = 6028130379939483437L;

    public NullObjectException(String exceptionMessage, Object arg) {
        super("NullObjectException", exceptionMessage, arg);
    }
}
