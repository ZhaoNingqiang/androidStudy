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
import com.engin.widget.FloatSurfaceView;
import com.engin.widget.LayerImageView;

import java.util.HashMap;

/**
 * Created by zhaoningqiang on 16/6/25.
 */

public class ImageCacheLoader {
    // 取运行内存阈值的1/8作为图片缓存
    private static final int MEM_CACHE_SIZE = 1024 * 1024 * ((ActivityManager) Rose.sContext
            .getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass() / 8;
    private static ImageLoader mImageLoader = new ImageLoader(Rose.mRequestQueue, new BitmapLruCache(
            MEM_CACHE_SIZE));




    public static void loadUpperLayerBitmap(final String requestUrl, final boolean setFloat, final FloatCircleImageView view, final HashMap<String,Bitmap> cache) {
        final Bitmap cacheBitmap = cache.get(requestUrl);
        if ( cacheBitmap != null){
            if (setFloat){
                view.setUpperLayerBitmap(cacheBitmap);
            }else {
                view.setImageBitmap(cacheBitmap);
            }
        }else {
            ImageLoader.ImageListener imageListener = new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    if (response.getBitmap() != null) {
                        if (setFloat){
                            view.setUpperLayerBitmap(response.getBitmap());
                        }else {
                            view.setImageBitmap(response.getBitmap());
                        }
                       if (!cache.containsKey(requestUrl)){
                           cache.put(requestUrl,response.getBitmap());
                       }
                    }
                }

                @Override
                public void onErrorResponse(VolleyError error) {

                }
            };
             mImageLoader.get(requestUrl, imageListener,Rose.SCREEN_WIDTH/6 , Rose.SCREEN_WIDTH/6);
        }

    }




    public static void loadUpperLayerBitmap(final String requestUrl, final boolean setFloat, final LayerImageView view, final HashMap<String,Bitmap> cache) {
        final Bitmap cacheBitmap = cache.get(requestUrl);
        if ( cacheBitmap != null){
            if (setFloat){
                view.setUpperLayerBitmap(cacheBitmap);
            }else {
                view.setImageBitmap(cacheBitmap);
            }
        }else {
            ImageLoader.ImageListener imageListener = new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    if (response.getBitmap() != null) {
                        if (setFloat){
                            view.setUpperLayerBitmap(response.getBitmap());
                        }else {
                            view.setImageBitmap(response.getBitmap());
                        }
                        if (!cache.containsKey(requestUrl)){
                            cache.put(requestUrl,response.getBitmap());
                        }
                    }
                }

                @Override
                public void onErrorResponse(VolleyError error) {

                }
            };
            mImageLoader.get(requestUrl, imageListener, 0, 0);
        }

    }
}
