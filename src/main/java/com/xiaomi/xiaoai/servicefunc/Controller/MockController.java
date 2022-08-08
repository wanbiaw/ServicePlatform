package com.xiaomi.xiaoai.servicefunc.Controller;

import com.xiaomi.xiaoai.servicefunc.Service.MockService;
import com.xiaomi.xiaoai.servicefunc.entity.TbMockEntity;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.expression.Ids;

import javax.annotation.Resource;
import javax.swing.text.DateFormatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/test")
public class MockController {

    @Resource
    private MockService mockService;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


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
}
