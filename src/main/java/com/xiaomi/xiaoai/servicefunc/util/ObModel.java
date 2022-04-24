package com.xiaomi.xiaoai.servicefunc.util;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ObModel {
    private Object object;
    private static String SPLIT = "/";
    private static Gson gson = GSONConfiguration.getGson();
    private static Gson transGson;

    public ObModel(Object object) {
        this.object = object;
    }

    public ObModel() {
    }

    public ObModel initMap() {
        this.object = gson.fromJson("{}", Object.class);
        return this;
    }

    public ObModel initArray() {
        this.object = gson.fromJson("[]", Object.class);
        return this;
    }

    public void setSplit(String str) {
        SPLIT = str;
    }

    public String getSplit() {
        return SPLIT;
    }

    public ObModel(Object object, Boolean f) {
        if (f) {
            this.object = gson.fromJson(transGson.toJson(object), Object.class);
        } else {
            this.object = object;
        }

    }

    public ObModel(String s) {
        this.object = gson.fromJson(s, Object.class);
    }

    public Boolean isN() {
        return this.object == null || ObUtil.isN(String.valueOf(this.object));
    }

    public List<ObModel> getArray(String k) {
        if (this.object instanceof Map) {
            Object orDefault = ((Map)this.object).getOrDefault(k, Lists.newArrayList());
            if (orDefault instanceof List) {
                return (List)((List)orDefault).stream().map(ObModel::new).collect(Collectors.toList());
            }
        }

        return Lists.newArrayList();
    }

    public ObModel get(String k) {
        return this.object instanceof Map ? new ObModel(((Map)this.object).getOrDefault(k, "")) : new ObModel();
    }

    public ObModel getM(String k) {
        return this.getM(k, SPLIT);
    }

    public ObModel getM(String k, String str) {
        return this.getM(new ObModel(this.object), k, str);
    }

    public ObModel getM(ObModel res, String k, String str) {
        if (ObUtil.isN(k)) {
            return res;
        } else {
            String[] split = k.split(str);
            String[] var5 = split;
            int var6 = split.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                String s = var5[var7];
                if (res.get() instanceof Map) {
                    res = new ObModel(((Map)res.get()).getOrDefault(s, ""));
                } else {
                    if (!(res.get() instanceof List)) {
                        break;
                    }

                    res = new ObModel(this.getObject(res.get(), s));
                }
            }

            return res;
        }
    }

    public ObModel getMS(ObModel res, String k, String str) {
        if (ObUtil.isN(k)) {
            return res;
        } else {
            String[] split = k.split(str);

            for(int i = 0; i < split.length; ++i) {
                String s = split[i];
                if (i == split.length - 1) {
                    res = new ObModel(this.getObjects(res.get(), s));
                    break;
                }

                if (res.get() instanceof Map) {
                    res = new ObModel(((Map)res.get()).getOrDefault(s, ""));
                } else {
                    if (!(res.get() instanceof List)) {
                        break;
                    }

                    res = new ObModel(this.getObject(res.get(), s));
                }
            }

            return res;
        }
    }

    public Boolean equal(ObModel o) {
        return o == null ? false : o.cString().equals(this.cString());
    }

    public Boolean equal(ObModel o1, ObModel o2) {
        if (o1 == null && o2 == null) {
            return true;
        } else {
            return o1 != null && o2 != null ? o1.cString().equals(o2.cString()) : false;
        }
    }

    public ObModel removeM(String k) {
        return this.removeM(k, SPLIT);
    }

    public ObModel removeM(String k, String str) {
        ObModel res = new ObModel(this.object);
        if (ObUtil.isN(k)) {
            return res;
        } else {
            List<String> split = Arrays.asList(k.split(str));
            String l = (String)split.get(split.size() - 1);
            split = ObUtil.getPagesList(split, 1, split.size() - 1);
            if (split.size() >= 1) {
                this.getM(String.join(SPLIT, split)).remove(l);
            } else {
                this.remove(l);
            }

            return res;
        }
    }

    public ObModel removeMS(ObModel ms, String k, String str) {
        ObModel res = new ObModel(this.object);
        if (ObUtil.isN(k)) {
            return res;
        } else {
            List<String> split = Arrays.asList(k.split(str));
            String l = (String)split.get(split.size() - 1);
            split = ObUtil.getPagesList(split, 1, split.size() - 1);
            if (split.size() >= 1) {
                ObModel m = this.getM(String.join(SPLIT, split));
                if (ms.isArray()) {
                    m.toArray().forEach((p) -> {
                        (new ObModel(p)).remove(l);
                    });
                } else {
                    m.remove(l);
                }
            } else {
                this.remove(l);
            }

            return res;
        }
    }

    private Object getObject(Object o, String k) {
        if (o instanceof Map) {
            return ((Map)o).getOrDefault(k, "");
        } else {
            if (o instanceof List) {
                if (((List)o).size() <= 0) {
                    o = "";
                } else {
                    o = this.getObject(((List)o).get(0), k);
                }
            }

            return o;
        }
    }

    private Object getObjects(Object o, String k) {
        if (o instanceof Map) {
            return ((Map)o).getOrDefault(k, "");
        } else if (!(o instanceof List)) {
            return o;
        } else {
            List<Object> res = Lists.newArrayList();
            Iterator var4 = ((List)o).iterator();

            while(var4.hasNext()) {
                Object o1 = var4.next();
                res.add(this.getObject(o1, k));
            }

            return res;
        }
    }

    public ObModel remove(String k) {
        if (this.object instanceof Map) {
            ((Map)this.object).remove(k);
        }

        return this;
    }

    public ObModel put(String k, Object v) {
        if (this.object instanceof Map) {
            ((Map)this.object).put(k, v);
        }

        return this;
    }

    public ObModel add(Object v) {
        if (this.object instanceof List) {
            ((List)this.object).add(v);
        }

        return this;
    }

    public Object get() {
        return this.object;
    }

    public <T> T get(Class<T> tClass) {
        return gson.fromJson(this.cString(), tClass);
    }

    public <T> List<T> getArray(TypeToken token) {
        return (List)gson.fromJson(this.cString(), token.getType());
    }

    public Map toMap() {
        return this.object instanceof Map ? (Map)this.object : (Map)gson.fromJson(transGson.toJson(this.object), Map.class);
    }

    public String vString() {
        if (this.object == null) {
            return "";
        } else {
            return this.object instanceof String ? (String)this.object : String.valueOf(this.object);
        }
    }

    public String cString() {
        return this.cString(false);
    }

    public String cString(Boolean trans) {
        if (trans == null) {
            return "";
        } else if (this.object instanceof String) {
            return (String)this.object;
        } else {
            return !trans ? transGson.toJson(this.object) : gson.toJson(this.object);
        }
    }

    public Integer cInteger() {
        return this.object instanceof Double ? ((Double)this.object).intValue() : Integer.parseInt(String.valueOf(this.object));
    }

    public List toArray() {
        return this.toArray(Object.class);
    }

    public <T> List<T> toArray(Class<T> tClass) {
        if (this.object instanceof List) {
            if (tClass == Object.class) {
                return (List)this.object;
            } else {
                List<T> res = new ArrayList(16);
                ((List)this.object).forEach((p) -> {
                    res.add(gson.fromJson((new ObModel(p)).cString(), tClass));
                });
                return res;
            }
        } else {
            return Lists.newArrayList();
        }
    }

    public ObModel getOb(String str) {
        return this.getOb(this, str);
    }

    public ObModel getOb(ObModel o, String str) {
        String[] split = str.split(SPLIT);
        Object ob = o.get();
        boolean b = ob instanceof String || ob instanceof Integer || ob instanceof Long || ob instanceof Double || ob instanceof Boolean || ob == null || split.length <= 0;
        if (b) {
            return o;
        } else {
            Iterator var9;
            Object k;
            if (ob instanceof Map) {
                if (((Map)ob).containsKey(split[0])) {
                    ObModel m = this.getMS(new ObModel(ob), str, SPLIT);
                    if (!this.equal(o, m) && !"".equals(String.valueOf(m.get()))) {
                        this.object = m.get();
                    }

                    Iterator var7 = ((Map)ob).keySet().iterator();

                    while(var7.hasNext()) {
                        k = var7.next();
                        this.getOb(new ObModel(((Map)ob).get(k)), str);
                    }
                } else {
                    var9 = ((Map)ob).keySet().iterator();

                    while(var9.hasNext()) {
                        k = var9.next();
                        this.getOb(new ObModel(((Map)ob).get(k)), str);
                    }
                }
            } else if (ob instanceof List) {
                var9 = ((ArrayList)ob).iterator();

                while(var9.hasNext()) {
                    k = var9.next();
                    this.getOb(new ObModel(k), str);
                }
            }

            return this;
        }
    }

    public void removeObList(String str) {
        this.removeObList(this, str);
    }

    public void removeObList(ObModel o, String str) {
        String[] split = str.split(SPLIT);
        Object ob = o.get();
        Iterator var8;
        Object k;
        if (ob instanceof Map) {
            if (((Map)ob).containsKey(split[0])) {
                ObModel m = this.getMS(new ObModel(ob), str, SPLIT);
                if (!this.equal(o, m) && !"".equals(String.valueOf(m.get()))) {
                    if (m.isArray()) {
                        o.removeMS(m, str, SPLIT);
                    } else {
                        o.removeM(str);
                    }
                }

                Iterator var6 = ((Map)ob).keySet().iterator();

                while(var6.hasNext()) {
                    k = var6.next();
                    this.removeObList(new ObModel(((Map)ob).get(k)), str);
                }
            } else {
                var8 = ((Map)ob).keySet().iterator();

                while(var8.hasNext()) {
                    k = var8.next();
                    this.removeObList(new ObModel(((Map)ob).get(k)), str);
                }
            }
        } else if (ob instanceof List) {
            var8 = ((ArrayList)ob).iterator();

            while(var8.hasNext()) {
                k = var8.next();
                this.removeObList(new ObModel(k), str);
            }
        }

    }

    public Boolean isMap() {
        return this.object instanceof Map;
    }

    public Boolean isArray() {
        return this.object instanceof List;
    }

    public List<ObModel> getObList(String str) {
        List<ObModel> res = Lists.newArrayList();
        this.getObList(this, str, res);
        return res;
    }

    public void getObList(ObModel o, String str, List<ObModel> list) {
        String[] split = str.split(SPLIT);
        Object ob = o.get();
        Iterator var9;
        Object k;
        if (ob instanceof Map) {
            if (((Map)ob).containsKey(split[0])) {
                ObModel m = this.getMS(new ObModel(ob), str, SPLIT);
                if (!this.equal(o, m) && !"".equals(String.valueOf(m.get()))) {
                    if (m.isArray()) {
                        m.toArray().forEach((p) -> {
                            list.add(new ObModel(p));
                        });
                    } else {
                        list.add(m);
                    }
                }

                Iterator var7 = ((Map)ob).keySet().iterator();

                while(var7.hasNext()) {
                    k = var7.next();
                    this.getObList(new ObModel(((Map)ob).get(k)), str, list);
                }
            } else {
                var9 = ((Map)ob).keySet().iterator();

                while(var9.hasNext()) {
                    k = var9.next();
                    this.getObList(new ObModel(((Map)ob).get(k)), str, list);
                }
            }
        } else if (ob instanceof List) {
            var9 = ((ArrayList)ob).iterator();

            while(var9.hasNext()) {
                k = var9.next();
                this.getObList(new ObModel(k), str, list);
            }
        }

    }

    public Boolean contains(String str) {
        if (ObUtil.isN(str)) {
            return false;
        } else {
            return this.object instanceof Map ? ((Map)this.object).containsKey(str) : false;
        }
    }

    public Boolean isContain(String str) {
        return this.isContain(this, str);
    }

    public Boolean isContain(ObModel o, String str) {
        Object ob = o.get();
        boolean b = ob instanceof String || ob instanceof Integer || ob instanceof Long || ob instanceof Double || ob instanceof Boolean || ob == null;
        if (b) {
            return false;
        } else {
            Iterator var5;
            Object k;
            if (ob instanceof Map) {
                if (((Map)ob).containsKey(str)) {
                    return true;
                }

                var5 = ((Map)ob).keySet().iterator();

                while(var5.hasNext()) {
                    k = var5.next();
                    if (this.isContain(new ObModel(((Map)ob).get(k)), str)) {
                        return true;
                    }
                }
            } else if (ob instanceof List) {
                var5 = ((ArrayList)ob).iterator();

                while(var5.hasNext()) {
                    k = var5.next();
                    if (this.isContain(new ObModel(k), str)) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    public Boolean isContainM(String str) {
        return this.isContainM(this, str);
    }

    public Boolean isContainM(ObModel o, String str) {
        String[] split = str.split(SPLIT);
        Object ob = o.get();
        Iterator var8;
        Object k;
        if (ob instanceof Map) {
            if (((Map)ob).containsKey(split[0])) {
                ObModel m = this.getM(new ObModel(ob), str, SPLIT);
                if (!this.equal(o, m) && !"".equals(String.valueOf(m.get()))) {
                    return true;
                }

                Iterator var6 = ((Map)ob).keySet().iterator();

                while(var6.hasNext()) {
                    k = var6.next();
                    if (this.isContainM(new ObModel(((Map)ob).get(k)), str)) {
                        return true;
                    }
                }
            } else {
                var8 = ((Map)ob).keySet().iterator();

                while(var8.hasNext()) {
                    k = var8.next();
                    if (this.isContainM(new ObModel(((Map)ob).get(k)), str)) {
                        return true;
                    }
                }
            }
        } else if (ob instanceof List) {
            var8 = ((ArrayList)ob).iterator();

            while(var8.hasNext()) {
                k = var8.next();
                if (this.isContainM(new ObModel(k), str)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static List<Object> getObList(List<ObModel> list) {
        return (List)list.stream().map(ObModel::get).collect(Collectors.toList());
    }

    public static <T> List<T> getObList(List<ObModel> list, Class<T> tClass) {
        return (List)list.stream().map((p) -> {
            return gson.fromJson(p.cString(), tClass);
        }).collect(Collectors.toList());
    }

    public static List<Map<String, Object>> getObList(List<ObModel> list, String key) {
        return (List)list.stream().map((p) -> {
            Map<String, Object> map = new LinkedTreeMap();
            map.put(key, p.get());
            return map;
        }).collect(Collectors.toList());
    }

    public List<ObModel> getObMlist() {
        List<ObModel> res = Lists.newArrayList();
        if (this.object instanceof List) {
            ((List)this.object).forEach((p) -> {
                res.add(new ObModel(p));
            });
        }

        return res;
    }

    public ObModel addKey(String key) {
        Map<String, Object> map = new LinkedTreeMap();
        map.put(key, this.object);
        return new ObModel(map);
    }

    public ObModel getObject() {
        if (this.object instanceof List) {
            if (((List)this.object).size() <= 0) {
                this.object = "";
            } else {
                this.object = ((List)this.object).get(0);
                this.getObject();
            }
        }

        return new ObModel(this.object);
    }

    public ObModel addList() {
        ObModel res = (new ObModel()).initArray();
        return this.object == null ? res : res.add(this.object);
    }

    public ObModel listRemove(int index) {
        if (this.object instanceof List) {
            ((List)this.object).remove(index);
        }

        return this;
    }

    public Integer getSize() {
        if (this.object instanceof List) {
            return ((List)this.object).size();
        } else {
            return this.object instanceof Map ? ((Map)this.object).size() : 0;
        }
    }

    static {
        transGson = gson.newBuilder().disableHtmlEscaping().create();
    }
}

