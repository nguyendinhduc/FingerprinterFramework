package com.uet.fingerpinter.model.response;

public class CustomExceptionResponse extends Exception {
    public static final int ERROR_PARAM = 100;
    private int errorCode;

    public CustomExceptionResponse(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
