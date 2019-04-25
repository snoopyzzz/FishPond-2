package com.zz.util;

/**
 * Created with IntelliJ IDEA.
 * User: 钊钊
 * Date: 2018/12/8
 * Time: 21:18
 * Description: No Description
 */
public class FishPondRuntimeException extends RuntimeException {
    private final int code;
    private final String description;
    private final boolean success;

    public FishPondRuntimeException (ReturnCodeEnum returnCodeEnum, String description){
        super(returnCodeEnum.getMemo());
        this.code = returnCodeEnum.getCode();
        this.description = returnCodeEnum.getMemo();
        this.success = false;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public boolean isSuccess() {
        return success;
    }
}
