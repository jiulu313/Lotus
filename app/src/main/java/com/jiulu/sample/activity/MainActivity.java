package com.jiulu.sample.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.jiulu.lotus.core.Lotus;
import com.jiulu.lotus.http.HttpHelper;
import com.jiulu.sample.R;
import com.jiulu.sample.adapter.HeadersAdapter;
import com.jiulu.sample.bean.HeaderBean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<String> headers = new ArrayList<>();
    List<HeaderBean> headerBeanList = new ArrayList<>();


    ListView listView;
    HeadersAdapter headersAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.lv_header_list);
        initData();


        headersAdapter = new HeadersAdapter(this, headerBeanList, R.layout.header_list_item);
        listView.setAdapter(headersAdapter);
    }

    void initData() {
        headers.add("http://img2.imgtn.bdimg.com/it/u=547138142,3998729701&fm=214&gp=0.jpg");
        headers.add("http://h.hiphotos.baidu.com/zhidao/pic/item/eac4b74543a98226686548458882b9014a90eb28.jpg");
        headers.add("http://img.dongqiudi.com/uploads/avatar/2015/07/25/QM387nh7As_thumb_1437790672318.jpg");
        headers.add("http://img3.duitang.com/uploads/item/201511/13/20151113110644_PcSFj.thumb.224_0.jpeg");
        headers.add("http://www.feizl.com/upload2007/2014_01/140116182482507.jpg");
        headers.add("http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg");
        headers.add("http://up.qqya.com/allimg/201710-t/17-101802_69236.jpg");
        headers.add("http://img0.imgtn.bdimg.com/it/u=2777008330,1289798081&fm=214&gp=0.jpg");
        headers.add("http://e.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=450129f5d12a60595245e91e1d0418ad/a8773912b31bb051ec177614307adab44aede0df.jpg");
        headers.add("http://up.qqjia.com/z/20/tu23989_4.jpg");
        headers.add("http://b.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=2b4e25657a8b4710ce7af5c8f6feefcb/b90e7bec54e736d1bec1514c93504fc2d46269a0.jpg");
        headers.add("http://ztd00.photos.bdimg.com/ztd/w=350;q=70/sign=ad52b448f71f4134e037037b1524e4f7/908fa0ec08fa513d2ddb4575376d55fbb2fbd923.jpg");
        headers.add("http://tx.haiqq.com/uploads/allimg/150326/1P4523Q5-13.jpg");
        headers.add("http://www.qqzhi.com/uploadpic/2015-01-16/025611231.jpg");
        headers.add("http://www.wzfzl.cn/uploads/allimg/140219/1_140219103511_2.jpg");
        headers.add("http://www.qqzhi.com/uploadpic/2015-01-09/114439669.jpg");
        headers.add("http://www.ld12.com/upimg358/allimg/c141106/141525MMC420-3361I.jpg");
        headers.add("http://photo.jokeji.cn/uppic/14-09/08/2105254682073.jpg");
        headers.add("http://d8.yihaodianimg.com/N07/M06/9F/D8/CgQIz1SqSTOAIRmFAABEIJIrtXM34800.jpg");
        headers.add("http://tupian.enterdesk.com/2014/lxy/2014/12/09/72/5.jpg");
        headers.add("http://www.qqzhi.com/uploadpic/2014-09-04/222434499.jpg");
        headers.add("http://img4.imgtn.bdimg.com/it/u=3960472725,4292408921&fm=27&gp=0.jpg");
        headers.add("http://file.popoho.com/wzfzl/20160706/qiz4n3d4sbb1-140416162218.jpg");
        headers.add("http://e.hiphotos.baidu.com/zhidao/wh%3D600%2C800/sign=0150cbdc9b25bc312b08099e6eefa189/5ab5c9ea15ce36d3f99c9d2238f33a87e950b103.jpg");
        headers.add("http://p2.gexing.com/G1/M00/0E/A1/rBACE1MDNw3R5TqgAAAikB508Fo919_200x200_3.jpg?recache=20131108");


        for (int i = 0; i < headers.size(); i++) {
            HeaderBean bean = new HeaderBean();
            bean.name = "张三" + i;
            bean.header = headers.get(i);
            bean.address = "北京市长安大号55号" + i;
            headerBeanList.add(bean);
        }

    }

//    private void onShowImage() {
//        String url = "http://b.hiphotos.baidu.com/image/pic/item/e4dde71190ef76c606ac2feb9416fdfaae5167f0.jpg";
//        Lotus.with(this).load(url).into(ivShowImg);
//    }
//
//    private void onDownload() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                String url = "http://b.hiphotos.baidu.com/image/pic/item/e4dde71190ef76c606ac2feb9416fdfaae5167f0.jpg";
//
//                File file = HttpHelper.getInstance().syncRequest(url);
//                if(file != null && file.exists()){
//                    final Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            ivShowImg.setImageBitmap(bitmap);
//                        }
//                    });
//                }
//            }
//        }).start();
//    }
}
