package com.example.myrepository.fragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.myrepository.Constant;

public class CircleProgress extends View {

    private static final String TAG = CircleProgress.class.getSimpleName();

    private Context mContext;


    // 获取当前圆的中心点
    private Point mCenterPoint;


    // 绘制背景圆弧
    private Paint mBgArcPaint;      // 画笔
    private int mBgArcColor;        // 颜色
    private float mBgArcWidth=20;      // 画笔宽度
    private int mDefaultSize;

    // 绘制渐变圆弧
    private Paint mArcPaint;        // 画笔
    private float mArcWidth =20;        // 画笔宽度

    public CircleProgress(Context context) {
        this(context, null);
    }

    public CircleProgress(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mContext = context;

        mDefaultSize = MiscUtil.dipToPx(mContext, Constant.DEFAULT_SIZE);       // 默认大小150dp转成px
        mCenterPoint = new Point();

        initPaint();
    }

    private void initPaint() {

        // 绘制背景圆弧
        mBgArcPaint = new Paint();
        mBgArcPaint.setAntiAlias(true);
        mBgArcPaint.setColor(mBgArcColor);
        mBgArcPaint.setStyle(Paint.Style.STROKE);
        mBgArcPaint.setStrokeWidth(mBgArcWidth);
        mBgArcPaint.setStrokeCap(Paint.Cap.ROUND);


        // 绘制渐变圆弧
        mArcPaint = new Paint();
        mArcPaint.setAntiAlias(true);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setStrokeWidth(mArcWidth);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(MiscUtil.measure(widthMeasureSpec, mDefaultSize),
                MiscUtil.measure(heightMeasureSpec, mDefaultSize));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d(TAG, "onSizeChanged: w = " + w + "; h = " + h + "; oldw = " + oldw + "; oldh = " + oldh);

        //求圆弧和背景圆弧的最大宽度

        float maxArcWidth = Math.max(mArcWidth, mBgArcWidth);

        //求最小值作为实际值

        int minSize = Math.min(w - getPaddingLeft() - getPaddingRight() - 2 * (int) maxArcWidth,

                h - getPaddingTop() - getPaddingBottom() - 2 * (int) maxArcWidth);

        Log.d(TAG, "getPaddingLeft(): w = " + getPaddingLeft() + "; getPaddingRight = " + getPaddingRight() + "; oldw = " + oldw + "; oldh = " + oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawArc(canvas);
    }

    private void drawArc(Canvas canvas) {


        //        canvas.drawArc(canvas);
    }
}
