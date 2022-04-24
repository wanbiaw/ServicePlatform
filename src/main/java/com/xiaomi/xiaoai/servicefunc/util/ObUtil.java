package com.xiaomi.xiaoai.servicefunc.util;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import com.google.common.collect.Lists;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ObUtil {
    public ObUtil() {
    }

    public static boolean check(Object object) {
        try {
            if (null == object) {
                return true;
            }

            if (object instanceof Map) {
                return ((Map)object).size() == 0;
            }

            Field[] var1 = object.getClass().getDeclaredFields();
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                Field f = var1[var3];
                f.setAccessible(true);
                if (f.get(object) != null && isNotN(f.get(object) == null ? "" : f.get(object).toString())) {
                    return false;
                }
            }
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return true;
    }

    public static boolean isExistNull(Object object) {
        try {
            if (null == object) {
                return true;
            }

            if (object instanceof Map) {
                return ((Map)object).size() == 0;
            }

            Field[] var1 = object.getClass().getDeclaredFields();
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                Field f = var1[var3];
                f.setAccessible(true);
                if (f.get(object) == null || isN(f.get(object) == null ? "" : f.get(object).toString())) {
                    return true;
                }
            }
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return false;
    }

    public static boolean checkN(Object object) {
        return !check(object);
    }

    public static boolean check(Object object, String str) {
        if (!isN(str) && null != object) {
            try {
                Field[] var2 = object.getClass().getDeclaredFields();
                int var3 = var2.length;

                for(int var4 = 0; var4 < var3; ++var4) {
                    Field f = var2[var4];
                    f.setAccessible(true);
                    if (str.equals(f.getName()) && checkN(f.get(object))) {
                        return false;
                    }
                }
            } catch (Exception var6) {
                var6.printStackTrace();
            }

            return true;
        } else {
            return check(object);
        }
    }

    public static boolean checkN(Object object, String str) {
        return !check(object, str);
    }

    public static boolean isN(String str) {
        return "null".equalsIgnoreCase(str) || str == null || str.trim().length() == 0;
    }

    public static boolean isNotN(String str) {
        return !isN(str);
    }

    public static boolean isEmpty(Collection coll) {
        return coll == null || coll.isEmpty();
    }

    public static boolean isNotEmpty(Collection coll) {
        return !isEmpty(coll);
    }

    public static Integer getInteger(Integer i) {
        return i == null ? 0 : i;
    }

    public static Boolean equals(Integer i1, Integer i2) {
        return getInteger(i1).equals(getInteger(i2));
    }

    public static Boolean notEqual(Integer i1, Integer i2) {
        return !getInteger(i1).equals(getInteger(i2));
    }

    public static <T> List<T> getPagesList(List<T> list, Integer pageNum, Integer pageSize) {
        if (pageNum != null && pageNum > 0 && pageSize != null && pageSize > 0 && list != null && list.size() != 0 && (pageNum - 1) * pageSize < list.size()) {
            Integer count = list.size();
            Integer pageCount = 0;
            if (count % pageSize == 0) {
                pageCount = count / pageSize;
            } else {
                pageCount = count / pageSize + 1;
            }

            if (pageNum > pageCount) {
                pageNum = pageCount;
            }

            int fromIndex;
            int toIndex;
            if (!pageNum.equals(pageCount)) {
                fromIndex = (pageNum - 1) * pageSize;
                toIndex = fromIndex + pageSize;
            } else {
                fromIndex = (pageNum - 1) * pageSize;
                toIndex = count;
            }

            return list.subList(fromIndex, toIndex);
        } else {
            return Lists.newArrayList();
        }
    }
}
