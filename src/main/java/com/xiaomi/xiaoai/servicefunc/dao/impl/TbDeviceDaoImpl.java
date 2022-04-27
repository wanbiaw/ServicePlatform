package com.xiaomi.xiaoai.servicefunc.dao.impl;

import cn.org.atool.fluent.mybatis.If;
import com.xiaomi.xiaoai.servicefunc.dao.base.TbDeviceBaseDao;
import com.xiaomi.xiaoai.servicefunc.dao.intf.TbDeviceDao;
import com.xiaomi.xiaoai.servicefunc.entity.TbDeviceEntity;
import com.xiaomi.xiaoai.servicefunc.helper.TbDeviceSegment;
import com.xiaomi.xiaoai.servicefunc.mapper.TbDeviceMapper;
import com.xiaomi.xiaoai.servicefunc.wrapper.TbDeviceQuery;
import com.xiaomi.xiaoai.servicefunc.wrapper.TbDeviceUpdate;
import org.springframework.stereotype.Repository;

/**
 * TbDeviceDaoImpl: 数据操作接口实现
 *
 * 这只是一个减少手工创建的模板文件
 * 可以任意添加方法和实现, 更改作者和重定义类名
 * <p/>@author Powered By Fluent Mybatis
 */
@Repository
public class TbDeviceDaoImpl extends TbDeviceBaseDao implements TbDeviceDao {

    /**
     * @Author wangxiang10
     * @Description  获取设备信息
     * @Date 16:57 2022/4/25
     * @Param [ids, prodId, appId, app, info, domain, type, creatorId, isDelete, version, isMonitor, category, screen, start, end]
     * @return com.xiaomi.xiaoai.servicefunc.wrapper.TbDeviceQuery
     **/
    public TbDeviceQuery getDevices(
            Integer[] ids, Integer prodId,
            String appId, String app, String info,
            String domains, Integer[] type, Integer creatorId,
            Integer isDelete, String version, Integer isMonitor,
            String category,String screen,Integer start,Integer end
            ){
        return new TbDeviceQuery()
                .where.applyFunc("1=1")
                .and.id().in(ids,If::notNull)
                .and.prodId().eq(prodId, If::notNull)
                .and.appId().eq(appId, If::notNull)
                .and.app().eq(app, If::notNull)
                .and.info().eq(info, If::notNull)
                .and.domains().eq(domains, If::notNull)
                .and.type().in(type, If::notNull)
                .and.creatorId().eq(creatorId, If::notNull)
                .and.isDelete().eq(isDelete, If::notNull)
                .and.version().eq(version, If::notNull)
                .and.isMonitor().eq(isMonitor, If::notNull)
                .and.apply("json_extract(category,'$.category')").like(category,If::notNull)
                .and.apply("json_extract(category,'$.screen')").like(screen,If::notNull).end()
                .orderBy.id().asc().end()
                .limit(start,end);
    }



    /**
     * @Author wangxiang10
     * @Description  获取设备数量
     * @Date 16:57 2022/4/25
     * @Param [ids, prodId, appId, app, info, domain, type, creatorId, isDelete, version, isMonitor, category, screen]
     * @return com.xiaomi.xiaoai.servicefunc.wrapper.TbDeviceQuery
     **/
    public TbDeviceQuery getDevicesNum(
            Integer[] ids, Integer prodId,
            String appId, String app, String info,
            String domains, Integer[] type, Integer creatorId,
            Integer isDelete, String version, Integer isMonitor,
            String category,String screen
    ){
        return new TbDeviceQuery()
                .where.applyFunc("1=1")
                .and.id().in(ids,If::notNull)
                .and.prodId().eq(prodId, If::notNull)
                .and.appId().eq(appId, If::notNull)
                .and.app().eq(app, If::notNull)
                .and.info().eq(info, If::notNull)
                .and.domains().eq(domains, If::notNull)
                .and.type().in(type, If::notNull)
                .and.creatorId().eq(creatorId, If::notNull)
                .and.isDelete().eq(isDelete, If::notNull)
                .and.version().eq(version, If::notNull)
                .and.isMonitor().eq(isMonitor, If::notNull)
                .and.apply("json_extract(category,'$.category')").like(category,If::notNull)
                .and.apply("json_extract(category,'$.screen')").like(screen,If::notNull).end();
    }



    /**
     * @Author wangxiang10
     * @Description  删除设备信息
     * @Date 17:09 2022/4/25
     * @Param [ids, prodId, appId, isDelete]
     * @return com.xiaomi.xiaoai.servicefunc.wrapper.TbDeviceQuery
     **/
    public TbDeviceQuery deleteDevice(
            Integer[] ids, Integer prodId,
            String appId, Integer isDelete
    ){
        return new TbDeviceQuery()
                .where.applyFunc("1=1")
                .and.id().in(ids,If::notNull)
                .and.prodId().eq(prodId,If::notNull)
                .and.appId().eq(appId,If::notNull)
                .and.isDelete().eq(isDelete,If::notNull).end();
    }



    /**
     * @Author wangxiang10
     * @Description  更新设备信息
     * @Date 17:26 2022/4/25
     * @Param [ids, prodId, appId, app, info, type, version, domains, params, conf, updater_name, isDelete, isMonitor, updated_at, deleted_at, category]
     * @return com.xiaomi.xiaoai.servicefunc.wrapper.TbDeviceUpdate
     **/
    public TbDeviceUpdate updateDevice(
            Integer[] ids, Integer prodId, String appId, String app,
            String info, Integer type, String version,
            String domains, String params, String conf,
            String updater_name, Integer isDelete, Integer isMonitor,
            String updated_at, String deleted_at, String category
    ){
        return new TbDeviceUpdate()
                .set.prodId().is(prodId, If::notNull)
                .set.appId().is(appId, If::notNull)
                .set.app().is(app, If::notNull)
                .set.info().is(info, If::notNull)
                .set.type().is(type, If::notNull)
                .set.version().is(version, If::notNull)
                .set.domains().is(domains, If::notNull)
                .set.params().is(params, If::notNull)
                .set.conf().is(conf, If::notNull)
                .set.updaterName().is(updater_name, If::notNull)
                .set.isDelete().is(isDelete, If::notNull)
                .set.isMonitor().is(isMonitor, If::notNull)
                .set.updatedAt().is(updated_at, If::notNull)
                .set.deletedAt().is(deleted_at, If::notNull)
                .set.category().is(category, If::notNull).end()
                .where.applyFunc("1=1")
                .and.id().in(ids,If::notNull).end();
    }

    public TbDeviceQuery getDevicesName(Integer isMonitor,Integer prodId){
        return new TbDeviceQuery()
                .select.app().end()
                .where.isMonitor().eq(isMonitor,If::notNull)
                .prodId().eq(prodId,If::notNull).end();
    }
}
