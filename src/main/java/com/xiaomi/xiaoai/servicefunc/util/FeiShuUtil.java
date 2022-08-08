package com.xiaomi.xiaoai.servicefunc.util;

import com.xiaomi.xms.plans.client.org.json.simple.JSONObject;
import com.xiaomi.xms.plans.client.org.json.simple.JSONValue;
import okhttp3.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class FeiShuUtil {

    private static final String TOKEN_URL = "https://open.f.mioffice.cn/open-apis/auth/v3/tenant_access_token/internal/";
    private static final String SEND_URL = "https://open.f.mioffice.cn/open-apis/message/v4/send/";
    private static final String SEND_POST_URL = "https://open.f.mioffice.cn/open-apis/image/v4/put/";
    private static final String SEND_POST_CARD_URL = "https://open.f.mioffice.cn/open-apis/ephemeral/v1/send";
    private static final String SEND_POST_URL_V1 = "https://open.f.mioffice.cn/open-apis/im/v1/messages?receive_id_type={receive_id_type}";
    private static String READ_SINGLE_EXCEL_URL = "https://open.f.mioffice.cn/open-apis/sheets/v2/spreadsheets/:spreadsheetToken/values/:range";
    private String appId = "cli_a12f555fd8f8d063";
    private String appSecret = "fzIeh0v9wvUjFkjfyyHsTho7Z5UPRJwh";
    private final OkHttpClient okhttp = new OkHttpClient.Builder()
            .readTimeout(100, TimeUnit.SECONDS)
            .connectTimeout(60,TimeUnit.SECONDS)
            .connectionPool(new ConnectionPool(32,5, TimeUnit.MINUTES))
            .build();
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public FeiShuUtil() {
    }

    public FeiShuUtil(String appId, String appSecret) {
        this.appId = appId;
        this.appSecret = appSecret;
    }

    public void sendCardMsg(String userId, String chatId, String title, String content, String color) {
        this.sendCardMsg(userId, chatId, title, content, (String)null, (String)null, color);
    }

    public void sendCardMsg(String userId, String chatId, String title, String content, String action, String uri, String color) {
        if (content != null) {
            ObModel markdown = (new ObModel()).initMap().put("tag", "markdown").put("content", content);
            ObModel elements = (new ObModel()).initArray().add(markdown.get());
            if (ObUtil.isNotN(action) && ObUtil.isNotN(uri)) {
                elements.add(this.getActions(action, uri));
            }

            ObModel card = (new ObModel()).initMap().put("tag", "plain_text").put("content", title).addKey("title").put("template", color).addKey("header").put("elements", elements.get());
            ObModel model = (new ObModel()).initMap().put("msg_type", "interactive").put("card", card.get());
            if (ObUtil.isNotN(userId)) {
                model.put("user_id", userId);
            }

            if (ObUtil.isNotN(chatId)) {
                model.put("chat_id", chatId);
            }

            System.out.println(model.get());
            Response execute;
            RequestBody requestBody = RequestBody.create(JSON, model.cString());
            Request authorization = new Request.Builder().url("https://open.f.mioffice.cn/open-apis/message/v4/send/").addHeader("Authorization", this.getHeaderByWeb()).post(requestBody).build();
            try {
                execute = okhttp.newCall(authorization).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Object getMarkDown(String content) {
        return (new ObModel()).initMap().put("tag", "markdown").put("content", content).get();
    }

    public Object getActions(String content, String url) {
        ObModel action = (new ObModel()).initMap().put("tag", "plain_text").put("content", content).addKey("text").put("tag", "button").put("url", url);
        return (new ObModel()).initArray().add(action.get()).addKey("actions").put("tag", "action").get();
    }

    public Object getHr() {
        return (new ObModel()).initMap().put("tag", "hr").get();
    }

    public String getHeaderByWeb() {
//        ObModel parameters = (new ObModel()).initMap().put("app_id", this.appId).put("app_secret", this.appSecret);
//        System.out.println(parameters.get());
        Response res = null;
        JSONObject parse = null;
        FormBody build = new FormBody.Builder().add("app_id", this.appId).add("app_secret", this.appSecret).build();
        Request post = new Request.Builder().url("https://open.f.mioffice.cn/open-apis/auth/v3/tenant_access_token/internal/").post(build).build();
        try {
            res = okhttp.newCall(post).execute();
            parse = (JSONObject) JSONValue.parse(res.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(parse.get("msg"));
        System.out.println(parse.get("tenant_access_token"));
        return "ok".equals(parse.get("msg").toString()) ? "Bearer " + parse.get("tenant_access_token").toString() : "";
    }

    public String getAppId() {
        return this.appId;
    }

    public String getAppSecret() {
        return this.appSecret;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public List getSingleExcel(String spreadsheetToken, String range){
        String url = READ_SINGLE_EXCEL_URL.replace(":spreadsheetToken",spreadsheetToken).replace(":range",range);
        String access_token = getHeaderByWeb();
        HttpUrl.Builder builder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
        builder.addQueryParameter("valueRenderOption","ToString");
        builder.addQueryParameter("dateTimeRenderOption", "FormattedString");
        Request request = new Request.Builder().url(builder.build()).addHeader("Authorization", access_token).build();
        ObModel responseBody = null;
        try {
            Response response = okhttp.newCall(request).execute();
            responseBody = new ObModel(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseBody.getM("data/valueRange/values").toArray();
    }


    public static void main(String[] args) throws IOException {
        FeiShuUtil feiShuUtil = new FeiShuUtil();
//        String tenant_access_token = feiShuUtil.getHeaderByWeb();
////        feiShuUtil.sendCardMsg(null,"oc_58344be92df7dcf2146ecb62eaa8fce0","test2monitor","my first robot","前往测试平台","http://localhost:8090/test/plan/prod/1","turquoise");
//        feiShuUtil.sendCardMsg("wangxiang10",null,"test2monitor","my first robot","前往测试平台","http://localhost:8090/test/plan/prod/1","turquoise");
        List list = feiShuUtil.getSingleExcel("shtk49wce5SrqmRC24jee56v8Nx", "Bd0IqZ");
        List<String> list1 = new ObModel(list.get(1)).toArray();
        System.out.println(list1.get(6));
    }
}
