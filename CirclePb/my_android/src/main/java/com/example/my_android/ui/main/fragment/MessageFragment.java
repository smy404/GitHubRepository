package com.example.my_android.ui.main.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.my_android.R;
import com.example.my_android.ui.main.BaseFragment;
import com.example.my_android.ui.main.adapter.TestAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author: li502
 * Date: 2019/5/2 20:24
 * Description:
 */
public class MessageFragment extends BaseFragment {

    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;
    private TestAdapter mAdapter;

    public static MessageFragment newInstance() {
        return new MessageFragment();
    }

    @Override
    protected void initView() {

        mAdapter = new TestAdapter();
        mRecycleView.setAdapter(mAdapter);
        mRecycleView.setLayoutManager(new LinearLayoutManager(mActivity));
    }

    @Override
    protected void loadData() {

        List<String> list = new ArrayList<>();
        list.add("小明");
        list.add("小线");
        list.add("小有");
        list.add("小国");
        mAdapter.setNewData(list);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
    }


}