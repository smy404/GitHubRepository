package com.example.myrepository.fragment;


import android.os.Bundle;

import com.example.myrepository.BaseFragment;
import com.example.myrepository.R;

/**

 */
public class Fragment_6 extends BaseFragment {

    static Fragment_6 mFragment_6;

    public Fragment_6() {
        // Required empty public constructor
    }

    public static Fragment_6 newInstance() {
        Bundle args = new Bundle();
        mFragment_6 = new Fragment_6();
        mFragment_6.setArguments(args);
        return mFragment_6;
    }


    @Override
    protected void init() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_6;
    }

}
