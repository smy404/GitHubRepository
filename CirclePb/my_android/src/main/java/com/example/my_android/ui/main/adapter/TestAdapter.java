package com.example.my_android.ui.main.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.my_android.R;

import java.util.List;

public class TestAdapter  extends BaseQuickAdapter<String, BaseViewHolder> {
    public TestAdapter() {
        super(R.layout.item_test);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv, item);
    }
}
