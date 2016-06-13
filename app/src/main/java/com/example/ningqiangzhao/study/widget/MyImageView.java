package com.example.ningqiangzhao.study.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

import com.example.ningqiangzhao.study.utils.ULog;

/**
 * @description: author:zhaoningqiang
 * @time 16/6/7/下午3:07
 */
public class MyImageView extends CircleImageView {
    private static final Bitmap.Config BITMAP_CONFIG = Bitmap.Config.ARGB_8888;
    private static final int COLORDRAWABLE_DIMENSION = 1;
    private static final int DEFAULT_BORDER_WIDTH = 0;
    private int mBorderWidth = DEFAULT_BORDER_WIDTH;

    private final Matrix mShaderMatrix = new Matrix();


    private int mBitmapWidth;
    private int mBitmapHeight;

    private BitmapShader mBitmapShader;
    private float mDrawableRadius;
    private final RectF mDrawableRect = new RectF();
    private final Paint mBitmapPaint = new Paint();
    private final RectF mBorderRect = new RectF();


    public MyImageView(Context context) {
        super(context);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private Bitmap mBitmap;

    public void setUpperLayerDrawable(Drawable drawable) {
        mBitmap = getBitmapFromDrawable(drawable);
        setup();
    }


    private Bitmap getBitmapFromDrawable(Drawable drawable) {
        if (drawable == null) {
            return null;
        }

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        try {
            Bitmap bitmap;

            if (drawable instanceof ColorDrawable) {
                bitmap = Bitmap.createBitmap(COLORDRAWABLE_DIMENSION, COLORDRAWABLE_DIMENSION, BITMAP_CONFIG);
            } else {
                bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), BITMAP_CONFIG);
            }

            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (OutOfMemoryError e) {
            return null;
        }
    }

    private void setup() {
//        if (!mReady) {
//            mSetupPending = true;
//            return;
//        }

        if (mBitmap == null) {
            return;
        }
//        bgPaint=new Paint();
//        bgPaint.setStyle(Paint.Style.FILL);
//        bgPaint.setColor(bgColor);
//        bgPaint.setAntiAlias(true);

        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        mBitmapPaint.setAntiAlias(true);
        mBitmapPaint.setShader(mBitmapShader);

//        mBorderPaint.setStyle(Paint.Style.STROKE);
//        mBorderPaint.setAntiAlias(true);
//        mBorderPaint.setColor(mBorderColor);
//        mBorderPaint.setStrokeWidth(mBorderWidth);

        mBitmapHeight = mBitmap.getHeight();
        mBitmapWidth = mBitmap.getWidth();

        mBorderRect.set(0, 0, getWidth(), getHeight());
//        mBorderRadius = Math.min((mBorderRect.height() - mBorderWidth) / 2, (mBorderRect.width() - mBorderWidth) / 2);

        mDrawableRect.set(mBorderWidth, mBorderWidth, mBorderRect.width() - mBorderWidth, mBorderRect.height() - mBorderWidth);
        mDrawableRadius = Math.min(mDrawableRect.height() / 2, mDrawableRect.width() / 2);

        updateShaderMatrix();
        invalidate();
    }
    private void updateShaderMatrix(){
        updateShaderMatrix(1f);
    }

    private void updateShaderMatrix(float ff) {
        float scale;
        float dx = 0;
        float dy = 0;

        mShaderMatrix.set(null);

        if (mBitmapWidth * mDrawableRect.height() > mDrawableRect.width() * mBitmapHeight) {
            scale = mDrawableRect.height() / (float) mBitmapHeight;
            dx = (mDrawableRect.width() - mBitmapWidth * scale) * 0.5f * ff;
        } else {
            scale = mDrawableRect.width() / (float) mBitmapWidth;
            dy = (mDrawableRect.height() - mBitmapHeight * scale) * 0.5f;
        }

        mShaderMatrix.setScale(scale, scale);
        mShaderMatrix.postTranslate((int) (dx + 0.5f) + mBorderWidth, (int) (dy + 0.5f) + mBorderWidth);

        mBitmapShader.setLocalMatrix(mShaderMatrix);
    }

    private float kk;

    public void update(float z) {
//        if (mBitmap == null) {
//            return;
//        }
        this.kk = z;
//        ULog.d("test z = " + z);

//        float trx = kk*getWidth();
//        mShaderMatrix.postTranslate(trx,0);
//        updateShaderMatrix(z);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        kk = 1 - kk;

//        kk = kk * 2 - .5f;
        canvas.translate(getWidth() * kk,0);
//        canvas.drawCircle(getWidth() , getHeight() / 2, mDrawableRadius, mBitmapPaint);

        canvas.drawCircle(getWidth() /2, getHeight() / 2, mDrawableRadius, mBitmapPaint);


    }
}
