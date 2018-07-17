package com.oppan.exception;

public class GeneralException extends Exception{

    private int errorCode;

    public GeneralException(String message){
        super(message);
    }

    public GeneralException(int errorCode, String message){
        super(message);
        this.errorCode = errorCode;
    }

    public GeneralException(String message, Throwable cause){
        super(message, cause);
    }

    public int getErrorCode() {
        return errorCode;
    }
}
