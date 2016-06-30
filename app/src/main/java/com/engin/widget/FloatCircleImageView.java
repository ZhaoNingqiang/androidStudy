package com.engin.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.engin.utils.LogUtil;

/**
 * Created by zhaoningqiang on 16/6/25.
 */

public class FloatCircleImageView extends ImageView {
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


    public FloatCircleImageView(Context context) {
        super(context);
        init();
    }

    public FloatCircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FloatCircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init(){
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public FloatCircleImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    public void setUpperLayerBitmap(Bitmap bitmap){
//        contentRect = new RectF(getPaddingLeft(),getPaddingTop(),getWidth() - getPaddingRight(),getHeight() - getPaddingBottom());
        floatBitmap = bitmap;
        floatShader = new BitmapShader(floatBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        floatPaint.setShader(floatShader);
        updateFloatMatrix();
        isDrawBitmap = true;
        invalidate();


        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;

        options.inSampleSize = floatBitmap.getWidth()/getWidth();



    }



    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        contentRect = new RectF(getPaddingLeft(),getPaddingTop(),getWidth() - getPaddingRight(),getHeight() - getPaddingBottom());
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



    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
    }


    public void update(float offset){
        LogUtil.d("test update 11111 offset = " + offset);
        if (offset >=0 && offset <=1){
            this.offset = offset;
            invalidate();
        }

    }
    private volatile float offset;
    private boolean isDrawBitmap = true;

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        if (floatBitmap != null){
//            canvas.save();
//            canvas.translate(getWidth() * offset, 0);
//            canvas.drawBitmap(floatBitmap, null, contentRect,null);
//            canvas.restore();
//        }
//
//
//
//
//
//
//        mPaint.setARGB(255,255,0,255);
//        mPaint.setStyle(Paint.Style.FILL);
//        RectF rectF = new RectF(getPaddingLeft(),getPaddingTop(),getWidth() - getPaddingRight(),getHeight() - getPaddingBottom());
//        Path mPath = new Path();
//        mPath.addRect(rectF, Path.Direction.CW);
//        mPath.addArc(rectF,0,360);
//        mPath.setFillType(Path.FillType.EVEN_ODD);
//        canvas.drawPath(mPath,mPaint);
    }




}
