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

/**
 * @description: author:zhaoningqiang
 * @time 16/6/27/下午1:40
 */
public class FloatSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private static final String TAG = FloatSurfaceView.class.getSimpleName();
    private static final int SLEEP_TIME = 10;

    Paint mPaint = new Paint();
    Bitmap floatBitmap;
    Bitmap backBitmap;
    RectF contentRect;
    Matrix floatMatrix = new Matrix();
    Matrix backMatrix = new Matrix();
    BitmapShader backShader;
    BitmapShader floatShader;

    Paint floatPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint backPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public FloatSurfaceView(Context context) {
        super(context);
    }

    public FloatSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
    }

    public FloatSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public FloatSurfaceView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

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
                       // doDraw(canvas);  //这里做真正绘制的事情
                        if (floatBitmap != null){
                            canvas.translate(getWidth() * offset, 0);
                            canvas.drawBitmap(floatBitmap, null, contentRect,null);
                        }



                        mPaint.setARGB(255,255,0,255);
                        mPaint.setStyle(Paint.Style.FILL);
                        RectF rectF = new RectF(getPaddingLeft(),getPaddingTop(),getWidth() - getPaddingRight(),getHeight() - getPaddingBottom());
                        Path mPath = new Path();
                        mPath.addRect(rectF, Path.Direction.CW);
                        mPath.addArc(rectF,0,360);
                        mPath.setFillType(Path.FillType.EVEN_ODD);
                        canvas.drawPath(mPath,mPaint);


                       mHolder.unlockCanvasAndPost(canvas);
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
        floatShader = new BitmapShader(floatBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        floatPaint.setShader(floatShader);
        updateFloatMatrix();
        invalidate();
    }


    private void updateFloatMatrix() {
        float scale;
        float dx = 0;
        float dy = 0;

        floatMatrix.set(null);

        int bitmapWidth =  floatBitmap.getWidth();
        int bitmapHeight = floatBitmap.getHeight();

        if (bitmapWidth * contentRect.height() > contentRect.width() * bitmapHeight) {
            scale = contentRect.height() / (float) bitmapHeight;
            dx = (contentRect.width() - bitmapWidth * scale) * 0.5f;
        } else {
            scale = contentRect.width() / (float) bitmapWidth;
            dy = (contentRect.height() - bitmapHeight * scale) * 0.5f;
        }

        floatMatrix.setScale(scale, scale);
        floatMatrix.postTranslate((int) (dx + 0.5f), (int) (dy + 0.5f));

        floatShader.setLocalMatrix(floatMatrix);
    }

    public void update(float offset){
        if (offset >=0 && offset <=1){
            this.offset = 1.f - offset;
            invalidate();
        }
    }
    private float offset;
}
