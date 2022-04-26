package com.xiaomi.xiaoai.servicefunc.ExceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * @Author wangxiang10
     * @Description  处理一般异常
     * @Date 20:36 2022/4/26
     * @Param [e]
     * @return com.xiaomi.xiaoai.servicefunc.ExceptionHandler.ExceptionResult
     **/
    @ExceptionHandler(value = Exception.class)
    public ExceptionResult exceptionHandler(Exception e){
        logger.info("Exception ==>" + e.getMessage());
        return ExceptionResult.commonError(ErrorEnum.INTERNAL_SERVER_ERROR);
    }

    /**
     * @Author wangxiang10
     * @Description  处理自定义异常
     * @Date 20:38 2022/4/26
     * @Param [defineException]
     * @return com.xiaomi.xiaoai.servicefunc.ExceptionHandler.ExceptionResult
     **/
    @ExceptionHandler(value = DefineException.class)
    public ExceptionResult defineExceptionHandler(DefineException defineException){
        logger.info("DefineException ==>" + defineException.getErrorMsg());
        return ExceptionResult.myDefineError(defineException);
    }
}
