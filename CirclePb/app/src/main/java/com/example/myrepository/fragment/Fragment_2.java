package com.example.myrepository.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myrepository.BaseFragment;
import com.example.myrepository.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**

 */
public class Fragment_2 extends BaseFragment {

    static Fragment_2 mFragment_2;
    @BindView(R.id.ProgressView)
    ProgressView_1 progressView;

    public Fragment_2() {
        // Required empty public constructor
    }

    public static Fragment_2 newInstance() {
        Bundle args = new Bundle();
        mFragment_2 = new Fragment_2();
        mFragment_2.setArguments(args);
        return mFragment_2;
    }


    @Override
    protected void init() {

//        progressView.setMaxCount(100.0f);
//
//        progressView.setCurrentCount(65);
//        progressView.setScore(value);

        progressView.setCurrentCount(30);

    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_2;
    }


}
