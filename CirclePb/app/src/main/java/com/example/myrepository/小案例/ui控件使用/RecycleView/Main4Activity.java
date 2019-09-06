package com.example.myrepository.小案例.ui控件使用.RecycleView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.myrepository.R;
import com.example.myrepository.小案例.ui控件使用.RecycleView.添加头布局和底布局.WrapRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main4Activity extends AppCompatActivity {

    @BindView(R.id.recycleView)
    WrapRecyclerView recycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        ButterKnife.bind(this);

        init();
    }

    private void init() {




    }
}
