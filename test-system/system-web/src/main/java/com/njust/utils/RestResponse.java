package com.njust.utils;

/**
 * @Author qufeng
 * @Date 2021/2/8 15:01
 * @Version 1.0
 */
public class RestResponse<T> {
    private int code;
    private String message;
    private T response;

    public RestResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public RestResponse(int code, String message, T response) {
        this.code = code;
        this.message = message;
        this.response = response;
    }

    public static RestResponse fail(Integer code, String msg) {
        return new RestResponse<>(code, msg);
    }

    public static RestResponse ok() {
        return new RestResponse<>(1, "成功");
    }

    public static <F> RestResponse<F> ok(F response) {
        return new RestResponse<>(1, "成功", response);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }
}
