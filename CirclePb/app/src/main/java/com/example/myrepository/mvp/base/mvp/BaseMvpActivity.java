package com.example.myrepository.mvp.base.mvp;

import com.example.myrepository.mvp.base.BaseActivity;

public abstract class BaseMvpActivity<T extends IPresenter> extends BaseActivity {

    protected abstract T getPresenter();
    protected T mPresenter;

    @Override
    protected void initView() {

        mPresenter = getPresenter();
        mPresenter.attachView(this);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(mPresenter != null){
            mPresenter.detachView();
            mPresenter = null;
        }
    }
}
