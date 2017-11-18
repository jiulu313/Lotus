package com.jiulu.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jiulu.lotus.http.FileStorageManager;
import com.jiulu.lotus.tool.Logger;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        File file = FileStorageManager.getInstance().getFileByUrl("www.baidu.com");
        Logger.e("main",file.getAbsolutePath());

    }
}
