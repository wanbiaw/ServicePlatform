package com.xiaomi.xiaoai.servicefunc.Service;


import com.xiaomi.xiaoai.servicefunc.entity.TbDeviceEntity;

import java.util.List;

public interface DeviceService {
    List<TbDeviceEntity> getDevices(Integer[] ids, Integer prodId,
                                    String appId, String app, String info,
                                    String domains, Integer[] type, Integer creatorId,
                                    Integer isDelete, String version, Integer isMonitor,
                                    String category,String screen,Integer start,Integer end);

    Integer getDevicesNum(Integer[] ids, Integer prodId,
                          String appId, String app, String info,
                          String domains, Integer[] type, Integer creatorId,
                          Integer isDelete, String version, Integer isMonitor,
                          String category,String screen);

    Integer deleteDevice(Integer[] ids, Integer prodId,
                         String appId, Integer isDelete);

    Integer updateDevice(Integer[] ids, Integer prodId, String appId, String app,
                         String info, Integer type, String version,
                         String domains, String params, String conf,
                         String updater_name, Integer isDelete, Integer isMonitor,
                         String updated_at, String deleted_at, String category);

    List<String> getDevicesName(Integer isMonitor,Integer prodId);
}
