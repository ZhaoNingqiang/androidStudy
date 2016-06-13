package com.example.ningqiangzhao.study;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * @description:
 * author:zhaoningqiang
 * @time 16/6/6/下午2:49
 */
public class MyViewPagerAdaper extends PagerAdapter {

    Context context;
    ArrayList<Integer> data;

    public MyViewPagerAdaper(Context context, ArrayList<Integer> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View v = View.inflate(context, R.layout.page_image_item, null);

        ImageView imageView = (ImageView) v.findViewById(R.id.iv_item);
        imageView.setImageResource(data.get(position%data.size()));
            container.addView(v);

        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public float getPageWidth(int position) {
//        if (position == data.size() - 1){
//            return 1f;
//        }
//        return super.getPageWidth(position);
        return  1/3f;
    }

    @Override
    public void startUpdate(ViewGroup container) {
        super.startUpdate(container);
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        super.finishUpdate(container);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {

        super.setPrimaryItem(container, position, object);
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

}
