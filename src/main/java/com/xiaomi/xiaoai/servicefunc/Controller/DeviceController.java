package com.xiaomi.xiaoai.servicefunc.Controller;

import com.xiaomi.xiaoai.servicefunc.Service.DeviceService;
import com.xiaomi.xiaoai.servicefunc.entity.TbDeviceEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/test")
public class DeviceController {

    @Resource
    private DeviceService deviceService;

    @RequestMapping(value = "/getDevices",method = RequestMethod.POST)
    public List<TbDeviceEntity> getDevices(
            @RequestParam(value = "prodId",required = false) Integer prodId,
            @RequestParam(value = "ids[]",required = false) Integer[] ids,
            @RequestParam(value = "appId",required = false) String appId,
            @RequestParam(value = "app",required = false) String app,
            @RequestParam(value = "info",required = false) String info,
            @RequestParam(value = "domains",required = false) String domains,
            @RequestParam(value = "type[]",required = false) Integer[] type,
            @RequestParam(value = "creatorId",required = false) Integer creatorId,
            @RequestParam(value = "isDelete",required = false) Integer isDelete,
            @RequestParam(value = "version",required = false) String version,
            @RequestParam(value = "category",required = false) String category
    ){
        return deviceService.getDevices(ids, prodId, appId, app, info, domains,
                type, creatorId, isDelete, version, null, category, null,0,5);
    }

    @RequestMapping(value = "/getDevicesNum",method = RequestMethod.GET)
    public Integer getDevicesNum(){
        return deviceService.getDevicesNum(null, 1, null, null, null, null,
                null, null, null, null, null, null, null);
    }

    @RequestMapping(value = "/deleteDevice",method = RequestMethod.GET)
    public Integer deleteDevice(){
        return deviceService.deleteDevice(new Integer[]{412}, 1, null, null);
    }

    @RequestMapping(value = "/updateDevice",method = RequestMethod.GET)
    public Integer updateDevice(){
        return deviceService.updateDevice(
                new Integer[]{412}, 1, null, null, null, null, null,
                null, null, null, null, null, null,
                null, null, null);
    }

    @RequestMapping(value = "/getDevicesName",method = RequestMethod.GET)
    public List<String> getDevicesName(){
        return deviceService.getDevicesName(1,1);
    }
}

