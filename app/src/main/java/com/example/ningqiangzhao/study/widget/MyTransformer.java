package com.example.ningqiangzhao.study.widget;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.ningqiangzhao.study.utils.ULog;

/**
 * @description: author:zhaoningqiang
 * @time 16/6/6/下午2:29
 */
public class MyTransformer implements ViewPager.PageTransformer {

    public static final float MAX_SIZE = 1.0f;
    public static final float MIN_SIZE = 0.8f;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void transformPage(View page, float position) {
       /* if (position < -1) {
            position = -1;
        } else if (position > 1) {
            position = 1;
        }
        float offset =0;
        if(position<0){
            offset=1 + position;
        }else{
            offset=1 - position;
        }
        float scaleSize = MIN_SIZE + offset * (MAX_SIZE - MIN_SIZE);
        page.setScaleY(scaleSize);
        page.setScaleX(scaleSize);*/

        ULog.d("test page = "+page + " position = "+position);

    }

}
