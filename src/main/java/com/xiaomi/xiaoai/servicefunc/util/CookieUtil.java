package com.xiaomi.xiaoai.servicefunc.util;

import okhttp3.*;
import okhttp3.OkHttpClient.Builder;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.util.*;

public class CookieUtil {
    private static final String APP_ID = "84f403c3";
    private static final String APP_KEY = "b057b270ec074b71";
    private static final String USERNAME = "ai-fb-robot";
    private static final String PASSWORD = "Xm3q654es374";
    private static final String CAS_AUTO_LOGIN_URL = "https://cas.mioffice.cn/v2/api/auto/login";
    public static Map<String, List<Cookie>> cookieStore = new HashMap(16);
    private static final String CAS_HOST = "cas.mioffice.cn";
    public static OkHttpClient client = (new Builder()).cookieJar(new CookieJar() {
        public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
            List<Cookie> currentCookies = (List)CookieUtil.cookieStore.get(httpUrl.host());
            if (currentCookies == null) {
                currentCookies = new ArrayList();
            }

            ((List)currentCookies).addAll(list);
            CookieUtil.cookieStore.put(httpUrl.host(), currentCookies);
        }

        public List<Cookie> loadForRequest(HttpUrl httpUrl) {
            List<Cookie> cookies = (List)CookieUtil.cookieStore.get(httpUrl.host());
            System.out.println("Url: " + httpUrl.toString() + " | Cookies: " + cookies);
            return (List)(cookies != null ? cookies : new ArrayList());
        }
    }).build();

    public CookieUtil() {
    }

    public static void setCookie(String url) {
        try {
            Request request = (new Request.Builder()).url(url).build();
            Response response = client.newCall(request).execute();
            HttpUrl responseUrl = response.request().url();
            if (responseUrl.host().equals("cas.mioffice.cn")) {
                cookieStore.remove(request.url().host());
                HttpUrl lastRequestUrl = loginAndStoreCookies(responseUrl, client);
                if (lastRequestUrl.host().equals("cas.mioffice.cn")) {
                    loginAndStoreCookies(lastRequestUrl, client);
                }
            }
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    private static HttpUrl loginAndStoreCookies(HttpUrl loginUrl, OkHttpClient client) throws Exception {
        String service = loginUrl.queryParameter("service");
        ObModel bodyObj = (new ObModel()).initMap().put("username", "ai-fb-robot").put("password", EncryptUtil.encryptPassword("Xm3q654es374", "b057b270ec074b71")).put("service", service);
        String sign = DigestUtils.md5Hex("84f403c3" + bodyObj.cString(true) + "b057b270ec074b71").toUpperCase();
        ObModel headerObj = (new ObModel()).initMap().put("appid", "84f403c3").put("sign", sign);
        String data = (new ObModel()).initMap().put("header", headerObj.get()).put("body", bodyObj.get()).cString(true);
        data = Base64.getEncoder().encodeToString(data.getBytes());
        RequestBody requestBody = (new okhttp3.FormBody.Builder()).add("data", data).build();
        Request request = (new Request.Builder()).url("https://cas.mioffice.cn/v2/api/auto/login").post(requestBody).build();
        String redirectTo = (new ObModel(client.newCall(request).execute().body().string())).getM("data/redirect_to").vString();
        return client.newCall((new Request.Builder()).url(redirectTo).build()).execute().request().url();
    }

    public static void send() throws IOException {
        Request request = (new Request.Builder()).addHeader("x-request-without-cas", "true").post((new okhttp3.FormBody.Builder()).build()).url("http://speechlabel-staging.ai.srv/api/labelStatistics").build();
        Response execute = client.newCall(request).execute();
        System.out.println(execute.body().string());
    }
}

