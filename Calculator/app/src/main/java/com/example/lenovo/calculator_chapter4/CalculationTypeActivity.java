package com.example.lenovo.calculator_chapter4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class CalculationTypeActivity extends AppCompatActivity {
    private RadioGroup group ;
    private RadioButton addition ;
    private RadioButton minus ;
    private RadioButton multiply ;
    private RadioButton divider ;
    private Button okBtn ;

    private int calculationType = -1;
    public static final int resultCode = 0;//因为在第一个活动中，调用了这个结果，所以不能设为private


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation_type);

        group = (RadioGroup) this.findViewById(R.id.group);
        addition = (RadioButton) this.findViewById(R.id.addition);
        minus = (RadioButton) this.findViewById(R.id.minus);
        multiply = (RadioButton) this.findViewById(R.id.multiply);
        divider = (RadioButton) this.findViewById(R.id.divider);
        okBtn = (Button)this.findViewById(R.id.ok);

        group.setOnCheckedChangeListener(new GroupSelectListener());
        okBtn.setOnClickListener(new oklistener());//监视确定按钮
    }

    class GroupSelectListener implements RadioGroup.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            //选择的是那一个单选按钮
            int radioButton = group.getCheckedRadioButtonId();
            RadioButton btn = (RadioButton)CalculationTypeActivity.this.findViewById(radioButton);

            if(btn.getId()==addition.getId())
            {
                calculationType = 1;
            }
            else if(btn.getId()==minus.getId())
            {
                calculationType = 2;
            }
            else if(btn.getId()==multiply.getId())
            {
                calculationType = 3;
            }
            else if(btn.getId()==divider.getId())
            {
                calculationType = 4;
            }
        }
    }
    //处理点击选择计算类型页面的确定按钮，并把选择的计算类型传到MainActivity
    class  oklistener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent intent = getIntent();
            intent.putExtra("type1",calculationType);//type1是键，第二个参数是要传的值
            Bundle bundle = new Bundle();//bundle传值
            intent.putExtras(bundle);
            //参1是向上一个活动返回的处理结果，参2是带数据的intent
            CalculationTypeActivity.this.setResult(resultCode,intent);
            CalculationTypeActivity.this.finish();//销毁当前活动，使MainActiviyt重新可见
        }
    }




}
