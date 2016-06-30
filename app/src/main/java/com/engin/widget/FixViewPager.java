package com.engin.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.engin.utils.LogUtil;

/**
 * Created by zhaoningqiang on 16/6/26.
 */

public class FixViewPager extends ViewPager {
    private int mPosition;
    private float mPositionOffset;
    private int mPositionOffsetPixels;


    public FixViewPager(Context context) {
        super(context);
        init();
    }

    public FixViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
//        addOnPageChangeListener(new OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                mPosition = position;
//                mPositionOffset = positionOffset;
//                mPositionOffsetPixels = positionOffsetPixels;
//                LogUtil.d("VVVVV onPageScrolled position = "+position+" positionOffset = "+positionOffset+"  positionOffsetPixels = "+positionOffsetPixels);
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                LogUtil.d("VVVVV ---- onPageSelected position = "+position);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//        setPageTransformer(false, new PageTransformer() {
//            @Override
//            public void transformPage(View page, float position) {
//                LogUtil.d("VVVVV +++ setPageTransformer position = "+position);
//            }
//        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
