package com.engin.cache;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.engin.app.Rose;
import com.engin.widget.FloatCircleImageView;

/**
 * Created by zhaoningqiang on 16/6/25.
 */

public class ImageCacheLoader {
    // 取运行内存阈值的1/8作为图片缓存
    private static final int MEM_CACHE_SIZE = 1024 * 1024 * ((ActivityManager) Rose.sContext
            .getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass() / 8;
    private static ImageLoader mImageLoader = new ImageLoader(Rose.mRequestQueue, new BitmapLruCache(
            MEM_CACHE_SIZE));




    public static ImageLoader.ImageContainer loadUpperLayerBitmap(String requestUrl, final boolean setFloat, final FloatCircleImageView view) {
        ImageLoader.ImageContainer container;
        ImageLoader.ImageListener imageListener = new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                if (response.getBitmap() != null) {
                    if (setFloat){
                        view.setUpperLayerBitmap(response.getBitmap());
                    }else {
                        view.setImageBitmap(response.getBitmap());
                    }
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        };
        container = mImageLoader.get(requestUrl, imageListener, 0, 0);
        return container;
    }

}
