package com.xiaomi.xiaoai.servicefunc.Controller;


import com.xiaomi.xiaoai.servicefunc.dao.impl.TbAlarmReasonDaoImpl;
import com.xiaomi.xiaoai.servicefunc.dao.impl.TbDeviceDaoImpl;
import com.xiaomi.xiaoai.servicefunc.entity.TbAlarmReasonEntity;
import com.xiaomi.xiaoai.servicefunc.entity.TbDeviceEntity;
import com.xiaomi.xiaoai.servicefunc.mapper.TbDeviceMapper;
import com.xiaomi.xiaoai.servicefunc.wrapper.TbDeviceQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/case")
public class caseController {
    @Autowired
    private TbAlarmReasonDaoImpl tbAlarmReasonDao;
    @Autowired
    private TbDeviceDaoImpl tbDeviceDao;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public List<TbDeviceEntity> test(){
        TbDeviceQuery query = tbDeviceDao.getDevices(null, 1, null, null, null, null,
                null, null, null, null, null,"Device/TV",null,null,null);

        return tbDeviceDao.listEntity(query);
    }
}
