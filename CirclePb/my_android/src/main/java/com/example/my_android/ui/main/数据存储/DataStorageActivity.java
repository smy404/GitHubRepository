package com.example.my_android.ui.main.数据存储;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.my_android.R;

public class DataStorageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    // 启动SharedPrefrence存储的测试界面
    public void onClickSP(View view) {
        startActivity(new Intent(this, SFActivity.class));
    }

    // 启动手机内部文件存储的测试界面
    public void onClickIF(View view) {
        startActivity(new Intent(this, InnerActivity.class));
    }

    // 启动SD卡存储的测试界面
    public void onClickOF(View view) {
        startActivity(new Intent(this, SDActivity.class));
    }


    // 启动Network存储的测试界面
    public void onClickNW(View view) {
        startActivity(new Intent(this, NetworkActivity.class));
    }

    // 启动DB存储的测试界面
    public void onClickDB(View view) {
        startActivity(new Intent(this, DBActivity.class));
    }
}
