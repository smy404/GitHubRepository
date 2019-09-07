package com.smy.httpconnectiontest;
/*
* 本文参考自
* https://blog.csdn.net/wei_zhi/article/details/52972230
* 仅供学习，侵删
* */
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private Button button;
    private ImageView imageView;

    //图片下载地址
    private final String IMAGE_URL = "https://img-blog.csdnimg.cn/20190907225813455.jpeg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2NjI5Mzcz,size_16,color_FFFFFF,t_70";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button =  findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyAsyncTask().execute(IMAGE_URL);
            }
        });
        imageView = findViewById(R.id.imageView);


        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("提示");
        progressDialog.setMessage("正在下载，请稍后...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

    }

    class MyAsyncTask extends AsyncTask<String, Integer, Bitmap> {
        @Override
        protected void onPreExecute() {
            // 显示进度对话框
            progressDialog.show();
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = null;
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            InputStream inputStream = null;
            HttpURLConnection connection = null;
            try {
                //第一步，获取到HttpURLConnection 的实例
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                //第二步，设置一下HTTP 请求所使用的方法GET ，从服务器那里获取数据
                connection.setRequestMethod("GET");
                //第三步，设置连接超时、读取超时
                connection.setConnectTimeout(8000);
                connection.setConnectTimeout(8000);
                int code = connection.getResponseCode();
                if (code == 200) {
                    //第四步，获取到服务器返回的输入流，并进行读取
                    inputStream = connection.getInputStream();
                    long file_len = connection.getContentLength();
                    int len = 0;
                    byte[] data = new byte[1024];
                    int total_len = 0;
                    while ((len = inputStream.read(data)) != -1) {
                        total_len += len;
                        int value = (int) ((total_len / (float) file_len) * 100);
                        //反馈当前任务的执行进度
                        publishProgress(value);
                        outputStream.write(data, 0, len);
                    }
                    byte[] result = outputStream.toByteArray();
                    bitmap = BitmapFactory.decodeByteArray(result, 0, result.length);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //第五步，将这个HTTP 连接关闭掉
                if(connection != null){
                    connection.disconnect();
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return bitmap;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            //设置进度条刻度
            progressDialog.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            //关闭进度条
            progressDialog.dismiss();
            //显示图片
            imageView.setImageBitmap(bitmap);
        }
    }
}