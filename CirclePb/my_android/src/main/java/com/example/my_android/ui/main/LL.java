package com.example.my_android.ui.main;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.common.widget.ClearEditText;
import com.example.my_android.R;
import com.example.my_android.ui.main.数据存储.LoginActivity;

public class LL extends LinearLayout {
    public LL(Context context) {
        this(context, null);
    }

    public LL(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LL(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean xx = xx(this);
        if (xx) {
            return true;
        }
        return super.onInterceptTouchEvent(ev);
    }

    private boolean xx(ViewGroup v) {


        //        if (v instanceof ViewGroup){
        //
        //            ViewGroup v1 = (ViewGroup) v;
        //
        //            int childCount = v1.getChildCount();
        //
        //
        //            for (int i = 0; i < childCount; i++) {
        //
        //
        //                View childAt = v1.getChildAt(i);
        //
        //
        //            }
        //        }

        int childCount = v.getChildCount();                 // 获取孩子数量
        Log.i("日志", "xx: "+v+"："+childCount);

        for (int i = 0; i < childCount; i++) {              // 遍历

            View childAt = getChildAt(i);                   // 获取孩子

            if (childAt instanceof ClearEditText) {         // 如果孩子是ClearEditText，就处理

            } else if (childAt instanceof ViewGroup) {      // 如果是ViewGroup，就可能还有孩子

                ViewGroup childAt2 = (ViewGroup) childAt;   // 强制转成ViewGroup

                xx(v);                                      // 递归，方法调用方法，重新获取这个ViewGroup孩子数量
            }

            //            } else if (childAt instanceof ClearEditText) {
            //                ClearEditText childAt1 = (ClearEditText) childAt;
            //                if (childAt1.getText().length() > 0) {
            //                    return false;
            //                } else {
            //                    return true;
            //                }


        }
        return false;
    }
}


//        ClearEditText et = findViewById(R.id.et2);
//        if (et.getText().length() > 0) {
//            return false;
//        } else {
//            return true;
//        }

//    }
//}
