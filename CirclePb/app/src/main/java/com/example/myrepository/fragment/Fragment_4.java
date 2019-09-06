package com.example.myrepository.fragment;


import android.os.Bundle;

import com.example.myrepository.BaseFragment;
import com.example.myrepository.R;

/**

 */
public class Fragment_4 extends BaseFragment {

    static  Fragment_4 mFragment_4;

    public Fragment_4() {
        // Required empty public constructor
    }

    public static Fragment_4 newInstance() {
        Bundle args = new Bundle();
        mFragment_4 = new Fragment_4();
        mFragment_4.setArguments(args);
        return mFragment_4;
    }


    @Override
    protected void init() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_4;
    }

}
