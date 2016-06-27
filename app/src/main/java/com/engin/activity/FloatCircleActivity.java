package com.engin.activity;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.engin.R;
import com.engin.cache.ImageCacheLoader;
import com.engin.utils.LogUtil;
import com.engin.widget.FixViewPager;
import com.engin.widget.FloatCircleImageView;

import java.util.ArrayList;

/**
 * Created by zhaoningqiang on 16/6/25.
 */

public class FloatCircleActivity extends BaseActivity {
    FloatCircleImageView fiv;
    FixViewPager vp;
    ArrayList<Struct> mUrls = new ArrayList<Struct>();
    @Override
    public int getLayout() {
        return R.layout.acitivty_float_circle_view;
    }

    @Override
    public void initView() {
        fiv = (FloatCircleImageView) findViewById(R.id.fiv);
        vp = (FixViewPager) findViewById(R.id.vp);


        mUrls = new ArrayList<Struct>();




        mUrls.add(new Struct("http://pic38.nipic.com/20140224/8472040_101914378000_2.jpg","-0-"));
        mUrls.add(new Struct("http://img4.imgtn.bdimg.com/it/u=172112303,3232882607&fm=21&gp=0.jpg","-1-"));
        mUrls.add(new Struct("http://img3.imgtn.bdimg.com/it/u=1876649012,2419765871&fm=21&gp=0.jpg","-2-"));
        mUrls.add(new Struct("http://img4.imgtn.bdimg.com/it/u=3053650863,2902423685&fm=21&gp=0.jpg","-3-"));
        mUrls.add(new Struct("http://www.guimobile.net/blog/uploads/2012/07/107141057.png","-4-"));
    }

    @Override
    public void setListener() {
        VPAdapter adapter = new VPAdapter(mUrls);
        vp.setAdapter(adapter);

        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {



                LogUtil.d("VP","dddd onPageScrolled position = "+position+" positionOffset = "+positionOffset +"  positionOffsetPixels = "+positionOffsetPixels+" name = "+mUrls.get((position)%mUrls.size()).name);
                ImageCacheLoader.loadUpperLayerBitmap(mUrls.get((position)%mUrls.size()).url,true,fiv);
                if (position == 0){
                    ImageCacheLoader.loadUpperLayerBitmap(mUrls.get(0).url,false,fiv);
                }else {
                    ImageCacheLoader.loadUpperLayerBitmap(mUrls.get((position-1)%mUrls.size()).url,false,fiv);
                }
                fiv.update(positionOffset);

            }

            @Override
            public void onPageSelected(int position) {
                LogUtil.d("VP","dddd onPageSelected position = "+position+" name = "+mUrls.get((position)%mUrls.size()).name);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vp.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                LogUtil.d("VP","vvv page tag = "+((Struct)page.getTag()).name+"  position = "+position);
            }
        });

    }




    /****************************************/
    class VPAdapter extends PagerAdapter{
        ArrayList<Struct> url = new ArrayList<Struct>();

        public VPAdapter(ArrayList<Struct> url) {
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
            Struct struct = url.get((position+1) % url.size());
            FloatCircleImageView v = (FloatCircleImageView) View.inflate(FloatCircleActivity.this,R.layout.ui_page_item,null);
            ImageCacheLoader.loadUpperLayerBitmap(struct.url,false,v);
            container.addView(v);
            v.setTag(struct);
            return v;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }


        @Override
        public float getPageWidth(int position) {
           return 1/3f;
        }
    }


    class Struct{
        public Struct(String url, String name) {
            this.url = url;
            this.name = name;
        }

        String url;
        String name;
    }
}
