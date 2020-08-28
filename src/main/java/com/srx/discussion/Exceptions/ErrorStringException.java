package com.srx.discussion.Exceptions;

/**
 * @author srx
 * @description
 * @create 2020-08-14 15:52:53
 */
public class ErrorStringException extends BaseException {
    private static final long serialVersionUID = -235980591188749595L;

    public ErrorStringException(String exceptionMessage, Object arg) {
        super("ErrorStringException", exceptionMessage, arg);
    }

}
