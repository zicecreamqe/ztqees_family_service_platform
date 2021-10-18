package com.ztqees.returnJson;

/**
 * @author zhouqien
 * @date 2021-10-13 20:33
 */
public class ReturnObject {
    private Integer code = 200;
    private String message = "";
    private Object result;

    public ReturnObject(Object result) {
        this.result = result;
    }

    public ReturnObject(Object result, String message) {
        this.result = result;
        this.message = message;
    }

    public ReturnObject() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ReturnObject{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }
}
