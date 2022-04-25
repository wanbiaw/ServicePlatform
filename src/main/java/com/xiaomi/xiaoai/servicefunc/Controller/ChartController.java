package com.xiaomi.xiaoai.servicefunc.Controller;


import com.xiaomi.xiaoai.servicefunc.entity.TbMonitorDashboardEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/test")
public class ChartController {
    private String yesterday;
    private String oneWeekAgo;


    @RequestMapping(value = "/getMonitorDashboard",method = RequestMethod.GET)
    public List<TbMonitorDashboardEntity> getMonitorDashboard(){
        getTime();
        return null;
    }


    public void getTime(){
        Calendar cdDay = Calendar.getInstance();
        cdDay.add(Calendar.DATE,-1);
        yesterday = new SimpleDateFormat("yyyy-MM-dd").format(cdDay.getTimeInMillis());
        cdDay.add(Calendar.DATE,-5);
        oneWeekAgo = new SimpleDateFormat("yyyy-MM-dd").format(cdDay.getTimeInMillis());
    }
}
