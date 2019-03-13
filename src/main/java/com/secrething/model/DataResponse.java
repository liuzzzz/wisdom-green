package com.secrething.model;

/**
 * Created by liuzz on 2019-03-13 11:25.
 */
public class DataResponse {
    private static final int ERR = 500;
    private static final int SUCCESS = 200;
    private int code;
    private String msg;
    private Object body;

    public DataResponse() {
    }

    public DataResponse(int code, String msg, Object body) {
        this.code = code;
        this.msg = msg;
        this.body = body;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public static DataResponse success(Object data) {
        return new DataResponse(SUCCESS, "success", data);
    }

    public static DataResponse error(String mes) {
        return new DataResponse(ERR, mes, null);
    }
}
