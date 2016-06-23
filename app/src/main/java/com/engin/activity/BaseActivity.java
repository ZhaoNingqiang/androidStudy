package com.engin.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.io.File;

/**
 * Created by zhaoningqiang on 16/6/18.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayout() != 0){
            setContentView(getLayout());
            initView();
            setListener();
        }
    }

    public abstract int getLayout();

    public abstract void initView();

    public abstract void setListener();


}
