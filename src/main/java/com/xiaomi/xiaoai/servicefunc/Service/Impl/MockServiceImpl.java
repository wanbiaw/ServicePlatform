package com.xiaomi.xiaoai.servicefunc.Service.Impl;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import com.xiaomi.xiaoai.servicefunc.Config.Constant;
import com.xiaomi.xiaoai.servicefunc.Service.MockService;
import com.xiaomi.xiaoai.servicefunc.dao.impl.TbMockDaoImpl;
import com.xiaomi.xiaoai.servicefunc.entity.TbMockEntity;
import com.xiaomi.xiaoai.servicefunc.util.ObModel;
import com.xiaomi.xiaoai.servicefunc.wrapper.TbMockQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

@Service
public class MockServiceImpl implements MockService {

    private Logger logger = LoggerFactory.getLogger(MockServiceImpl.class);

    @Resource
    private TbMockDaoImpl tbMockDao;
    @Resource
    private Constant constant;

    @Override
    public List<TbMockEntity> getHttpMocks(
            Integer[] Ids, String name, String url, String requestMethod,
            Date createdAt, Date updatedAt)
    {
        TbMockQuery httpMocks =
                tbMockDao.getHttpMocks(Ids, name, url, requestMethod, createdAt, updatedAt);
        return tbMockDao.listEntity(httpMocks);
    }

    @Override
    public void saveHttpMocks(TbMockEntity mockEntity) {
        logger.info("saveHttpMocks()执行===================>");
        if (mockEntity.getUuid()!=null && mockEntity.getUuid().contains("-")){
            File file = new File(constant.getMockMappingFilesLocation()+File.separator+"mappings");
            logger.info("保存路径为=============》"+constant.getMockMappingFilesLocation()+File.separator+"mappings");
            File[] mappings = file.listFiles();
            List<File> existMapping = Arrays.stream(mappings).filter(p -> p.getAbsolutePath().contains(mockEntity.getUuid())).collect(Collectors.toList());
            if (existMapping.size()>0){
                existMapping.get(0).delete();
            }
        }
        configureFor(constant.getMockHost(),constant.getMockPort());
        StubMapping stubMapping;
        MappingBuilder mappingBuilder;
        if (mockEntity.getRequestMethod().equalsIgnoreCase(constant.getGET())){
            mappingBuilder = configHeaderAndParams(mockEntity, get(urlPathEqualTo(mockEntity.getUrl())))
                            .willReturn(aResponse()
                                .withStatus(mockEntity.getStatus())
                                .withBody(mockEntity.getResponse())
                                .withFixedDelay(mockEntity.getDelay()));
        }else{
            mappingBuilder = configHeaderAndParams(mockEntity, post(urlPathEqualTo(mockEntity.getUrl())))
                            .withRequestBody(equalToJson(mockEntity.getRequestData()))
                            .willReturn(aResponse()
                                .withStatus(mockEntity.getStatus())
                                .withBody(mockEntity.getResponse())
                                .withFixedDelay(mockEntity.getDelay()));
        }
        System.out.println(mappingBuilder);
        stubMapping = stubFor(mappingBuilder);
        mockEntity.setUuid(stubMapping.getUuid().toString());
        mockEntity.setRequestData(
                mockEntity.getRequestMethod().equals(constant.getGET())?"{}": mockEntity.getRequestData());
        mockEntity.setUpdatedAt(new Date());
        mockEntity.setCreatedAt(
                mockEntity.getCreatedAt() == null ?new Date():mockEntity.getCreatedAt());
        mockEntity.setUrl(mockEntity.getUrl().contains(constant.getMockHost()) ? mockEntity.getUrl() :
                constant.getMockHost()+":"+constant.getMockPort()+mockEntity.getUrl());
        tbMockDao.saveOrUpdateMock(mockEntity);
        WireMock.saveAllMappings();
    }

    @Override
    public void delHttpMocks(Integer[] ids) {
        logger.info("delHttpMocks执行===================>");
        tbMockDao.delHttpMocks(ids);
    }

    /**
     * @Author wangxiang10
     * @Description  配置请求头和请求参数值
     * @Date 15:14 2022/8/11
     * @Param [mockEntity, mappingBuilder]
     * @return com.github.tomakehurst.wiremock.client.MappingBuilder
     **/
    public MappingBuilder configHeaderAndParams(
            TbMockEntity mockEntity,
            MappingBuilder mappingBuilder
    ){
        logger.info("configHeaderOrParams()执行===================>");
        Map headerMap = new ObModel(mockEntity.getHeader()).toMap();
        Map paramMap = new ObModel(mockEntity.getParam()).toMap();
        headerMap.keySet().stream().iterator().forEachRemaining(p->{
            mappingBuilder.withHeader((String) p,equalTo((String) headerMap.get(p)));
        });
        paramMap.keySet().stream().iterator().forEachRemaining(p->{
            mappingBuilder.withQueryParam((String) p,equalTo((String) paramMap.get(p)));
        });
        return mappingBuilder;
    }


}
