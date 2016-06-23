package com.engin.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.engin.utils.LogUtil;

/**
 * Created by zhaoningqiang on 16/6/19.
 */

public class TouchContainer extends LinearLayout {
    private static final String TAG = TouchContainer.class.getSimpleName();
    public TouchContainer(Context context) {
        super(context);
    }

    public TouchContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TouchContainer(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.d(TAG,"onInterceptTouchEvent");
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogUtil.d(TAG,"onInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtil.d(TAG,"dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }
}
