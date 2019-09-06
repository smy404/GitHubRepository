package com.example.my_android.ui.main;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.common.BaseActivity;
import com.example.common.utils.BaseToolbarActivity;
import com.example.common.utils.IDCardUtil;
import com.example.my_android.R;
import com.example.my_android.ui.main.数据存储.LoginActivity;

public class EmptyActivity extends AppCompatActivity {


    private EditText mEt;
    TextInputEditText mEt2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        LinearLayout ll = findViewById(R.id.ll);
        LinearLayout ll2 = findViewById(R.id.ll2);

        mEt = findViewById(R.id.et);
        mEt2 = findViewById(R.id.et2);


        ll2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                startActivity(new Intent(EmptyActivity.this, LoginActivity.class));


                startActivityForResult(new Intent(EmptyActivity.this, LoginActivity.class), 1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        String str = data.getExtras().getString("key");

        int length = str.length();
        int length1 = mEt2.getText().toString().length();


        mEt2.setText(str);

        mEt2.requestFocus();
        mEt2.setSelection(length);
    }

    @Override
    protected void onResume() {
        super.onResume();


        //        mEt2.setSelection(2);



        Toast.makeText(this, "可见", Toast.LENGTH_SHORT).show();
    }

    public void click1(View view) {


        String s = mEt.getText().toString();

        boolean idCard = IDCardUtil.isIDCard(s);

        Toast.makeText(this, "" + idCard, Toast.LENGTH_SHORT).show();

    }


}
