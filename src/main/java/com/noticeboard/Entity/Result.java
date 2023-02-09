package com.noticeboard.Entity;


public class Result {
    public enum ResultCode{
        OK,FAIL,ILLEAGLE
    }
    private ResultCode code=ResultCode.FAIL;
    private String msg="unkonwn error!";
    private Object data;

    public ResultCode getCode() {
        return code;
    }

    public void setCode(ResultCode code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
