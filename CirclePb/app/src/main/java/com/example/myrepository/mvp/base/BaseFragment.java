package com.example.myrepository.mvp.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myrepository.mvp.base.mvp.BaseView;
import com.orhanobut.logger.Logger;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment implements BaseView {
    private Unbinder mBinder;
    protected Activity mActivity;

    protected abstract void inject();
    protected abstract void initView();//初始化控件
    protected abstract void loadData();//加载数据
    protected abstract int getLayoutId();//获取Fragment的布局Id


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        mBinder = ButterKnife.bind(this, view);
        inject();
        initView();
        Logger.d("hello"+this,getClass().getSimpleName());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        loadData();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mBinder != null && mBinder != Unbinder.EMPTY){
            mBinder.unbind();
            mBinder = null;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
//        RefWatcher refWatcher = App.getRefWatcher(mActivity);
//        refWatcher.watch(this);
    }


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
}
