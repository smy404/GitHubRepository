package com.example.myrepository.mvp.ui.fragment.project;


import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.common.utils.PermissionsUtils;
import com.example.myrepository.MainActivity;
import com.example.myrepository.R;
import com.example.myrepository.mvp.base.BaseFragment;
import com.example.myrepository.小案例.ui控件使用.RecycleView.欢迎页.SplashActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ProjectFragment extends BaseFragment {

    Unbinder unbinder;

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
        return R.layout.fragment_project;
    }


    @OnClick({R.id.btn_1, R.id.btn_2, R.id.btn_3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                startActivity(new Intent(mActivity, SplashActivity.class));
                break;
            case R.id.btn_2:
                p();
                break;
            case R.id.btn_3:
                break;
        }
    }

    private void p() {

        //两个日历权限和一个数据读写权限
        String[] permissions = new String[]{Manifest.permission.WRITE_CALENDAR, Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        //PermissionsUtils.showSystemSetting = false;//是否支持显示系统设置权限设置窗口跳转
        //这里的this不是上下文，是Activity对象！
        PermissionsUtils.getInstance().chekPermissions(mActivity, permissions, new PermissionsUtils.IPermissionsResult() {
            @Override
            public void passPermissons() {
                Toast.makeText(mActivity, "权限通过，可以做其他事情!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void forbitPermissons() {
                Toast.makeText(mActivity, "权限不通过!", Toast.LENGTH_SHORT).show();
            }
        });


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //就多一个参数this
        PermissionsUtils.getInstance().onRequestPermissionsResult(mActivity, requestCode, permissions, grantResults);
    }
}
