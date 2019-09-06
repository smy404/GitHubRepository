package com.example.myrepository.mvp.ui.activity;


import com.example.myrepository.mvp.base.mvp.BasePresenter;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    public MainPresenter() {
    }

    @Override
    public void checkVersion(String currentVersion) {

    }

    @Override
    public void setNavCurrentItem(int position) {

    }

    @Override
    public int getNavCurrentItem() {
        return 0;
    }
}
