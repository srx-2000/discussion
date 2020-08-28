package com.srx.discussion.Exceptions;

/**
 * @author srx
 * @description
 * @create 2020-08-19 02:43:35
 */
public class RepeatInsertException extends BaseException {
    private static final long serialVersionUID = 6686522434118529855L;

    public RepeatInsertException(String exceptionMessage, Object arg) {
        super("RepeatInsertException", exceptionMessage, arg);
    }
}
