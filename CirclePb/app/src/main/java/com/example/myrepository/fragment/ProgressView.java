package com.example.myrepository.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.myrepository.R;

public class ProgressView extends View {

    private Paint mPaint;
    private int mStrokeWidth;
    private RectF mRectFArc;
    private RectF mRectFInnerArc;
    private Rect mRectText;
    private float mStartAngle = 150;
    private float mSweepAngle = 240;
    private float mPercent;
    private String mTvxt;

    public ProgressView(Context context) {
        this(context, null);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {

        mStrokeWidth = dp2px(28);


        mRectFArc = new RectF();            // 创建一个具有指定坐标的新矩形
        mRectFInnerArc = new RectF();
        mRectText = new Rect();


    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // 将矩形的坐标设置为指定的值
        mRectFArc.set(33, 33, 33, 33);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(ContextCompat.getColor(getContext(), R.color.colorGrey200));

        //        drawCircle(canvas);         // 画圆形
        //        drawRect(canvas);           // 画矩形
        //        drawArc(canvas);            // 画圆弧

        drawArc2(canvas);            // 背景圆弧
        drawArc3(canvas);            // 背景圆弧


    }

    private void drawArc3(Canvas canvas) {

        Paint paint5 = new Paint();
        paint5.setColor(ContextCompat.getColor(getContext(), R.color.color_blue_400));
        paint5.setAntiAlias(true);
        paint5.setStyle(Paint.Style.STROKE);                // 填充样式改为描边

        int StrokeWidth = 40;
        paint5.setStrokeWidth(StrokeWidth);

        // 获取当前View的中心点
        PointF pointF = new PointF();
        pointF.x = getWidth() * 1.0f / 2;
        pointF.y = getHeight() * 1.0f / 2;

        // 获取圆的半径，如果外面的View长宽不一样，怎么样呢，要按哪个的半径设置圆的半径
        int radius = Math.min(getWidth(), getHeight()) / 2 - StrokeWidth / 2;     // 获取较小值的一半

        float percent = 0.5f;

        // 距形
        RectF oval = new RectF(
                pointF.x - radius,
                pointF.y - radius,
                pointF.x + radius,
                pointF.y + radius);

        canvas.drawArc(oval,
                145,                    // 椭圆弧起始角度
                percent * 360f,                 // 顺时针方向测量扫角(度数)
                false,                  // 如果是true就会把弧的两端与圆心相连，如果是false只画弧
                paint5);




    }

    private void drawArc2(Canvas canvas) {

        Paint paint4 = new Paint();
        paint4.setColor(ContextCompat.getColor(getContext(), R.color.color_blue_100));
        paint4.setAntiAlias(true);
        paint4.setStyle(Paint.Style.STROKE);                // 填充样式改为描边

        int StrokeWidth = 40;
        paint4.setStrokeWidth(StrokeWidth);

        // 获取当前View的中心点
        PointF pointF = new PointF();
        pointF.x = getWidth() * 1.0f / 2;
        pointF.y = getHeight() * 1.0f / 2;

        // 获取圆的半径，如果外面的View长宽不一样，怎么样呢，要按哪个的半径设置圆的半径
        int radius = Math.min(getWidth(), getHeight()) / 2 - StrokeWidth / 2;     // 获取较小值的一半


        // 距形
        RectF oval = new RectF(
                pointF.x - radius,
                pointF.y - radius,
                pointF.x + radius,
                pointF.y + radius);

        canvas.drawArc(oval,
                145,                    // 椭圆弧起始角度
                0.7f * 360f,                 // 顺时针方向测量扫角(度数)
                false,                  // 如果是true就会把弧的两端与圆心相连，如果是false只画弧
                paint4);
    }

    private void drawRect(Canvas canvas) {

        Paint paint2 = new Paint();
        paint2.setColor(Color.RED);                         // 设置画笔颜色
        paint2.setStyle(Paint.Style.FILL);                  // 设置填充样式
        paint2.setStrokeWidth(15);                          // 设置画笔宽度

        canvas.drawRect(10, 10, 100, 100, paint2);      // 画矩形1

        RectF rect = new RectF(120, 10, 210, 100);      // 画矩形2
        canvas.drawRect(rect, paint2);

        Rect rect3 = new Rect(230, 10, 320, 100);       // 画矩形3
        canvas.drawRect(rect3, paint2);

    }

    private void drawArc(Canvas canvas) {

        Paint paint3 = new Paint();
        paint3.setColor(Color.RED);                         // 设置画笔颜色
        paint3.setStyle(Paint.Style.STROKE);                // 填充样式改为描边

        int StrokeWidth = 30;
        paint3.setStrokeWidth(StrokeWidth);                 // 设置画笔宽度


        float percent = 0.5f;                               // 扫角(度数)百分比

        // 获取当前View的中心点
        PointF pointF = new PointF();
        pointF.x = getWidth() * 1.0f / 2;
        pointF.y = getHeight() * 1.0f / 2;

        // 获取圆的半径，如果外面的View长宽不一样，怎么样呢，要按哪个的半径设置圆的半径
        int radius = Math.min(getWidth(), getHeight()) / 2 - StrokeWidth / 2;     // 获取较小值的一半

        // 距形
        RectF oval = new RectF(
                pointF.x - radius,
                pointF.y - radius,
                pointF.x + radius,
                pointF.y + radius);

        canvas.drawArc(oval,
                -90,                    // 椭圆弧起始角度
                mPercent * 360,        // 顺时针方向测量扫角(度数)
                false,                  // 如果是true就会把弧的两端与圆心相连，如果是false只画弧
                paint3);

        RectF rect2 = new RectF(400, 10, 600, 100);
        canvas.drawArc(rect2, 0, 90, false, paint3);

    }

    private void drawCircle(Canvas canvas) {

        Paint paint1 = new Paint();                 // 设置画笔
        paint1.setStrokeCap(Paint.Cap.BUTT);        // 设置油漆的线帽样式
        paint1.setStyle(Paint.Style.STROKE);        // 设置填充样式，仅描边
        paint1.setStrokeWidth(mStrokeWidth);        // 设置画笔的宽度
        paint1.setColor(Color.RED);                 // 设置颜色
        paint1.setAntiAlias(true);                  // 设置抗锯齿

        int StrokeWidth = 20;
        paint1.setStrokeWidth(StrokeWidth);          // 设置画笔宽度

        PointF pointF = new PointF();

        // 获取当前View的中心点
        pointF.x = getWidth() * 1.0f / 2;
        pointF.y = getHeight() * 1.0f / 2;

        // 获取圆的半径，如果外面的View长宽不一样，怎么样呢，要按哪个的半径设置圆的半径
        int radius = Math.min(getWidth(), getHeight()) / 2 - StrokeWidth / 2;     // 获取较小值的一半

        canvas.drawCircle(           // 使用canvas画圆形
                pointF.x,            // 圆心点X轴坐标
                pointF.y,            // 圆心点Y轴坐标
                radius,              // 圆的半径
                paint1);

    }


    public void setText(String tvxt) {
        this.mTvxt = tvxt;
    }

    // 设置进度
    public void setPercent(float percent) {
        this.mPercent = percent;
        invalidate();// 每次设置都绘制一下
    }


    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                Resources.getSystem().getDisplayMetrics());
    }

    private int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                Resources.getSystem().getDisplayMetrics());
    }


}
