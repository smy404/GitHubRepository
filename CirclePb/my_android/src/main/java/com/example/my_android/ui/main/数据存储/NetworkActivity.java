package com.example.my_android.ui.main.数据存储;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.my_android.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class NetworkActivity extends AppCompatActivity {

    private String path = "http://192.168.1.100/T4_web/index.jsp";
    private ProgressDialog pd;
    private ImageView iv;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {


            switch (msg.what) {
                case 0:         //失败
                    Toast.makeText(NetworkActivity.this, "连接失败", Toast.LENGTH_SHORT).show();
                    break;

                case 1:         //成功
                    bitmap = (Bitmap) msg.obj;
                    iv.setImageBitmap(bitmap);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        iv = findViewById(R.id.iv);
    }

    // 使用HttpUrlConnection
    public void onClickGet1(View v) {
        // 显示一个提示
        pd = ProgressDialog.show(this, null, "正在加载中...");
        // 启动一个线程去联网请求
        new Thread() {
            public void run() {
                try {
                    // 模拟联网时间较长
                    Thread.sleep(1000);

                    URL url = new URL(path + "?name=tt&age=12");

                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    connection.setRequestMethod("GET");

                    int responseCode = connection.getResponseCode();
                    if (responseCode == 200) {
                        InputStream inputStream = connection.getInputStream();// 得到服务器端返回的流,包含了服务器返回的数据
                        // 将inputStream读取成一个字符串
                        final String result = readToStringFromIs(inputStream);
                        Log.e("TAG", result);
                        // 在UIThread/MainThread显示提示信息
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pd.dismiss();

                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void onClickPOST1(View v) {
        // 显示一个提示
        pd = ProgressDialog.show(this, null, "正在加载中...");
        // 启动一个线程去联网请求
        new Thread() {
            public void run() {
                try {
                    // 模拟联网时间较长
                    Thread.sleep(1000);

                    URL url = new URL(path);
                    String reqBody = "name=tt&age=12";

                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);// 设置可以向服务器端写数据
                    connection.setConnectTimeout(5000);// 设置连接的超时时间

                    // 向服务器端写请求体
                    OutputStream outputStream = connection.getOutputStream();// 得到一个指向服务器端的输出流,
                    // 写请求体
                    outputStream.write(reqBody.getBytes("utf-8"));

                    // 获取服务器端返回的数据
                    int responseCode = connection.getResponseCode();
                    if (responseCode == 200) {
                        InputStream inputStream = connection.getInputStream();// 得到服务器端返回的流,包含了服务器返回的数据
                        // 将inputStream读取成一个字符串
                        final String result = readToStringFromIs(inputStream);
                        Log.e("TAG", result);
                        // 在UIThread/MainThread显示提示信息
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pd.dismiss();

                            }
                        });
                    }
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    private Bitmap bitmap;


    /*
     * 将inputStream读取成一个字符串
     */
    private String readToStringFromIs(InputStream inputStream) throws IOException {
        String result = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = inputStream.read(buffer)) > 0) {
            baos.write(buffer, 0, len);
        }

        result = baos.toString();
        baos.close();
        inputStream.close();

        return result;
    }

    public void onClickGetImageView(View view) {


        new Thread(new Runnable() {
            @Override
            public void run() {
                String path = "https://imgsa.baidu.com/forum/w%3D580/sign=104c2d14f5" +
                        "f2b211e42e8546fa816511/86abcd11728b4710b966b6abcdcec3fdfc032311.jpg";
                URL url;
                HttpURLConnection connection;

                try {

                    url = new URL(path);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    if (connection.getResponseCode() == 200) {          // 如果请求码==200
                        InputStream is = connection.getInputStream();   // 获取服务器的返回输入流
                        bitmap = BitmapFactory.decodeStream(is);        // 把流解码成bitmap对象

                        Message msg = mHandler.obtainMessage();         // 使用Handler获取一个Message对象
                        msg.what = 1;                                   // 给这个消息设置一个标志为1
                        msg.obj = bitmap;                               // 把bitmap对象放到消息里面
                        mHandler.sendMessage(msg);                      // 发送消息

                    } else {                                            // 请求码非200，失败

                        Message msg = mHandler.obtainMessage();         // 把失败消息发送到Handler主线程
                        msg.what = 0;
                        mHandler.sendMessage(msg);
                    }

                    connection.disconnect();                            // 断开连接


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
