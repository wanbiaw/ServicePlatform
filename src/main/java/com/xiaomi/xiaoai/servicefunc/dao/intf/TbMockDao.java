package com.xiaomi.xiaoai.servicefunc.dao.intf;

import cn.org.atool.fluent.mybatis.base.IBaseDao;
import com.xiaomi.xiaoai.servicefunc.entity.TbMockEntity;
import com.xiaomi.xiaoai.servicefunc.wrapper.TbMockQuery;

import java.util.Date;

/**
 * TbMockDao: 数据操作接口
 *
 * 这只是一个减少手工创建的模板文件
 * 可以任意添加方法和实现, 更改作者和重定义类名
 * <p/>@author Powered By Fluent Mybatis
 */
public interface TbMockDao extends IBaseDao<TbMockEntity> {
    TbMockQuery getHttpMocks(Integer[] ids, String name, String url,
                             String requestMethod, Date createdAt, Date updatedAt);

    Boolean saveOrUpdateMock(TbMockEntity mockEntity);

    Integer delHttpMocks(Integer[] ids);
}
