package com.example.common.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.common.R;

public class BaseToolbarActivity extends AppCompatActivity {

    private TextView commonTitleTv;
    private Toolbar commonToolbar;
    private RelativeLayout content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_base);

        initView();


    }

    private void initView() {

        commonTitleTv = findViewById(R.id.common_title_tv);
        commonToolbar = findViewById(R.id.common_toolbar);
        content = findViewById(R.id.content);


        setSupportActionBar(commonToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


    }

    /** 如果子类不需要原来的Toolbar，那就重新设置一个自己的Toolbar */
    private void setToolbar(int layout) {
        hideToolBar();
        commonToolbar = content.findViewById(layout);
        setSupportActionBar(commonToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);  //设置actionBar的标题是否显示
    }

    /** 隐藏Toolbar */
    private void hideToolBar() {
        commonToolbar.setVisibility(View.GONE);
    }

    public void setToolBarMenuOnclick(Toolbar.OnMenuItemClickListener onclick) {
        commonToolbar.setOnMenuItemClickListener(onclick);
    }

    public void setBackArrow() {
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_back);
        //给ToolBar设置左侧的图标
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        // 给左上角图标的左边加上一个返回的图标 。对应ActionBar.DISPLAY_HOME_AS_UP
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //设置返回按钮的点击事件
        commonToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    /** 设置Toolbar下面显示内容的区域 */
    public void setContentLayout(int layoutId) {

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View contentView = inflater.inflate(layoutId, null);

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        content.addView(contentView, params);
    }

    public void setTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            commonTitleTv.setText(title);
        }
    }


    public void setTitle(int resId) {
        commonTitleTv.setText(resId);
    }
}
