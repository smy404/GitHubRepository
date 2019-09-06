package com.example.my_android.ui.main.EventBus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.my_android.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventBusActivity_3 extends AppCompatActivity {

    private EditText mEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_3);
        mEt = findViewById(R.id.et);

    }


    public void click3(View view) {

        String name = mEt.getText().toString();

        MessageEvent event = new MessageEvent();
        event.setName(name);
        event.setTag(1);

        EventBus.getDefault().post(event);

    }
}
