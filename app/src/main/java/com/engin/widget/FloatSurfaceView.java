package com.engin.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.concurrent.locks.Lock;

/**
 * @description: author:zhaoningqiang
 * @time 16/6/27/下午1:40
 */
public class FloatSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private static final String TAG = FloatSurfaceView.class.getSimpleName();
    private static final int SLEEP_TIME = 10;

    private Paint mPathPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap floatBitmap;
    private Bitmap backBitmap;
    private RectF contentRect;

    private float offset;

    private Path mPath = new Path();

    public FloatSurfaceView(Context context) {
        super(context);
        init();
    }

    public FloatSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FloatSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

   private void init(){
       getHolder().addCallback(this);
       mPathPaint.setARGB(255,255,0,255);
       mPathPaint.setStyle(Paint.Style.FILL);
   }

    private final Object mSurfaceLock = new Object();
    private DrawThread mThread;
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mThread = new DrawThread(holder);
        mThread.setRun(true);
        mThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        //这里可以获取SurfaceView的宽高等信息
        contentRect = new RectF(getPaddingLeft(),getPaddingTop(),width - getPaddingLeft() - getPaddingRight(),height - getPaddingTop() - getPaddingBottom());
        mPath.addRect(contentRect, Path.Direction.CW);
        mPath.addArc(contentRect,0,360);
        mPath.setFillType(Path.FillType.EVEN_ODD);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        synchronized (mSurfaceLock) {  //这里需要加锁，否则doDraw中有可能会crash
            mThread.setRun(false);
        }
    }

    private class DrawThread extends Thread {

        private SurfaceHolder mHolder;
        private boolean mIsRun = false;

        public DrawThread(SurfaceHolder holder) {
            super(TAG);
            mHolder = holder;
        }

        @Override
        public void run() {
            while(true) {
                synchronized (mSurfaceLock) {
                    if (!mIsRun) {
                        return;
                    }
                    Canvas canvas = mHolder.lockCanvas();
                    if (canvas != null) {
                        //绘制底层图
                        if (backBitmap != null){
                            canvas.drawBitmap(backBitmap, null, contentRect,null);
                        }

                       //绘制浮层图
                        if (floatBitmap != null){
                            canvas.save();
                            canvas.translate(getWidth() * offset, 0);
                            canvas.drawBitmap(floatBitmap, null, contentRect,null);
                            canvas.restore();
                        }


                        //绘制中空圆
                        canvas.drawPath(mPath,mPathPaint);


                       mHolder.unlockCanvasAndPost(canvas);
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                try {
                    Thread.sleep(SLEEP_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void setRun(boolean isRun) {
            this.mIsRun = isRun;
        }
    }

    public void setImageBitmap(Bitmap bitmap){

    }


    public void setUpperLayerBitmap(Bitmap bitmap){
        floatBitmap = bitmap;
    }



    public void update(float offset){
        if (offset >=0 && offset <=1){
            this.offset = 1.f - offset;
            invalidate();
        }
    }

}
