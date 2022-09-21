package com.xiaomi.xiaoai.servicefunc.Service;

import cn.org.atool.fluent.mybatis.refs.FieldRef;
import com.xiaomi.xiaoai.servicefunc.entity.TbMockEntity;

import java.util.Date;
import java.util.List;

public interface MockService {
    List<TbMockEntity> getHttpMocks(Integer[] Ids, String name, String url,
                                    String requestMethod, Date createdAt, Date updatedAt);

    void saveHttpMocks(TbMockEntity mockEntity);

    void delHttpMocks(Integer[] ids);
}
