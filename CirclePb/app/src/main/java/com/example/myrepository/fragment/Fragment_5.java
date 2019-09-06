package com.example.myrepository.fragment;


import android.os.Bundle;

import com.example.myrepository.BaseFragment;
import com.example.myrepository.R;

/**

 */
public class Fragment_5 extends BaseFragment {

    static  Fragment_5 mFragment_5;

    public Fragment_5() {
        // Required empty public constructor
    }

    public static Fragment_5 newInstance() {
        Bundle args = new Bundle();
        mFragment_5 = new Fragment_5();
        mFragment_5.setArguments(args);
        return mFragment_5;
    }


    @Override
    protected void init() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_5;
    }

}
