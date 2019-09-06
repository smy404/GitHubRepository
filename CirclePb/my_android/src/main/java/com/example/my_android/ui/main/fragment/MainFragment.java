package com.example.my_android.ui.main.fragment;


import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.my_android.MainActivity;
import com.example.my_android.R;
import com.example.my_android.ui.main.BaseFragment;
import com.example.my_android.ui.main.EmptyActivity;
import com.example.my_android.ui.main.EventBus.EventBusActivity;
import com.example.my_android.ui.main.adapter.MainAdapter;
import com.example.my_android.ui.main.数据存储.DataStorageActivity;
import com.example.my_android.ui.main.数据存储.LoginActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class MainFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener {


    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    private MainAdapter mAdapter;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    protected void initView() {
        mAdapter = new MainAdapter(R.layout.item_main);
        mRecycleView.setAdapter(mAdapter);
        mRecycleView.setLayoutManager(new LinearLayoutManager(mActivity));
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    protected void loadData() {
        List<String> list = new ArrayList<>();
        list.add("EventBus");
        list.add("BaseToolbar");
        list.add("数据存储");
        list.add("XX");
        mAdapter.setNewData(list);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_fragment;
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        String o = (String) adapter.getData().get(position);
        Toast.makeText(mActivity, "" + o, Toast.LENGTH_SHORT).show();

        switch (position) {
            case 0:
                ((MainActivity) mActivity).startActivity(EventBusActivity.class);
                break;
            case 1:
                ((MainActivity) mActivity).startActivity(EmptyActivity.class);
                break;

            case 2:
                ((MainActivity) mActivity).startActivity(DataStorageActivity.class);
                break;
        }

    }
}
