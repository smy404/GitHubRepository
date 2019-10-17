package com.smy.gensuiview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrameLayout frameLayout=findViewById(R.id.myLayout);
        final RabbitView rabbitView=new RabbitView(this);
        //为了使图片跟随，需要为图片添加触摸事件监听器
        rabbitView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                rabbitView.bitmapX=motionEvent.getX();
                rabbitView.bitmapY=motionEvent.getY();
                //获得当前位置的xy坐标
                rabbitView.invalidate();
                //直接调用invalidate()方法，请求重新draw()，但只会绘制调用者本身。
                return true;
            }
        });

        frameLayout.addView(rabbitView);
        //把图片添加到当前布局


    }
}
