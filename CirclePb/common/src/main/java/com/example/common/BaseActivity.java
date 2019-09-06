package com.example.common;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.common.utils.ActivityManager;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author: li502
 * Date: 2019/5/2 17:45
 * Description：
 * 抽象方法：initView()、initData()、getLayoutId()
 * 1、跳到指定Activity
 * 2、统一管理Activity
 * 3、Base标题栏
 * 4、监听返回按钮
 * 5、弹土司
 * 6、权限检查
 */
public abstract class BaseActivity extends AppCompatActivity {

    private Intent mIntent;
    private Unbinder mBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mBinder = ButterKnife.bind(this);
        //        setContentView(R.layout.activity_base);
        //        setLayout();
        mIntent = new Intent();
        ActivityManager.getInstance().addActivity(this);
        initView();
        initData();
    }

    protected abstract void initData();

    protected abstract int getLayoutId();

    protected abstract void initView();

    public void setLayout() {
//        FrameLayout frameLayout = findViewById(R.id.content_view);
//        View view = LayoutInflater.from(this).inflate(getLayoutId(), null, false);
//        frameLayout.addView(view);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().removeActivity(this);
        if (mBinder!=null){
            mBinder.unbind();
        }
    }

    // 设置返回按钮的监听事件
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        // 判断Activity是不是最后一个
        if (isTaskRoot()) {
            // 监听返回键，点击两次退出程序
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
                if ((System.currentTimeMillis() - exitTime) > 1000) {
                    Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_LONG).show();
                    exitTime = System.currentTimeMillis();
                } else {
                    ActivityManager.getInstance().exitSystem();
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    public void startActivity(Class cls) {
        mIntent.setClass(this, cls);
        startActivity(mIntent);
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
    }

    public void startActivityFinish(Class cls) {
        mIntent.setClass(this, cls);
        startActivity(mIntent);
        finish();
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
    }

    public void toast(String text) {
        if (text != null) {
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        }
    }

    public void resetStatusViewHeight() {

//        View basestatus_view = findViewById(R.id.basestatus_view);
//        int statusheight = getStatusBarHeight();
//        if (statusheight != -1) {
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusheight);
//            basestatus_view.setLayoutParams(params);
//        }

    }

    /** 获取当前设备状态栏高度 */
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    /** 权限检查方法，false代表没有该权限，ture代表有该权限 */
    public boolean hasPermission(String... permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /** 权限请求方法 */
    public void requestPermission(int code, String... permissions) {
        ActivityCompat.requestPermissions(this, permissions, code);
    }

    /**
     * 处理请求权限结果事件
     *
     * @param requestCode  请求码
     * @param permissions  权限组
     * @param grantResults 结果集
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doRequestPermissionsResult(requestCode, grantResults);
    }

    /**
     * 处理请求权限结果事件
     *
     * @param requestCode  请求码
     * @param grantResults 结果集
     */
    public void doRequestPermissionsResult(int requestCode, @NonNull int[] grantResults) {

    }


}