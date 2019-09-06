package com.example.myrepository.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myrepository.BaseFragment;
import com.example.myrepository.R;
import com.example.myrepository.activity.Main2Activity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**

 */
public class Fragment_11 extends BaseFragment {



    public static Fragment_11 newInstance() {
        Bundle args = new Bundle();
        Fragment_11 fragment = new Fragment_11();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void init() {
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_11;
    }


    @OnClick({R.id.btn_1, R.id.btn_2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                startActivity(Main2Activity.class);
                break;
            case R.id.btn_2:
                break;
        }
    }
}
