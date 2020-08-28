package com.srx.discussion.Exceptions;

/**
 * @author srx
 * @description
 * @create 2020-08-12 14:53:02
 */
public abstract class BaseException extends RuntimeException {
    private static final long serialVersionUID = -2854427558805142493L;

    private final String exceptionType;

    private final String exceptionMessage;

    private final Object arg;

    public BaseException(String exceptionType, String exceptionMessage, Object arg) {
        this.exceptionType = exceptionType;
        this.exceptionMessage = exceptionMessage;
        this.arg = arg;
    }

    public BaseException(String exceptionType) {
        this(exceptionType,null,null);
    }

    public BaseException(String exceptionType, String exceptionMessage) {
        this(exceptionType,exceptionMessage,null);
    }

    public BaseException(String exceptionType, Object[] arg) {
        this(exceptionType,null,arg);
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public Object getArg() {
        return arg;
    }

    @Override
    public String getMessage(){
        String message;
        if (exceptionType!=null){
            message=exceptionType;
            if(exceptionMessage!=null){
                message+=":"+exceptionMessage;
            }
            if (arg!=null){
                    message+=" : error object value is "+arg;
            }
        }else {
            message="";
        }
        return message;
    }
}
