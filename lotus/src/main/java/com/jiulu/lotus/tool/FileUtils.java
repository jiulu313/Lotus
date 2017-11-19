package com.jiulu.lotus.tool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * 操作文件相关
 */

public class FileUtils {
    private FileUtils() {
    }

    //把流写入到文件中
    public static boolean writeFile(InputStream inputStream, File file) {
        if (inputStream == null || !file.exists()) {
            return false;
        }

        //写入文件
        byte[] buffer = new byte[1024 * 500];
        int len;
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(file);
            while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
                fos.write(buffer, 0, len);
                fos.flush();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}
