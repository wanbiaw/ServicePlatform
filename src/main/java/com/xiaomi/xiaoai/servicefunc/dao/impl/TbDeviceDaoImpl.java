package com.xiaomi.xiaoai.servicefunc.dao.impl;

import cn.org.atool.fluent.mybatis.If;
import cn.org.atool.fluent.mybatis.base.model.SqlOp;
import com.xiaomi.xiaoai.servicefunc.dao.base.TbDeviceBaseDao;
import com.xiaomi.xiaoai.servicefunc.dao.intf.TbDeviceDao;
import com.xiaomi.xiaoai.servicefunc.wrapper.TbDeviceQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TbDeviceDaoImpl: 数据操作接口实现
 *
 * 这只是一个减少手工创建的模板文件
 * 可以任意添加方法和实现, 更改作者和重定义类名
 * <p/>@author Powered By Fluent Mybatis
 */
@Repository
public class TbDeviceDaoImpl extends TbDeviceBaseDao implements TbDeviceDao {
    public TbDeviceQuery getDevices(
            Integer[] ids, Integer prodId,
            Integer appId, String app, String info,
            String domain, Integer[] type, Integer creatorId,
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
                .and.domains().eq(domain, If::notNull)
                .and.type().in(type, If::notNull)
                .and.creatorId().eq(creatorId, If::notNull)
                .and.isDelete().eq(isDelete, If::notNull)
                .and.version().eq(version, If::notNull)
                .and.isMonitor().eq(isMonitor, If::notNull)
                .and.category().apply(If::notNull, SqlOp.LIKE,"json_extract(category,'$.category') like CONCAT('%',"+ category +",'%')")
                .and.applyIf(If::notNull,"json_extract(category,'$.screen') like CONCAT('%',?,'%')",screen).end()
                .orderBy.id().asc().end()
                .limit(start,end);
    }
}
