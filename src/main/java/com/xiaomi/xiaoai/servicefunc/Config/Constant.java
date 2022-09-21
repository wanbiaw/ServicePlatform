package com.xiaomi.xiaoai.servicefunc.Config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "constant")
@PropertySource("classpath:constant.properties")
@Data
public class Constant {

    private String GET;
    private String POST;
    private String mockHost;
    private Integer mockPort;
    private String mockMappingFilesLocation;
}
