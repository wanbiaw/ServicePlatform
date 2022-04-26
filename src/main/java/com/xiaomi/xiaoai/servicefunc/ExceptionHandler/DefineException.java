package com.xiaomi.xiaoai.servicefunc.ExceptionHandler;

import lombok.Data;

@Data
public class DefineException extends RuntimeException{
    protected Integer errorCode;
    protected String errorMsg;

    public DefineException() {
    }

    public DefineException(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
