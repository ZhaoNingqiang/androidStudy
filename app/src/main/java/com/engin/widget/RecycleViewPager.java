package com.engin.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;

import com.engin.utils.LogUtil;

/**
 * @description: author:zhaoningqiang
 * @time 16/6/28/上午11:32
 */
public class RecycleViewPager extends RecyclerView {
    LinearLayoutManager lm;
    public RecycleViewPager(Context context) {
        super(context);
//        RecyclerViewPager


    }

    public RecycleViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        lm = (LinearLayoutManager) getLayoutManager();
        setOnDragListener(new OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                LogUtil.d("onDrag v = " + v + " event y = " + event.getY());
                return false;
            }
        });

        setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LogUtil.d("onScrolled dx = " + dx + " dy = " + dy);
            }
        });

//        lm.findFirstCompletelyVisibleItemPosition()

    }

    public RecycleViewPager(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return super.onTouchEvent(e);
    }
}
