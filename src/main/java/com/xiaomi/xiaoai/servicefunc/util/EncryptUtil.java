package com.xiaomi.xiaoai.servicefunc.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class EncryptUtil {
    public EncryptUtil() {
    }

    public static String md5(String s) {
        char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        try {
            byte[] btInput = s.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            byte[] var8 = md;
            int var9 = md.length;

            for(int var10 = 0; var10 < var9; ++var10) {
                byte byte0 = var8[var10];
                str[k++] = hexDigits[byte0 >>> 4 & 15];
                str[k++] = hexDigits[byte0 & 15];
            }

            return (new String(str)).toLowerCase();
        } catch (Exception var12) {
            var12.printStackTrace();
            return "";
        }
    }

    public static String sha1Encrypt(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("sha1");
            byte[] digest = md.digest(str.getBytes());
            char[] chars = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            StringBuffer sb = new StringBuffer();
            byte[] var5 = digest;
            int var6 = digest.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                byte b = var5[var7];
                sb.append(chars[b >> 4 & 15]);
                sb.append(chars[b & 15]);
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException var9) {
            var9.printStackTrace();
            return "";
        }
    }

    public static String encryptPassword(String text, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
        cipher.init(1, secretKeySpec);
        byte[] encrypted = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encrypted);
    }
}

