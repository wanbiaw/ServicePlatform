package com.xiaomi.xiaoai.servicefunc.Controller;

import com.xiaomi.xiaoai.servicefunc.ExceptionHandler.ErrorEnum;
import com.xiaomi.xiaoai.servicefunc.ExceptionHandler.ResponseResult;
import com.xiaomi.xiaoai.servicefunc.Service.MockService;
import com.xiaomi.xiaoai.servicefunc.entity.TbMockEntity;
import com.xiaomi.xiaoai.servicefunc.util.ObModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/test")
public class MockController {

    @Resource
    private MockService mockService;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private ResponseResult<Object> rr = new ResponseResult<>();


    @RequestMapping(value = "/getHttpMocks",method = RequestMethod.GET)
    public List<TbMockEntity> getMocks(
            @RequestParam(value = "ids[]",required = false) Integer[] mockIds,
            @RequestParam(value = "name",required = false) String mockName,
            @RequestParam(value = "url",required = false) String mockUrl,
            @RequestParam(value = "requestMethod",required = false) String mockRequestMethod,
            @RequestParam(value = "createdAt",required = false) Date createdAt,
            @RequestParam(value = "updatedAt",required = false) Date updatedAt
    ) throws ParseException {
        return mockService.getHttpMocks(mockIds, mockName, mockUrl, mockRequestMethod,
                createdAt == null?sdf.parse("1970-01-01"):createdAt,
                updatedAt == null?sdf.parse("1970-01-01"):updatedAt);
    }

    @RequestMapping(value = "/saveHttpMock",method = RequestMethod.POST)
    public ResponseResult saveMock(
        @RequestBody TbMockEntity mockEntity
    ){
        mockService.saveHttpMocks(mockEntity);
        return rr.responseResult(ErrorEnum.SUCCESS,true,"Mock更新成功！");
    }

    @RequestMapping(value = "/delHttpMock",method = RequestMethod.GET)
    public ResponseResult deleteMock(
        @RequestBody Integer[] ids
    ){

        return null;
    }
}
