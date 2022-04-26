package com.xiaomi.xiaoai.servicefunc.ExceptionHandler;

public enum ErrorEnum {
    //数据操作返回定义
    SUCCESS(200,"OK~"),
    NOT_FOUND(404, "未找到该资源!"),
    INTERNAL_SERVER_ERROR(500, "服务器炸了，联系wangxiang10");

    private Integer errorCode;
    private String errorMsg;

    ErrorEnum(Integer errorCode, String errorMsg){
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
