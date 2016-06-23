package com.engin.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;

/**douhao
 * Created by zhaoningqiang on 16/6/18.
 * 带有引号得TextView
 *
 * 存在得问题:
 * 绘制不同大小文字得baseLine不好找,没有对齐
 *
 * ----------------
 *
 * android 关于paint的设置
 * http://blog.csdn.net/wangchangshuai0010/article/details/7336435
 */

public class QuotationTextView extends AppCompatTextView {
    private String TAG = QuotationTextView.class.getSimpleName();
    String quotationMark = "“";
    Paint textPaint = null;
    float quotationMarkWidth ;

    public QuotationTextView(Context context) {
        super(context);
        init();
    }

    public QuotationTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public QuotationTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init(){
        textPaint  = new Paint(getPaint());
        textPaint.setTextSize(100);
        textPaint.setFakeBoldText(true);
        quotationMarkWidth = textPaint.measureText(quotationMark);
        setPadding((int) (getPaddingLeft()+quotationMarkWidth),getPaddingTop(),getPaddingRight(),getPaddingBottom());
        log(TAG,"quotationMarkWidth"+quotationMarkWidth);
    }




    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        if (widthMode == MeasureSpec.UNSPECIFIED){
            log(TAG,"widthMode UNSPECIFIED");
        }else if (widthMode == MeasureSpec.EXACTLY){
            log(TAG,"widthMode EXACTLY");
        }else if (widthMode == MeasureSpec.AT_MOST){
            log(TAG,"widthMode AT_MOST");
        }else {
            log(TAG,"widthMode error-");
        }
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        log(TAG,"measureWidth = "+measureWidth);

        //height
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (heightMode == MeasureSpec.UNSPECIFIED){
            log(TAG,"heightMode UNSPECIFIED");
        }else if (heightMode == MeasureSpec.EXACTLY){
            log(TAG,"heightMode EXACTLY");
        }else if (heightMode == MeasureSpec.AT_MOST){
            log(TAG,"heightMode AT_MOST");
        }else {
            log(TAG,"heightMode error-");
        }
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        log(TAG,"measureHeight = "+measureHeight);
        int width = (int) (measureWidth + quotationMarkWidth);
        setMeasuredDimension(width,measureHeight);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        float size = getPaint().getTextSize();
        float textSzie = textPaint.getTextSize();
        canvas.drawText(quotationMark,getPaddingLeft()-quotationMarkWidth,getBaseline(),textPaint);
        super.onDraw(canvas);
    }




    public  void log(String tag, String log) {
        try {
            Log.d(tag,log);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
