package com.example.common.widget;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.common.R;

public class ClearEditText extends TextInputEditText implements View.OnFocusChangeListener, TextWatcher, View.OnTouchListener {

    private Drawable mClearTextIcon;
    private OnFocusChangeListener mOnFocusChangeListener;
    private OnTouchListener mOnTouchListener;

    public ClearEditText(final Context context) {
        super(context);
        init(context);
    }

    public ClearEditText(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ClearEditText(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(final Context context) {

        final Drawable drawable = ContextCompat.getDrawable(context, R.drawable.icon_delete_32);
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable); //Wrap the drawable so that it can be tinted pre Lollipop
        DrawableCompat.setTint(wrappedDrawable, getCurrentHintTextColor());
        mClearTextIcon = wrappedDrawable;

        //        mClearTextIcon= context.getResources().getDrawable(R.drawable.icon_delete_32);
        mClearTextIcon.setBounds(0, 0, mClearTextIcon.getIntrinsicHeight(), mClearTextIcon.getIntrinsicHeight());

        if (getText().toString().length() > 0) {


            setClearIconVisible(true);
        }

        setOnTouchListener(this);
        setOnFocusChangeListener(this);
        addTextChangedListener(this);
    }

    @Override
    public void setOnFocusChangeListener(OnFocusChangeListener l) {
        mOnFocusChangeListener = l;
    }

    @Override
    public void setOnTouchListener(OnTouchListener l) {
        mOnTouchListener = l;
    }

    /** 当ClearEditText焦点发生变化的时候，判断里面字符串长度设置清除图标的显示与隐藏 */
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            setClearIconVisible(getText().length() > 0);
        } else {
            setClearIconVisible(false);
        }
        if (mOnFocusChangeListener != null) {
            mOnFocusChangeListener.onFocusChange(v, hasFocus);
        }
    }

    // 处理删除事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (event.getX() > getWidth()) {
                getPaddingRight();
                mClearTextIcon.getIntrinsicWidth();
                setText("");
            }

        }
        return super.onTouchEvent(event);
    }

    //    @Override
    //    public boolean onTouch(View v, MotionEvent motionEvent) {
    //
    //        Log.i("日志", "onTouchEvent:外 ");
    //        final int x = (int) motionEvent.getX();
    //        if (mClearTextIcon.isVisible() && x > getWidth() - getPaddingRight() - mClearTextIcon.getIntrinsicWidth()) {
    //            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
    //                setError(null);
    //                setText("");
    //                Log.i("日志", "onTouchEvent: ");
    //            }
    //            return true;
    //        }
    //        return mOnTouchListener != null && mOnTouchListener.onTouch(v,motionEvent);
    //    }

    //    @Override
    //    public boolean onTouch(View view, MotionEvent motionEvent) {
    //        Log.i("日志", "onTouch:外 ");
    //        final int x = (int) motionEvent.getX();
    //        if (mClearTextIcon.isVisible() && x > getWidth() - getPaddingRight() - mClearTextIcon.getIntrinsicWidth()) {
    //            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
    //                setError(null);
    //                setText("");
    //                Log.i("日志", "onTouch: ");
    //            }
    //            return true;
    //        }
    //        return mOnTouchListener != null && mOnTouchListener.onTouch(view, motionEvent);
    //    }

    /**
     * 当输入框里面内容发生变化的时候回调的方法
     */
    @Override
    public final void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        if (isFocused()) {
            setClearIconVisible(text.length() > 0);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    /**
     * 设置清除图标的显示与隐藏，调用setCompoundDrawables为EditText绘制上去
     *
     * @param visible
     */
    public void setClearIconVisible(final boolean visible) {
        mClearTextIcon.setVisible(visible, false);
        final Drawable[] compoundDrawables = getCompoundDrawables();
        setCompoundDrawables(
                compoundDrawables[0],
                compoundDrawables[1],
                visible ? mClearTextIcon : null,
                compoundDrawables[3]);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }


    //(x,y)是否在view的区域内
    private boolean isTouchPointInView(View view, int x, int y) {
        if (view == null) {
            return false;
        }
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int left = location[0];
        int top = location[1];
        int right = left + view.getMeasuredWidth();
        int bottom = top + view.getMeasuredHeight();
        //view.isClickable() &&
        if (y >= top && y <= bottom && x >= left
                && x <= right) {
            return true;
        }
        return false;
    }
}
