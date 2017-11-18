package com.jiulu.lotus.tool;

import android.text.TextUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.DeflaterInputStream;

/**
 * Created by zhanghongjun on 2017/11/18.
 */

public class Md5Utils {

    public static String toMd5(String str) {
       if(str == null || "".equals(str)){
           return null;
       }

       StringBuilder sb = new StringBuilder();
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            digest.update(str.getBytes());
            byte[] cipher = digest.digest();
            for (byte b : cipher){
                String hexStr = Integer.toHexString(b & 0xff);
                sb.append(hexStr.length() == 1 ? "0" + hexStr : hexStr);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
}
