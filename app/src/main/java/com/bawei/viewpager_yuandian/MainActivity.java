package com.bawei.viewpager_yuandian;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> list = new ArrayList<String>();
    private ViewPager viewpager;
    private LinearLayout linearLayout;
    private List<ImageView> imageviewlist = new ArrayList<>();

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    int currentItem = viewpager.getCurrentItem();
                    viewpager.setCurrentItem(currentItem + 1);
                    handler.sendEmptyMessageDelayed(1, 2000);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        linearLayout = (LinearLayout) findViewById(R.id.linearlayout);

        //得到网址
        init();
        //创建圆点
        addshape();


        MyAdapter adapter = new MyAdapter(this, list, handler);
        viewpager.setAdapter(adapter);

        handler.sendEmptyMessageDelayed(1, 2000);
        viewpager.setCurrentItem(list.size() * 1000);

        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < list.size(); i++) {
                    if (i == position % list.size()) {
                        imageviewlist.get(i).setImageResource(R.drawable.shape_true);
                    } else {
                        imageviewlist.get(i).setImageResource(R.drawable.shape_false);
                    }

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    //创建圆点
    private void addshape() {
        for (int i = 0; i < list.size(); i++) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, 0, 8, 0);
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(layoutParams);
            imageviewlist.add(imageView);
            if (i == 0) {
                imageView.setImageResource(R.drawable.shape_true);
            } else {
                imageView.setImageResource(R.drawable.shape_false);

            }
            linearLayout.addView(imageView);
        }

    }

    private void init() {
        //添加图片
        list.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2732071056,1940592635&fm=23&gp=0.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493804221104&di=6a33373924e330f8a65261887b56bdbe&imgtype=0&src=http%3A%2F%2Fpic15.nipic.com%2F20110806%2F7316020_155844875000_2.jpg");
        list.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=376388072,2830330809&fm=23&gp=0.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493804268120&di=ae648bb8dd82fae8e9682aed734e8fdc&imgtype=0&src=http%3A%2F%2Fpic4.nipic.com%2F20091102%2F2406060_201239011685_2.jpg");
    }
}
