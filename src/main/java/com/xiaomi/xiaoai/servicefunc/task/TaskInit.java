//package com.xiaomi.xiaoai.servicefunc.task;
//
//import com.xiaomi.xms.plans.client.AbstractPlanTask;
//import com.xiaomi.xms.plans.client.PlanManager;
//import com.xiaomi.xms.plans.client.vo.CountryDomainEnum;
//import com.xiaomi.xms.plans.client.vo.EnvEnum;
//import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.function.client.WebClient;
//
//import javax.annotation.PostConstruct;
//import java.util.HashSet;
//
//@Component
//public class TaskInit {
//
//    @PostConstruct
//    public void init(){
//        HashSet<AbstractPlanTask> planTasks = new HashSet<>();
//
////        SyncMonitorDataTask syncMonitorDataTask = new SyncMonitorDataTask();
////        syncMonitorDataTask.setName("syncMonitorDataTask");
////        syncMonitorDataTask.setAllowCountryDomains(new CountryDomainEnum[]{CountryDomainEnum.CN});
////        syncMonitorDataTask.setQuartzCron("0 0 1 * * ? *");
//////        syncMonitorDataTask.setQuartzCron("0 5/2 * * * ? *");
////        syncMonitorDataTask.setDesc("定期获取前一天的监控数据，每晚01:00执行(王翔)");
////        planTasks.add(syncMonitorDataTask);
//
//        PlanManager planManager = new PlanManager();
//        planManager.setEnv(EnvEnum.TEST);
////        planManager.setEnv(EnvEnum.PROD);
//        planManager.setCountryDomain(CountryDomainEnum.CN);
////        planManager.setProjectUid("069a5692a65240449363b2a11207ca6e");
//        planManager.setProjectUid("e864c13bd6b340398d707f58e2397e30");
//        planManager.setPlanTaskSet(planTasks);
//        planManager.setAutoStartup(true);
//        planManager.setInitThreadSize(1);
//        planManager.setMaxThreadSize(10);
//        planManager.init();
//    }
//}
