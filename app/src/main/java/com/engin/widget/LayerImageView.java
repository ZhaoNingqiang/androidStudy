package com.engin.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * @description: author:zhaoningqiang
 * @time 16/6/27/下午3:08
 */
public class LayerImageView extends FrameLayout {
    public LayerImageView(Context context) {
        super(context);
        init(context);
    }

    public LayerImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LayerImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LayerImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private ImageView underImageView;
    private ImageView topImageView;

    private void init(Context context) {
        setWillNotDraw(false);
        underImageView = new ImageView(context);
        FrameLayout.LayoutParams underParamas = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        underImageView.setLayoutParams(underParamas);
        underImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        addView(underImageView);


        topImageView = new ImageView(context);
        FrameLayout.LayoutParams topParamas = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        topImageView.setLayoutParams(topParamas);
        topImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        addView(topImageView);
    }


    public void setUpperLayerBitmap(Bitmap bitmap){
        topImageView.setImageBitmap(bitmap);
    }

    public void setImageBitmap(Bitmap bm) {
        underImageView.setImageBitmap(bm);
    }

    int width = 0;
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
    }

    public void update(float offset){
        if (offset >=0 && offset <=1){
            offset = 1.f - offset ;
            topImageView.setScrollX((int) (width * offset));
            invalidate();
        }
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setARGB(255,255,0,255);
        mPaint.setStyle(Paint.Style.FILL);
        RectF rectF = new RectF(getPaddingLeft(),getPaddingTop(),getWidth() - getPaddingRight(),getHeight() - getPaddingBottom());
        Path mPath = new Path();
        mPath.addRect(rectF, Path.Direction.CW);
        mPath.addArc(rectF,0,360);
        mPath.setFillType(Path.FillType.EVEN_ODD);
        canvas.drawPath(mPath,mPaint);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
    }
}
