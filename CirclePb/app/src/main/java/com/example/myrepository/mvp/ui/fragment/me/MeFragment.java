package com.example.myrepository.mvp.ui.fragment.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myrepository.R;
import com.example.myrepository.mvp.base.BaseFragment;
import com.example.myrepository.小案例.ui控件使用.RecycleView.版本更新.VersionUpdateActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MeFragment extends BaseFragment {



    @Override
    protected void inject() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }


    @OnClick(R.id.btn_1)
    public void onViewClicked() {
        startActivity(new Intent(mActivity, VersionUpdateActivity.class));
    }
}
