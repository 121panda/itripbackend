package com.ytzl.itrip.utils.exception;

/**
 * Created by Administrator on 2018/4/23 0023.
 */
public class ItripExcepion extends  Exception{
    //错误码
    private String errorCode;


    public ItripExcepion(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ItripExcepion() {
    }

    public ItripExcepion(String message) {
        super(message);
    }

    public String getErrorCode() {
        return errorCode;
    }
}
