package com.smy.jishiqi;

/**
 * 60s计时器，到时间就停止计时
 * Chronometer控件
 */

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity {

    Chronometer chronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer= findViewById(R.id.chronometer);
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.setFormat("%s");
        chronometer.start();
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if(SystemClock.elapsedRealtime()-chronometer.getBase()>=60000){
                    chronometer.stop();
                }
            }
        });
    }
}
