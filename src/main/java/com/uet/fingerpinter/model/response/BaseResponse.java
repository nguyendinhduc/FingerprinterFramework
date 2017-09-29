package com.uet.fingerpinter.model.response;

public class BaseResponse<T> {
    private int errorCode;
    private String msg;
    private T data;

    public BaseResponse(T data) {
        this.data = data;
        msg = "success";
        errorCode = 0;
    }

    public BaseResponse(int errorCode, String msg, T data) {
        this.errorCode = errorCode;
        this.msg = msg;
        this.data = data;
    }

    public BaseResponse(int errorCode, String msg) {
        this.errorCode = errorCode;
        this.msg = msg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public static BaseResponse exceptionResponse(String msg, int errorCode) {
        return new BaseResponse(errorCode, msg);
    }

}
