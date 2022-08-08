package com.xiaomi.xiaoai.servicefunc.util;

import cn.org.atool.generator.FileGenerator;
import cn.org.atool.generator.annotation.Table;
import cn.org.atool.generator.annotation.Tables;

public class EntityGenerator {

    static final String url = "jdbc:mysql://10.38.154.14:3306/db_monitor?useUnicode=true&characterEncoding=utf8&characterSetResults=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&allowMultiQueries=true&transformedBitIsBoolean=true&serverTimezone=PRC";
    static final String username = "db_monitor_staging";
    static final String password = "db_monitor_staging";

    public static void main(String[] args) {
        FileGenerator.build(Empty.class);
    }

    @Tables(
            url = url, username = username, password = password,
            srcDir = "src/main/java",
            basePack = "com.xiaomi.xiaoai.servicefunc",
            daoDir = "src/main/java",
            tables = {@Table(value = {
                    "tb_result","tb_monitor_dashboard","tb_plan","tb_device",
                    "tb_case_test","tb_intent","tb_mock"})}
    )

    static class Empty{
    }
}
