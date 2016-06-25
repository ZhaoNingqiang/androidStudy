package com.engin.activity;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.engin.R;
import com.engin.cache.ImageCacheLoader;
import com.engin.utils.LogUtil;
import com.engin.widget.FloatCircleImageView;

import java.util.ArrayList;

/**
 * Created by zhaoningqiang on 16/6/25.
 */

public class FloatCircleActivity extends BaseActivity {
    FloatCircleImageView fiv;
    ViewPager vp;
    ArrayList<String> mUrls = new ArrayList<String>();
    @Override
    public int getLayout() {
        return R.layout.acitivty_float_circle_view;
    }

    @Override
    public void initView() {
        fiv = (FloatCircleImageView) findViewById(R.id.fiv);
        vp = (ViewPager) findViewById(R.id.vp);


        mUrls = new ArrayList<String>();
        mUrls.add("http://img4.imgtn.bdimg.com/it/u=172112303,3232882607&fm=21&gp=0.jpg");
        mUrls.add("http://img3.imgtn.bdimg.com/it/u=1876649012,2419765871&fm=21&gp=0.jpg");
        mUrls.add("http://img4.imgtn.bdimg.com/it/u=3053650863,2902423685&fm=21&gp=0.jpg");
        mUrls.add("http://www.guimobile.net/blog/uploads/2012/07/107141057.png");
        mUrls.add("http://pic38.nipic.com/20140224/8472040_101914378000_2.jpg");
    }

    @Override
    public void setListener() {
        VPAdapter adapter = new VPAdapter(mUrls);
        vp.setAdapter(adapter);

        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                LogUtil.d("VP","onPageScrolled position = "+position+" positionOffset = "+positionOffset);
                ImageCacheLoader.loadUpperLayerBitmap(mUrls.get(position%mUrls.size()),true,fiv);
                if (position == 0){
                    ImageCacheLoader.loadUpperLayerBitmap(mUrls.get(0),false,fiv);
                }else {
                    ImageCacheLoader.loadUpperLayerBitmap(mUrls.get((position-1)%mUrls.size()),false,fiv);
                }
                fiv.update(positionOffset);

            }

            @Override
            public void onPageSelected(int position) {
                LogUtil.d("VP","onPageSelected position = "+position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }




    /****************************************/
    class VPAdapter extends PagerAdapter{
        ArrayList<String> url = new ArrayList<String>();

        public VPAdapter(ArrayList<String> url) {
            this.url = url;
        }

        @Override
        public int getCount() {
            return url == null ? 0 : Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            String imageUrl = url.get((position+1) % url.size());
            FloatCircleImageView v = (FloatCircleImageView) View.inflate(FloatCircleActivity.this,R.layout.ui_page_item,null);
            ImageCacheLoader.loadUpperLayerBitmap(imageUrl,false,v);
            container.addView(v);
            return v;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }


        @Override
        public float getPageWidth(int position) {
//            position = position % 3;
//            float width = 0;
//            if (position == 0){
//                width = 0.34f;
//            }else {
//                width = 0.33f;
//            }
//            return width;
            return .5f;
        }
    }
}
