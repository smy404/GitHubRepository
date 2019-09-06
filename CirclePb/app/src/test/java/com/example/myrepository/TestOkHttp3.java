package com.example.myrepository;


import android.os.Handler;
import android.os.Message;

import org.junit.Test;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TestOkHttp3 {

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            if (msg.what == 0) {

                String obj = (String) msg.obj;

                System.out.print("handleMessage______onResponse: " + obj);

            }
        }
    };

    @Test
    public void testGet() {

        // 创建 OkHttpClient 对象
        OkHttpClient client = new OkHttpClient();

        // 创建 Request 对象
        Request request = new Request.Builder()
                .url("http://httpbin.org/get?id=id")
                .build();

        // OkHttpClient 执行 Request
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.print("onResponse: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
//                System.out.print("onResponse: " + response.body().string());

                Message message = Message.obtain();

                message.what = 0;
                message.obj = string;

                mHandler.sendMessage(message);
            }
        });

        try {
            Response response = client.newCall(request).execute();

//            System.out.print("onResponse: " + response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
