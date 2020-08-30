package com.srx.discussion.Exceptions;

/**
 * @author srx
 * @description
 * @create 2020-08-28 18:06:01
 */
public class NumberLessThanZeroException extends BaseException {

    private static final long serialVersionUID = 4736668407031549443L;

    public NumberLessThanZeroException(String exceptionMessage, Object arg) {
        super("NumberLessThanZeroException", exceptionMessage, arg);
    }
}
