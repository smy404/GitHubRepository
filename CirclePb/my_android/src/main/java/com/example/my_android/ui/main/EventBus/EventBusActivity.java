package com.example.my_android.ui.main.EventBus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.my_android.MainActivity;
import com.example.my_android.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventBusActivity extends AppCompatActivity {

    private EditText mEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);

        mEt = findViewById(R.id.et);


    }

    public void click1(View view) {

        startActivity(new Intent(this,EventBusActivity_1.class));
    }


    public void click3(View view) {

        MessageEvent event = new MessageEvent();

        String name = mEt.getText().toString();
        event.setName(name);

        EventBus.getDefault().postSticky(event);
    }


}
