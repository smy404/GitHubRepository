package com.example.myrepository.fragment;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputEditText;


import com.example.myrepository.BaseFragment;
import com.example.myrepository.R;

import butterknife.BindView;
import butterknife.Unbinder;


/**

 */
public class Fragment_3 extends BaseFragment {

    static Fragment_3 mFragment_3;
    @BindView(R.id.sensitive_tiet)
    TextInputEditText mSensitiveTiet;
    Unbinder unbinder;

    public Fragment_3() {
    }

    public static Fragment_3 newInstance() {
        Bundle args = new Bundle();
        mFragment_3 = new Fragment_3();
        mFragment_3.setArguments(args);
        return mFragment_3;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void init() {

//        // 新建一个可以添加属性的文本对象
//        SpannableString ss = new SpannableString("敏感度");
//
//        // 新建一个属性对象,设置文字的大小
//        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(80,true);
//        // 附加属性到文本
//        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        // 设置hint
//        mSensitiveTiet.setHint(new SpannedString(ss)); // 一定要进行转换,否则属性会消失

    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_3;
    }


}
