package com.example.myrepository.mvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.myrepository.mvp.base.mvp.BaseView;
import com.example.myrepository.utils.ActivityCollector;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {
    private Unbinder mBinder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mBinder = ButterKnife.bind(this);
        ActivityCollector.getInstance().addActivity(this);
        initView();
        initData();

//        startActivity();
    }

    protected abstract int getLayoutId();

    protected abstract void inject(); //注入

    protected abstract void initView(); //初始化布局

    protected abstract void initData(); //初始化数据

    @Override
    public void setStatusBarColor() {

    }

    @Override
    public void showNormalView() {

    }

    @Override
    public void showErrorView() {

    }

    @Override
    public void reLoad() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void showNightStyle(boolean isNight) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.getInstance().removeActivity(this);
        if(mBinder != null && mBinder != mBinder.EMPTY){
            mBinder.unbind();
            mBinder = null;
        }
    }
}
