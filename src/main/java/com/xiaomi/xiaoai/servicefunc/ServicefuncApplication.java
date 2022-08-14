package com.xiaomi.xiaoai.servicefunc;

import com.xiaomi.xiaoai.servicefunc.Config.Constant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(Constant.class)
public class ServicefuncApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicefuncApplication.class, args);
    }

}
