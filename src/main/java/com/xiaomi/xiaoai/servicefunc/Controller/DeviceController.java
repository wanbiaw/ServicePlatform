package com.xiaomi.xiaoai.servicefunc.Controller;

import com.xiaomi.xiaoai.servicefunc.dao.impl.TbDeviceDaoImpl;
import com.xiaomi.xiaoai.servicefunc.entity.TbDeviceEntity;
import com.xiaomi.xiaoai.servicefunc.wrapper.TbDeviceQuery;
import com.xiaomi.xiaoai.servicefunc.wrapper.TbDeviceUpdate;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/test")
public class DeviceController {

    @Resource
    private TbDeviceDaoImpl tbDeviceDao;

    @RequestMapping(value = "/getDevices",method = RequestMethod.GET)
    public List<TbDeviceEntity> getDevices(){
        TbDeviceQuery devices = tbDeviceDao.getDevices(null, 1, null, null, null, null,
                null, null, null, null, null,"Device/TV","Large",0,2);
        log.info("print");
        return tbDeviceDao.listEntity(devices);
    }

    @RequestMapping(value = "/getDevicesNum",method = RequestMethod.GET)
    public Integer getDevicesNum(){
        TbDeviceQuery devicesNum = tbDeviceDao.getDevicesNum(null, 1, null, null, null, null,
                null, null, null, null, null, null, null);
        return tbDeviceDao.count(devicesNum);
    }

    @RequestMapping(value = "/deleteDevice",method = RequestMethod.GET)
    public Integer deleteDevice(){
        TbDeviceQuery deleteDevice = tbDeviceDao.deleteDevice(new Integer[]{412}, 1, null, null);
        return tbDeviceDao.deleteBy(deleteDevice);
    }

    @RequestMapping(value = "/updateDevice",method = RequestMethod.GET)
    public Integer updateDevice(){
        TbDeviceUpdate updateDevice = tbDeviceDao.updateDevice(new Integer[]{412}, 1, null, null, null, null,
                null, null, null, null, null, null, null, null,
                null, null);
        return tbDeviceDao.updateBy(updateDevice);
    }
}

