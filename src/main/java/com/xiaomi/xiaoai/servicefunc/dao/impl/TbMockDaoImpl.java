package com.xiaomi.xiaoai.servicefunc.dao.impl;

import cn.org.atool.fluent.mybatis.If;
import com.xiaomi.xiaoai.servicefunc.dao.base.TbMockBaseDao;
import com.xiaomi.xiaoai.servicefunc.dao.intf.TbMockDao;
import com.xiaomi.xiaoai.servicefunc.entity.TbMockEntity;
import com.xiaomi.xiaoai.servicefunc.mapper.TbDeviceMapper;
import com.xiaomi.xiaoai.servicefunc.mapper.TbMockMapper;
import com.xiaomi.xiaoai.servicefunc.wrapper.TbMockQuery;
import com.xiaomi.xiaoai.servicefunc.wrapper.TbMockUpdate;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;

/**
 * TbMockDaoImpl: 数据操作接口实现
 *
 * 这只是一个减少手工创建的模板文件
 * 可以任意添加方法和实现, 更改作者和重定义类名
 * <p/>@author Powered By Fluent Mybatis
 */
@Repository
public class TbMockDaoImpl extends TbMockBaseDao implements TbMockDao {

    @Override
    public TbMockQuery getHttpMocks(
            Integer[] ids, String name, String url,
            String requestMethod, Date createdAt, Date updatedAt)
    {
        return new TbMockQuery()
                .where.applyFunc("1=1")
                .and.id().in(ids, If::notNull)
                .and.name().like(name,If::notNull)
                .and.url().like(url,If::notNull)
                .and.requestMethod().eq(requestMethod,If::notNull)
                .and.createdAt().ge(createdAt)
                .and.updatedAt().ge(updatedAt).end();
    }

    @Override
    public Boolean saveOrUpdateMock(TbMockEntity mockEntity) {
        return mapper.saveOrUpdate(mockEntity);
    }




}
