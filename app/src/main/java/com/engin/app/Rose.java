package com.engin.app;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.engin.cache.BitmapLruCache;

/**
 * Created by zhaoningqiang on 16/6/25.
 */

public class Rose extends Application {

    public static Context sContext;
    public static RequestQueue mRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        mRequestQueue = Volley.newRequestQueue(sContext);
        initImageLoader(getApplicationContext());
    }

    public static Context getContext() {
        return sContext;
    }

    // 初始化ImageLoader
    public static void initImageLoader(Context context) {
//        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
//                .threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
//                .memoryCache(new LruMemoryCache(2 * 1024 * 1024)).discCacheSize(10 * 1024 * 1024)
//                .discCacheFileNameGenerator(new Md5FileNameGenerator())
//                .tasksProcessingOrder(QueueProcessingType.LIFO)
//                .build();
//        ImageLoader.getInstance().init(config);
    }
}
