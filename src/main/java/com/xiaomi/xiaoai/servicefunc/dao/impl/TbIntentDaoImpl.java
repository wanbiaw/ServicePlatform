package com.xiaomi.xiaoai.servicefunc.dao.impl;

import com.xiaomi.xiaoai.servicefunc.dao.base.TbIntentBaseDao;
import com.xiaomi.xiaoai.servicefunc.dao.intf.TbIntentDao;
import com.xiaomi.xiaoai.servicefunc.entity.TbIntentEntity;
import com.xiaomi.xiaoai.servicefunc.mapper.TbIntentMapper;
import com.xiaomi.xiaoai.servicefunc.wrapper.TbIntentQuery;
import com.xiaomi.xiaoai.servicefunc.wrapper.TbIntentUpdate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TbIntentDaoImpl: 数据操作接口实现
 *
 * 这只是一个减少手工创建的模板文件
 * 可以任意添加方法和实现, 更改作者和重定义类名
 * <p/>@author Powered By Fluent Mybatis
 */
@Repository
public class TbIntentDaoImpl extends TbIntentBaseDao implements TbIntentDao {
    @Override
    public List<TbIntentEntity> selectAll() {
        TbIntentQuery query = query()
                .select.deviceInfo().intent().publishedSlots().screen().skill().slots().envs().createdAt().end()
                .where.applyFunc("1=1").end().orderBy.createdAt().desc().end();
        return this.listEntity(query);
    }

    @Override
    public Integer selectCount() {
        TbIntentQuery query = query().where.applyFunc("1=1").end();
        return this.count(query);
    }

    @Override
    public Integer removeAll() {
        TbIntentQuery query = query().where.applyFunc("1=1").end();
        return this.deleteBy(query);
    }
}
