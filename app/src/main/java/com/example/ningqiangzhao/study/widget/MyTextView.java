package com.example.ningqiangzhao.study.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @description: author:zhaoningqiang
 * @time 16/6/7/下午4:56
 */
public class MyTextView extends TextView {
    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void setAlpha(float alpha) {
        super.setAlpha(alpha);
    }
    String current = "";
    public void setMyText(String text) {
        current = text;
        setText(text);
    }

    @Override
    public void setTranslationX(float translationX) {
        super.setTranslationX(translationX);
    }
    int Max_Translation = 200;

    public void update(float kk){
        kk = 1 - kk;
            setText(kk*100000+"用户关联账号信息用户关联账号信息用户关联账号信息用户关联账号信息用户关联账号信息");
        setAlpha(kk);
        setTranslationX(kk*Max_Translation);
    }
}
