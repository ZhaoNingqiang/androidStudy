package com.example.ningqiangzhao.study;

import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.example.ningqiangzhao.study.utils.ULog;
import com.example.ningqiangzhao.study.widget.MyImageView;
import com.example.ningqiangzhao.study.widget.MyTextView;
import com.example.ningqiangzhao.study.widget.MyTransformer;
import com.example.ningqiangzhao.study.widget.MyViewPager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    MyViewPager viewPager;
    MyImageView imageSwitcher;
    Matrix matrix ;
    MyTextView textview ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageSwitcher = (MyImageView) findViewById(R.id.is_left);
        matrix = new Matrix();
        imageSwitcher.setImageMatrix(matrix);
        textview = (MyTextView) findViewById(R.id.textview);


        viewPager = (MyViewPager) findViewById(R.id.viewpager);
//        viewPager.setPageMargin(100);
        viewPager.setPageTransformer(true, new MyTransformer());

        final ArrayList<Integer> data = new ArrayList<>();
        data.add(R.drawable.img1);
        data.add(R.drawable.img2);

//        data.add(R.drawable.img3);
//        data.add(R.drawable.img4);
//        data.add(R.drawable.img5);
//        data.add(R.drawable.img6);

//        imageSwitcher.setFactory(new ImageFactory());
//
//        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.enter));
//        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.exit));

//        data.add(R.drawable.a);
//        data.add(R.drawable.b);
//        data.add(R.drawable.c);
//        data.add(R.drawable.d);
//        data.add(R.drawable.e);
//        data.add(R.drawable.f);
//        data.add(R.drawable.g);
//        data.add(R.drawable.h);


        MyViewPagerAdaper adaper = new MyViewPagerAdaper(this,data);

        viewPager.setAdapter(adaper);


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                imageSwitcher.update(positionOffset);
                textview.update(positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
                ULog.d(" onPageSelected  p = "+position);
//                imageSwitcher.setImageResource(data.get(position%data.size()));
                View v = viewPager.getChildAt(position%data.size());
                ImageView imageView = (ImageView) v.findViewById(R.id.iv_item);
                imageSwitcher.setUpperLayerDrawable(imageView.getDrawable());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    class  ImageFactory implements ViewSwitcher.ViewFactory{

        @Override
        public View makeView() {
            ImageView iv = new ImageView(MainActivity.this);
            ImageSwitcher.LayoutParams lp = new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            iv.setLayoutParams(lp);
            return iv;
        }
    }

}
