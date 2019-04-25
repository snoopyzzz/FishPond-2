package com.zz.util;

/**
 * Created with IntelliJ IDEA.
 * User: 钊钊
 * Date: 2018/12/8
 * Time: 21:22
 * Description: No Description
 */
public enum ReturnCodeEnum {
    SUCCESS(0, "Success"),
    DATE_PARSE_EXCEPTION_ERROR(101, "数据分析异常"),
    USER_ERROR(102, "用户模块异常"),
    FISH_ERROR(103, "鱼模块异常"),
    POND_ERROR(104, "池塘模块异常");

    private int code;
    private String memo;

    ReturnCodeEnum(int code, String memo){
        this.code = code;
        this.memo = memo;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
