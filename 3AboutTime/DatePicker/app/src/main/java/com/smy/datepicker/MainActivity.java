package com.smy.datepicker;


/**
 * 日期选择器
 * 使用了DatePicker控件
 * 在日历中点击时间时，可以使用Toast将其显示出来
 */

import android.app.Activity;
import android.os.Bundle;

import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends Activity {

    DatePicker datePicker;
    int year,month,day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datePicker= findViewById(R.id.datepicker);

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        //DatePicker初始化
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            //前三个形参是当前日期
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
                //后三个形参是点击的日期
                MainActivity.this.year=year;
                MainActivity.this.month=month;
                MainActivity.this.day=day;
                //貌似没有这三行也可以运行成功
                show(year, month, day);
            }
        });
    }

    private void show(int year,int month,int day) {
        String str = year + "年"+ (month+1) +"月" + day +"日";
        Toast.makeText(MainActivity.this,str,Toast.LENGTH_LONG).show();
    }
}
