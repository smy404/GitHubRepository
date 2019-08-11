package com.example.lenovo.calculator_chapter4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private Button back;
    private TextView resulText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        back = (Button)this.findViewById(R.id.back);
        resulText = (TextView)this.findViewById(R.id.resulText);

        Intent intent = this.getIntent();
        back.setOnClickListener(new BackListener());//监视返回键

        StringBuilder builder = new StringBuilder();
        builder.append(intent.getStringExtra("first_number"))
                .append(" ")
                .append(ConstantInfo.infoMap.get(intent.getIntExtra("calctype",0)))
                // 调用map集合方法get,通过键获取到值
                .append(" ")
                .append(intent.getStringExtra("second_number"))
                //getStringExtra("second_number")根据键second_number显示意图值
                .append(" ")
                .append("=")
                .append(intent.getIntExtra("result",-1));
        //在 StringBuilder 上的主要操作是 append 和 insert 方法。每个方法都能有效地将给定的数据转换成字符串，
        // 然后将该字符串的字符添加或插入到字符串生成器中。append 方法始终将这些字符添加到生成器的末端；
        //StringBuilder类可以用于在无需创建一个新的字符串对象情况下修改字符串。
        //这个串是 num1 + num2 = 结果
        resulText.setText(builder.toString());

    }

    class BackListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ResultActivity.this,MainActivity.class);
            startActivity(intent);
            ResultActivity.this.finish();//销毁当前活动
        }
    }



}
