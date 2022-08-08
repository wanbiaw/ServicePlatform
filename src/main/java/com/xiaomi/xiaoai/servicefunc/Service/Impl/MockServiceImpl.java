package com.xiaomi.xiaoai.servicefunc.Service.Impl;

import com.xiaomi.xiaoai.servicefunc.Service.MockService;
import com.xiaomi.xiaoai.servicefunc.dao.impl.TbMockDaoImpl;
import com.xiaomi.xiaoai.servicefunc.dao.intf.TbMockDao;
import com.xiaomi.xiaoai.servicefunc.entity.TbMockEntity;
import com.xiaomi.xiaoai.servicefunc.wrapper.TbMockQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class MockServiceImpl implements MockService {

    @Resource
    private TbMockDaoImpl tbMockDao;

    @Override
    public List<TbMockEntity> getHttpMocks(
            Integer[] Ids, String name, String url, String requestMethod,
            Date createdAt, Date updatedAt)
    {
        TbMockQuery httpMocks =
                tbMockDao.getHttpMocks(Ids, name, url, requestMethod, createdAt, updatedAt);
        return tbMockDao.listEntity(httpMocks);
    }
}
