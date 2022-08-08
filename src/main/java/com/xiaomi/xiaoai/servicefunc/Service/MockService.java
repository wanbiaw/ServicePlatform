package com.xiaomi.xiaoai.servicefunc.Service;

import com.xiaomi.xiaoai.servicefunc.entity.TbMockEntity;

import java.util.Date;
import java.util.List;

public interface MockService {
    List<TbMockEntity> getHttpMocks(Integer[] Ids, String name, String url,
                                    String requestMethod, Date createdAt, Date updatedAt);
}
