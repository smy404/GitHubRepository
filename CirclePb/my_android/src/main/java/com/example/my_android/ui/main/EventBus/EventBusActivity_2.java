package com.example.my_android.ui.main.EventBus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.my_android.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventBusActivity_2 extends AppCompatActivity {

    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_2);
        mTv = findViewById(R.id.tv);

        //注册成为订阅者
        EventBus.getDefault().register(this);
    }

    //订阅方法，当接收到事件的时候，会调用该方法
    @Subscribe(sticky = true)
    public void onEvent(MessageEvent messageEvent) {
        if (messageEvent.getTag()==1){
            mTv.setText(messageEvent.getName());
            Toast.makeText(this, messageEvent.getName(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除注册
        EventBus.getDefault().unregister(this);
    }

    public void click1(View view) {

        startActivity(new Intent(this,EventBusActivity_3.class));
    }
}
