package com.example.my_android.ui.main;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import static android.view.MotionEvent.ACTION_DOWN;
import static org.greenrobot.eventbus.EventBus.TAG;

public class TT extends TextInputEditText {
    public TT(Context context) {
        super(context);
    }

    public TT(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TT(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }




    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case ACTION_DOWN:

                Log.i(TAG, "onTouchEvent: ACTION_DOWN--"+ACTION_DOWN);
                return true;
        }


        return super.onTouchEvent(event);
    }
}
