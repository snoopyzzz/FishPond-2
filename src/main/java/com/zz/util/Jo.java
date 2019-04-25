package com.zz.util;

/**
 * Created with IntelliJ IDEA.
 * User: 钊钊
 * Date: 2018/12/8
 * Time: 20:28
 * Description: No Description
 */
public class Jo {
    private boolean success;
    private String msg = "success";
    private Object data;
    private String code = "200";

    public Jo() {
    }

    public String getMsg() {
        return this.msg;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public Object getData() {
        return this.data;
    }

    public String getCode() {
        return this.code;
    }

    public Jo success(){
        this.success = true;
        return this;
    }

    public Jo suceess(String msg){
        this.msg = msg;
        return this.success();
    }

    public Jo fail(){
        this.success = false;
        return this;
    }

    public Jo fail(String msg){
        this.msg = msg;
        return this.fail();
    }

    public Jo sendMsg(String msg){
        this.msg = msg;
        return this;
    }

    public Jo sendData(Object data){
        this.data = data;
        return this;
    }

    public Jo setCode(String code){
        this.code = code;
        return this;
    }
}
