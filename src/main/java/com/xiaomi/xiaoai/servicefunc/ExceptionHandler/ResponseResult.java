package com.xiaomi.xiaoai.servicefunc.ExceptionHandler;

import lombok.Data;

@Data
public class ResponseResult<T> {
    //成功状态
    private Boolean success;
    //状态码
    private Integer code;
    //异常信息
    private String msg;
    //数据
    private T data;

    public ResponseResult(){
    }

    //自定义返回结果的构造方法
    public ResponseResult(Boolean success, Integer code, String msg, T data) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * @Author wangxiang10
     * @Description  一般异常的处理返回结果
     * @Date 20:02 2022/4/26
     * @Param [errorEnum]
     * @return com.xiaomi.xiaoai.servicefunc.ExceptionHandler.ExceptionResult
     **/
    public static ResponseResult commonError(ErrorEnum errorEnum,Exception e){
        ResponseResult result = new ResponseResult();
        result.setSuccess(Boolean.FALSE);
        result.setCode(errorEnum.getErrorCode());
        result.setMsg(errorEnum.getErrorMsg());
        result.setData(e.getMessage());
        return result;
    }

    /**
     * @Author wangxiang10
     * @Description  自定义异常处理返回结果
     * @Date 20:08 2022/4/26
     * @Param [defineException]
     * @return com.xiaomi.xiaoai.servicefunc.ExceptionHandler.ExceptionResult
     **/
    public static ResponseResult myDefineError(DefineException defineException){
        ResponseResult result = new ResponseResult();
        result.setSuccess(Boolean.FALSE);
        result.setCode(defineException.getErrorCode());
        result.setMsg(defineException.getErrorMsg());
        result.setData(null);
        return result;
    }


    /**
     * @Author wangxiang10
     * @Description  封装api请求的返回结果
     * @Date 16:02 2022/5/17
     * @Param [errorEnum]
     * @return com.xiaomi.xiaoai.servicefunc.ExceptionHandler.ExceptionResult
     **/
    public ResponseResult responseResult(ErrorEnum statusEnum,Boolean success, Object data){
        ResponseResult result = new ResponseResult();
        result.setSuccess(success);
        result.setCode(statusEnum.getErrorCode());
        result.setMsg(statusEnum.getErrorMsg());
        result.setData(data);
        return result;
    }
}
