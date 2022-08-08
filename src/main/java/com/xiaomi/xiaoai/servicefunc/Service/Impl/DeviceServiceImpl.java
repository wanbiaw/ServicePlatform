package com.xiaomi.xiaoai.servicefunc.Service.Impl;

import com.xiaomi.xiaoai.servicefunc.Service.DeviceService;
import com.xiaomi.xiaoai.servicefunc.dao.impl.TbDeviceDaoImpl;
import com.xiaomi.xiaoai.servicefunc.entity.TbDeviceEntity;
import com.xiaomi.xiaoai.servicefunc.wrapper.TbDeviceQuery;
import com.xiaomi.xiaoai.servicefunc.wrapper.TbDeviceUpdate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Resource
    private TbDeviceDaoImpl tbDeviceDao;

    @Override
    public List<TbDeviceEntity> getDevices(
            Integer[] ids, Integer prodId, String appId, String app, String info, String domains,
            Integer[] type, Integer creatorId, Integer isDelete, String version, Integer isMonitor,
            String category, String screen, Integer start, Integer end)
    {
        TbDeviceQuery devices =
                tbDeviceDao.getDevices(ids, prodId, appId, app, info, domains, type, creatorId, isDelete,
                                        version, isMonitor, category, screen, start, end);
        return tbDeviceDao.listEntity(devices);
    }

    @Override
    public Integer getDevicesNum(
            Integer[] ids, Integer prodId, String appId, String app, String info,
            String domains, Integer[] type, Integer creatorId, Integer isDelete, String version,
            Integer isMonitor, String category, String screen)
    {
        TbDeviceQuery devicesNum =
                tbDeviceDao.getDevicesNum(ids, prodId, appId, app, info, domains, type,
                                            creatorId, isDelete, version, isMonitor, category, screen);
        return tbDeviceDao.count(devicesNum);
    }

    @Override
    public Integer deleteDevice(
            Integer[] ids, Integer prodId, String appId, Integer isDelete)
    {
        TbDeviceQuery deleteDevice = tbDeviceDao.deleteDevice(ids, prodId, appId, isDelete);
        return tbDeviceDao.deleteBy(deleteDevice);
    }

    @Override
    public Integer updateDevice(
            Integer[] ids, Integer prodId, String appId, String app, String info, Integer type,
            String version, String domains, String params, String conf, String updater_name,
            Integer isDelete, Integer isMonitor, String updated_at, String deleted_at, String category)
    {
        TbDeviceUpdate updateDevice =
                tbDeviceDao.updateDevice(ids, prodId, appId, app, info, type, version, domains, params,
                        conf, updater_name, isDelete, isMonitor, updated_at, deleted_at, category);
        return tbDeviceDao.updateBy(updateDevice);
    }

    @Override
    public List<String> getDevicesName(Integer isMonitor, Integer prodId) {
        TbDeviceQuery devicesName = tbDeviceDao.getDevicesName(isMonitor,prodId);
        List<Map<String, Object>> tmpList = tbDeviceDao.listMaps(devicesName);
        ArrayList<String> res = new ArrayList<>();
        for (Map<String,Object> tmp:
             tmpList) {
            res.add(tmp.get("app").toString());
        }
        return res;
    }
}
