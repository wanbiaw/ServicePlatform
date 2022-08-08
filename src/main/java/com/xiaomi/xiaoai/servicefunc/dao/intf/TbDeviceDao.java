package com.xiaomi.xiaoai.servicefunc.dao.intf;

import cn.org.atool.fluent.mybatis.base.IBaseDao;
import com.xiaomi.xiaoai.servicefunc.entity.TbDeviceEntity;
import com.xiaomi.xiaoai.servicefunc.wrapper.TbDeviceQuery;
import com.xiaomi.xiaoai.servicefunc.wrapper.TbDeviceUpdate;

/**
 * TbDeviceDao: 数据操作接口
 *
 * 这只是一个减少手工创建的模板文件
 * 可以任意添加方法和实现, 更改作者和重定义类名
 * <p/>@author Powered By Fluent Mybatis
 */
public interface TbDeviceDao extends IBaseDao<TbDeviceEntity> {
    TbDeviceQuery getDevices(
            Integer[] ids, Integer prodId,
            String appId, String app, String info,
            String domains, Integer[] type, Integer creatorId,
            Integer isDelete, String version, Integer isMonitor,
            String category,String screen,Integer start,Integer end
    );

    TbDeviceUpdate updateDevice(
            Integer[] ids, Integer prodId, String appId, String app,
            String info, Integer type, String version,
            String domains, String params, String conf,
            String updater_name, Integer isDelete, Integer isMonitor,
            String updated_at, String deleted_at, String category
    );

    TbDeviceQuery deleteDevice(
            Integer[] ids, Integer prodId,
            String appId, Integer isDelete
    );

    TbDeviceQuery getDevicesNum(
            Integer[] ids, Integer prodId,
            String appId, String app, String info,
            String domains, Integer[] type, Integer creatorId,
            Integer isDelete, String version, Integer isMonitor,
            String category,String screen
    );

    TbDeviceQuery getDevicesName(Integer isMonitor,Integer prodId);


}
