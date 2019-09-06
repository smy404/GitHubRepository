package com.example.myrepository.mvp.base.mvp;

import com.example.myrepository.mvp.model.DataModel;

public class BasePresenter<T extends BaseView> implements IPresenter<T> {

    protected T mView;
    protected DataModel mModel;

    public BasePresenter() {

        mModel = new DataModel();
    }

    @Override
    public void attachView(T view) {
        mView = view;
    }

    @Override
    public boolean isAttachView() {
        return false;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void subscribeEvent() {

    }

    @Override
    public boolean getNightStyleState() {
        return false;
    }

    @Override
    public boolean getNoImgStyleState() {
        return false;
    }

    @Override
    public boolean getAutoCacheState() {
        return false;
    }
}
