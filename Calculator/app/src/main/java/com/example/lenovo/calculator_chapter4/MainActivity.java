package com.example.lenovo.calculator_chapter4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private EditText firstText;//name
    private EditText secondText;

    private Button select_Type;//用的是Id
    private Button calculate;
    private TextView calc_type_text;

    private int firstNumber = 0;
    private int secondNumber = 0;//初始化

    private int resultCode = 0;
    private int calctype = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        firstText =(EditText) this.findViewById(R.id.first_number);
        secondText =(EditText) this.findViewById(R.id.second_number);
        calc_type_text=(TextView) this.findViewById(R.id.calc_type_text);
        select_Type=(Button)this.findViewById(R.id.selset_Type);//选择计算类型按钮
        calculate=(Button)this.findViewById(R.id.calculate);//计算按钮

        select_Type.setOnClickListener(new SelectCalulationTypeListener());//监视选择按钮
        calculate.setOnClickListener(new ClaculationListener());//监视计算按钮

    }

    class SelectCalulationTypeListener  implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this,CalculationTypeActivity.class);
            MainActivity.this.startActivityForResult(intent,resultCode);//使用这个方法来启动调用方法二
        }
    }

    @Override
    //当第二个活动结束之后会回调这个方法
    // 参1是启动活动二时传入的请求码，用来判断数据来源，参2用来判断处理结果是否成功
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);好像这句可有可无
        if(this.resultCode == requestCode && CalculationTypeActivity.resultCode== resultCode)
        {
            Bundle bundle = data.getExtras();
            calctype=data.getIntExtra("type1",-1);//从活动二中接收计算类型这个值
           calc_type_text.setText(ConstantInfo.typeMap.get(calctype));
        }
    }

    class ClaculationListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this,ResultActivity.class);

            intent.putExtra("first_number", firstText.getText().toString());//键，真正要传的值
            intent.putExtra("second_number", secondText.getText().toString());
            intent.putExtra("calctype",calctype);

            int result = 0;
            // firstText.getText().toString()就是把编辑框输入的内容取成字符串
            //integer.parseInt(String)的作用就是将String字符类型数据转换为Integer整型数据。
            int first_number =Integer.parseInt(firstText.getText().toString());
            int second_number =Integer.parseInt(secondText.getText().toString());
            //有了数字1和数字2以及运算类型，下面就可以进行计算
            switch (calctype)
            {
                case 1:
                    result = first_number + second_number;
                    break;

                case 2:
                    result = first_number - second_number;
                    break;

                case 3:
                    result = first_number * second_number;
                    break;

                case 4:
                    result = first_number / second_number;
                    break;

                default:
                    result=1;
                    break;
            }

            intent.putExtra("result",result);//键，要传的计算结果
            startActivity(intent);
        }
    }



}
