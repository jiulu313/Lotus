package com.jiulu.sample;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.jiulu.lotus.core.Lotus;
import com.jiulu.lotus.http.FileStorageManager;
import com.jiulu.lotus.http.HttpHelper;
import com.jiulu.lotus.tool.Logger;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    Button btnDownload;
    ImageView ivShowImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnDownload = findViewById(R.id.btn_download);
        ivShowImg = findViewById(R.id.iv_imageview);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                onDownload();
                onShowImage();
            }
        });
    }

    private void onShowImage() {
        String url = "http://cyjctrip.qiniudn.com/1373740741/818B9AA0-6681-48E9-8B3C-147E30D5CA2C.jpg";
        Lotus.with(this).load(url).into(ivShowImg);
    }

    private void onDownload() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://cyjctrip.qiniudn.com/1373740741/818B9AA0-6681-48E9-8B3C-147E30D5CA2C.jpg";

                File file = HttpHelper.getInstance().syncRequest(url);
                if(file != null && file.exists()){
                    final Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ivShowImg.setImageBitmap(bitmap);
                        }
                    });
                }
            }
        }).start();
    }
}
