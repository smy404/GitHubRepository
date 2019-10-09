package com.smy.timepicker;
/**
 * 在屏幕中添加时间选择器，并实现在改变时间的时候，通过消息提示框显示改变后的时间。
 */

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TimePicker timePicker = findViewById(R.id.timePicker);

        timePicker.setIs24HourView(true);//默认是12进制的，这里设置成24进制

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hourOfDay, int minute) {
                String str = hourOfDay+"时"+minute+"分";
                Toast.makeText(MainActivity.this,str,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
