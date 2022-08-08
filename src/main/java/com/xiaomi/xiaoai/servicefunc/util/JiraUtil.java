package com.xiaomi.xiaoai.servicefunc.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JiraUtil {
    private Logger logger = LoggerFactory.getLogger(JiraUtil.class);
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final String speakToken = "shtk48vA6Bxwzajte5WCtea72ub";
    private static final String sheetId = "10d4a1";
    private FeiShuUtil fsUtil = new FeiShuUtil();
    private final OkHttpClient okhttp = new OkHttpClient.Builder()
            .readTimeout(100,TimeUnit.SECONDS)
            .connectTimeout(60,TimeUnit.SECONDS)
            .connectionPool(new ConnectionPool(32,5, TimeUnit.MINUTES))
            .build();
    private static final Integer SPLIT_NUM = 300;
    private static final String P4T = "p4t";
    private static final String PREV = "prev";
    private static final String PROD = "prod";

    /*
     * @Author wangxiang10
     * @Description  获取手动收集表格的task数据
     * @Date 11:52 2022/5/12
     * @Param []
     * @return java.util.List
     **/
    public List getTaskList(){
        List singleExcel = fsUtil.getSingleExcel(speakToken, sheetId);
        List res = new ArrayList();
        ObModel tmp;
        for (int i = 0; i < singleExcel.size(); i++) {
            if (i<2) continue;
            List<String> row = new ObModel(singleExcel.get(i)).toArray();
            tmp = new ObModel();
            String demandJira = (row.get(0) == null ?"":row.get(0)).trim().replaceAll("\n","");
            String demandName = (row.get(1) == null ?"":row.get(1)).trim().replaceAll("\n","");
            String type = (row.get(2) == null ?"":row.get(2)).trim().replaceAll("\n","");
            String taskJira = (row.get(3) == null ?"":row.get(3)).trim().replaceAll("\n|\u2063","");
            String owner = (row.get(4) == null ?"":row.get(4)).trim().replaceAll("\n","");
            String design_start = (row.get(5) == null ?"":row.get(5)).trim()
                            .replaceAll("\\.","-").replaceAll("\\/","-");
            String design_end = (row.get(6) == null ?"":row.get(6)).trim()
                            .replaceAll("\\.","-").replaceAll("\\/","-");
            Integer case_num = ((Number)Double.valueOf(String.valueOf(row.get(7) == null ? new Double(-1.0):row.get(7)))).intValue();
            String first_sub = (row.get(8) == null ?"":row.get(8)).trim()
                            .replaceAll("\\.","-").replaceAll("\\/","-");
            String second_sub = (row.get(9) == null ?"":row.get(9)).trim()
                            .replaceAll("\\.","-").replaceAll("\\/","-");
            String reject_reason = (row.get(10) == null ?"":row.get(10)).trim().replaceAll("\n","");
            String p4t_test_start = (row.get(11) == null ?"":row.get(11)).trim()
                            .replaceAll("\\.","-").replaceAll("\\/","-");
            String p4t_test_end = (row.get(12) == null ?"":row.get(12)).trim()
                            .replaceAll("\\.","-").replaceAll("\\/","-");
            String prev_test_start = (row.get(13) == null ?"":row.get(13)).trim()
                            .replaceAll("\\.","-").replaceAll("\\/","-");
            String prev_test_end = (row.get(14) == null ?"":row.get(14)).trim()
                            .replaceAll("\\.","-").replaceAll("\\/","-");
            String prod_test_start = (row.get(15) == null ?"":row.get(15)).trim()
                            .replaceAll("\\.","-").replaceAll("\\/","-");
            String prod_test_end = (row.get(16) == null ?"":row.get(16)).trim()
                            .replaceAll("\\.","-").replaceAll("\\/","-");
            String status = (row.get(17) == null ?"":row.get(17)).trim().replaceAll("\n","");
            String link_bug = (row.get(18) == null ?"":row.get(18)).trim().replaceAll("\n","");
            tmp.initMap()
                    .put("demandJira",demandJira).put("demandName",demandName)
                    .put("type",type).put("taskJira",taskJira).put("owner",owner)
                    .put("design_start",design_start).put("design_end",design_end)
                    .put("case_num",case_num).put("first_sub",first_sub)
                    .put("second_sub",second_sub).put("reject_reason",reject_reason)
                    .put("p4t_test_start",p4t_test_start).put("p4t_test_end",p4t_test_end)
                    .put("prev_test_start",prev_test_start).put("prev_test_end",prev_test_end)
                    .put("prod_test_start",prod_test_start).put("prod_test_end",prod_test_end)
                    .put("status",status).put("link_bug",link_bug);
            res.add(tmp.get());
        }
        return res;
    }

    /*
     * @Author wangxiang10
     * @Description  获取task链接的bug的信息
     * @Date 11:52 2022/5/12
     * @Param []
     * @return com.xiaomi.xiaoai.servicefunc.util.ObModel
     **/
    public ObModel getBugsForTask(){
        StringBuilder query = new StringBuilder("");
        StringBuilder jql = new StringBuilder("issuekey in ");
        List taskList = getTaskList();
        for (Object tmp :
                taskList) {
            String taskJira = (String)new ObModel(tmp).toMap().get("taskJira");
            if(!taskJira.equals("")){
                query.append(taskJira).append(",");
            }
        }
        jql.append("(").append(query.substring(0,query.length()-1)).append(")");

        Request request = new Request.Builder().url("http://biu.ai.srv/api/jira").post(
                RequestBody.create(new ObModel().initMap()
                        .put("search", new ObModel().initMap()
                                .put("jql_str", jql.toString())
                                .put("startAt", 0)
                                .put("maxResults", 5000)
                                .put("fields", new ObModel().initArray().add("issuelinks").get())
                                .get()).cString(), JSON)
        ).build();
        ObModel responseBody = null;
        try {
            Response response = okhttp.newCall(request).execute();
            responseBody = new ObModel(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return handleResponse(responseBody.get(),taskList);
    }

    public ObModel handleResponse(Object response,List taskList){
        ObModel bugForTask = new ObModel().initMap();
        ObModel obModel = new ObModel(response);
        List issues = obModel.getM("result/issues").toArray();
        for (Object tmp :
                issues) {
            ObModel ob = new ObModel(tmp);
            String taskKey = (String) ob.get("key").get();
            ObModel taskTime = new ObModel().initMap().put("taskKey", taskKey)
                    .put("p4t_test_start", "").put("prev_test_start", "")
                    .put("prod_test_start", "").put("prod_test_end", "");
            taskList.forEach(idx -> {
                ObModel tmp1 = new ObModel(idx);
                if (tmp1.get("taskJira").get().toString().equals(taskKey))
                    taskTime.put("p4t_test_start",tmp1.get("p4t_test_start").get().toString())
                            .put("prev_test_start",tmp1.get("prev_test_start").get().toString())
                            .put("prod_test_start",tmp1.get("prod_test_start").get().toString())
                            .put("prod_test_end",tmp1.get("prod_test_end").get().toString());
            });
            List issueLinks = ob.getM("fields/issuelinks").toArray();
            String bugKey;
            String bugType;
            for (Object tmp1 :
                    issueLinks) {
                ObModel ob1 = new ObModel(tmp1);
                if (ob1.isContain("outwardIssue")){
                    bugKey = (String) ob1.getM("outwardIssue/key").get();
                    bugType = (String) ob1.getM("outwardIssue/fields/issuetype/name").get();
                    if (bugType.equals("Bug")){
                        bugForTask.put(bugKey,taskTime.toMap());
                    }
                }
                if (ob1.isContain("inwardIssue")){
                    bugKey = (String)ob1.getM("inwardIssue/key").get();
                    bugType = (String) ob1.getM("inwardIssue/fields/issuetype/name").get();
                    if (bugType.equals("Bug")){
                        bugForTask.put(bugKey,taskTime.toMap());
                    }
                }
            }
        }
        return bugForTask;
    }

    public Integer countSplitTime(Integer size){
        return (size+SPLIT_NUM-1)/SPLIT_NUM;
    }

    /*
     * @Author wangxiang10
     * @Description  获取每个bug的详情，用于数据存取
     * @Date 11:53 2022/5/12
     * @Param [bugsForTask]
     * @return void
     **/
    public void getBugsMileStone(ObModel bugsForTask){
        List test_bugs = new ObModel().toArray();
        Map bugsForTaskMap = bugsForTask.toMap();
        List<String> bugsCollect = (List<String>) bugsForTask.toMap().keySet().stream().collect(Collectors.toList());
        Integer splitTime = countSplitTime(bugsCollect.size());
        List<List<String>> splitBugsCollect = new ArrayList<>();
        Stream.iterate(0, n -> n + 1).limit(splitTime)
                .forEach(i -> splitBugsCollect.add(bugsCollect.stream().skip(i*SPLIT_NUM).limit(SPLIT_NUM).collect(Collectors.toList())));
        for (List<String> tmp :
                splitBugsCollect) {
            ObModel temp = new ObModel(getBugsInfo(tmp));
            List issues = temp.getM("result/issues").toArray();
            for (Object bugTmp:
                 issues) {
                ObModel bug = new ObModel(bugTmp);
                String bugKey = (String) bug.get("key").get();
                ObModel bugForTaskTimeMap = new ObModel(bugsForTaskMap.get(bugKey));
                List<String> component = new ArrayList<>();
                bug.getM("fields/components").toArray().forEach(idx -> component.add(new ObModel(idx).toMap().get("name").toString()));
                List<String> labels = (List<String>)bug.getM("fields/labels").toArray();
                ObModel bugInfo = new ObModel().initMap().put("label", labels).put("component", component);
                String openTime = ((String) bug.getM("fields/created").get()).replace("T"," ").replace(".000+0800","");
                String bugStatus = (String) bug.getM("fields/status/name").get();
                List history = bug.getM("changelog/histories").toArray();
                String reopenedTime = getBugHistoryTime("Reopened",history);
                String resolvedTime = getBugHistoryTime("Resolved",history);
                String closedTime = getBugHistoryTime("Closed",history);
                String bugRes = bug.getM("fields/resolution").get() == null ? "Unresolved" : bug.getM("fields/resolution/name").get().toString();
                test_bugs.add(new ObModel().initMap()
                        .put("task_jira", bugForTaskTimeMap.get("taskKey").get().toString()).put("bug_jira", bugKey).put("bug_info", bugInfo.toMap())
                        .put("status", bugStatus).put("opened", openTime).put("reopened", reopenedTime)
                        .put("resolved", resolvedTime).put("closed", closedTime).put("bug_res", bugRes).put("env",getBugEnv(openTime,bugForTaskTimeMap)).toMap());
            }
        }
        System.out.println(111);
    }

    /*
     * @Author wangxiang10
     * @Description  通过比对链接的task的各阶段测试时间段，决定bug的环境归属
     * @Date 15:24 2022/5/12
     * @Param [bugCreatedTime, bugForTaskTimeMap]
     * @return java.lang.String
     **/
    public String getBugEnv(String bugCreatedTime, ObModel bugForTaskTimeMap){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        Date bugCreatedDate = new Date(),
             p4t_test_start = null,
             prev_test_start = null,
             prod_test_start = null,
             prod_test_end = null;
        try {
            bugCreatedDate = sdf.parse(bugCreatedTime);
            p4t_test_start = sdf1.parse(
                    bugForTaskTimeMap.get("p4t_test_start").get().toString().equals("") ?
                    "2100-01-01":bugForTaskTimeMap.get("p4t_test_start").get().toString());
            prev_test_start = sdf1.parse(
                    bugForTaskTimeMap.get("prev_test_start").get().toString().equals("") ?
                    "2100-01-01":bugForTaskTimeMap.get("prev_test_start").get().toString());
            prod_test_start = sdf1.parse(
                    bugForTaskTimeMap.get("prod_test_start").get().toString().equals("") ?
                    "2100-01-01":bugForTaskTimeMap.get("prod_test_start").get().toString());
            prod_test_end = sdf.parse(
                    bugForTaskTimeMap.get("prod_test_end").get().toString().equals("") ?
                    "1970-01-01 00:00:00":bugForTaskTimeMap.get("prod_test_end").get().toString()+" 23:59:59");
        } catch (ParseException e) {
            logger.info("getBugEnv ==>" + e.getMessage());
        }
        if(bugCreatedDate.compareTo(p4t_test_start)>=0 && bugCreatedDate.compareTo(prev_test_start) < 0)
            return P4T;
        else if (bugCreatedDate.compareTo(prev_test_start)>=0 && bugCreatedDate.compareTo(prod_test_start) < 0)
            return PREV;
        else if (bugCreatedDate.compareTo(prod_test_start)>=0 && bugCreatedDate.compareTo(prod_test_end) <= 0)
            return PROD;
        else
            return "";
    }

    /*
     * @Author wangxiang10
     * @Description  获取bug的各个历史最近时间信息
     * @Date 11:54 2022/5/12
     * @Param [timeType, history]
     * @return java.lang.String
     **/
    public String getBugHistoryTime(String timeType,List history){
        String res = "";
        for (int i = history.size()-1; i >=0; i--) {
            ObModel changeLog = new ObModel(history.get(i));
            List items = changeLog.get("items").toArray();
            for (int j = items.size()-1; j >= 0; j--) {
                ObModel item = new ObModel(items.get(j));
                if (item.get("field").toString().equals("status")){
                    if (item.get("toString").toString().equals(timeType))
                        res = changeLog.get("created").toString().replace("T"," ").replace(".000+0800","");
                }
            }
        }
        return res;
    }

    /*
     * @Author wangxiang10
     * @Description  请求jira获取bug详情信息
     * @Date 11:53 2022/5/12
     * @Param [bugsCollection]
     * @return java.lang.Object
     **/
    public Object getBugsInfo(List<String> bugsCollection){
        StringBuilder query = new StringBuilder("");
        StringBuilder jql = new StringBuilder("issuekey in ");
        bugsCollection.stream().forEach(i ->query.append(i).append(","));
        jql.append("(").append(query.substring(0,query.length()-1)).append(")");

        Request request = new Request.Builder().url("http://biu.ai.srv/api/jira").post(
                RequestBody.create(new ObModel().initMap()
                        .put("search", new ObModel().initMap()
                                .put("jql_str", jql.toString())
                                .put("startAt", 0)
                                .put("maxResults", 5000)
                                .put("fields", new ObModel().initArray()
                                                    .add("labels")
                                                    .add("component")
                                                    .add("createdDate")
                                                    .add("status")
                                                    .add("resolution").get())
                                .put("expand","changelog")
                                .get()).cString(), JSON)
        ).build();
        ObModel responseBody = null;
        try {
            Response response = okhttp.newCall(request).execute();
            responseBody = new ObModel(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseBody.get();
    }

    public static void main(String[] args) throws ParseException {
        JiraUtil jiraUtil = new JiraUtil();
        ObModel bugsForTask = jiraUtil.getBugsForTask();
        jiraUtil.getBugsMileStone(bugsForTask);

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date parse = sdf.parse("2022-5-6");
//        System.out.println(sdf.format(parse));
    }
}
