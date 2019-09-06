package com.example.myrepository.小案例.ui控件使用.RecycleView.版本更新;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.icu.util.VersionInfo;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationSet;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myrepository.Constant;
import com.example.myrepository.R;
import com.example.myrepository.mvp.http.OkHttpUtils;
import com.example.myrepository.utils.LogUtil;
import com.example.myrepository.小案例.ui控件使用.RecycleView.欢迎页.SplashActivity;
import com.google.gson.Gson;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class VersionUpdateActivity extends AppCompatActivity {
    private static final int LOADINGMAIN = 0;//代表进入主界面
    private static final int SHOWDIALOG = 1;

    private RelativeLayout rl_splash_root;//根布局
    private TextView tv_splash_version_name;//版本名称
    private TextView tv_splash_version_code;//版本号
    private AnimationSet animationSet;
    private VersionInfoBean versionInfo;
    private long startTime;
    private Message msg;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case LOADINGMAIN:
                    goHome();
                    break;
                case SHOWDIALOG:
                    showISDialog();
                    break;
                default:
                    switch (msg.what) {
                        case 1001:
                            Toast.makeText(getApplicationContext(), "服务器错误", Toast.LENGTH_LONG).show();
                            break;
                        case 1002:
                            Toast.makeText(getApplicationContext(), "IO异常", Toast.LENGTH_LONG).show();
                            break;
                        case 1003:
                            Toast.makeText(getApplicationContext(), "JSON解析粗我", Toast.LENGTH_LONG).show();
                            break;
                        case 1004:
                            Toast.makeText(getApplicationContext(), "包名没有找到", Toast.LENGTH_LONG).show();
                            break;
                    }
            }
        }
    };

    private void showISDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("升级提醒");
        builder.setMessage(versionInfo.getDesc());
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                goHome();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //再次联网断点下载apk
                downLoadNewAPK();
            }
        });

        builder.show();

    }

    private void downLoadNewAPK() {


        OkHttpClient client = new OkHttpClient();

        FormBody.Builder builder = new FormBody.Builder();

        final Request request = new Request.Builder()
                .url(versionInfo.getDownloadApk())
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {

                InputStream is = null;//输入流
                FileOutputStream fos = null;//输出流

                is = response.body().byteStream();
                long total = response.body().contentLength();
                LogUtil.d("total------>" + total);
                File file = new File(Environment.getExternalStorageDirectory(), "Earn.apk");// 设置路径
                fos = new FileOutputStream(file);
                byte[] buf = new byte[1024];
                int ch = -1;
                int sum = 0;
                while ((ch = is.read(buf)) != -1) {
                    fos.write(buf, 0, ch);
                    sum += ch;
                    int progress = (int) (sum * 1.0f / total * 100);
                    LogUtil.d("下载进度process------>" + progress);
                    // view.downLoading(process);       //这里就是关键的实时更新进度了！
                }

                fos.flush();
                fos.close();
                is.close();
            }

            @Override
            public void onFailure(Call call, IOException e) {
                LogUtil.d("网络失败------>" + e.getMessage());
            }
        });


    }

    private void goHome() {


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version_update);
    }

    //在子线程中检查是否需要更新
    public void checkVersion(View view) {
        new Thread() {
            public void run() {
                // 请求网络获取下载apk
                readURLData();
            }
        }.start();
    }

    private void readURLData() {

        msg = Message.obtain();
        try {
            //1 获得url路径
            URL url = new URL("http://10.0.216.174:8080/version/info.json");
            //            URL url = new URL("https://wanandroid.com/wxarticle/chapters/json");
            //            URL url = new URL(Constant.URL.UPDATEURL);
            // 2得到一个链接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 3 设置一些属性
            conn.setRequestMethod("GET");
            // 设置超时时间
            conn.setConnectTimeout(5000);
            //得到请求码
            int code = conn.getResponseCode();
            if (code == 200) {
                InputStream inputStream = conn.getInputStream();    // 获得服务端返回的字节流
                String jsonString = StreamToString(inputStream);    // 把字节流转换为字符串

                //解析Json，得到数据对象
                Gson gson = new Gson();
                versionInfo = gson.fromJson(jsonString, VersionInfoBean.class);

                // 判断版本号一致性，如果相等，就设置一个标志发送到Handler，可以进入到主页
                if (Integer.parseInt(versionInfo.getVersion()) == getVersionCode()) {
                    msg.what = LOADINGMAIN;
                } else {
                    msg.what = SHOWDIALOG;
                }

                // 返回码是非200，网络请求错误，把响应码发送到Handler说明处理
            } else {
                msg.what = code;
            }

            // 如果出现异常，就把异常代码发送到主线程处理
        } catch (MalformedURLException e) {
            msg.what = 1001;
            e.printStackTrace();
        } catch (IOException e) {
            msg.what = 1002;
            e.printStackTrace();
        } catch (PackageManager.NameNotFoundException e) {
            msg.what = 1004;
            e.printStackTrace();
        } finally {
            // 从访问网络到进入主页要休眠2秒，因为要等动画执行完毕（我这里没有设置动画，可以不用）
            long endTime = System.currentTimeMillis();
            if (endTime - startTime < 2000) {
                SystemClock.sleep(2000 - (endTime - startTime));
            }
            handler.sendMessage(msg);// 发送上面的消息
        }
    }

    /** 把流转成String */
    private String StreamToString(InputStream is) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line = reader.readLine();
        while (line != null) {
            builder.append(line);
            line = reader.readLine();
        }
        return builder + "";
    }

    /** 获取版本名 */
    private String getVersionName() throws PackageManager.NameNotFoundException {
        PackageManager pm = this.getPackageManager();
        PackageInfo info = pm.getPackageInfo(this.getPackageName(), 0);
        return info.versionName;
    }

    /** 获取版本号 */
    private int getVersionCode() throws PackageManager.NameNotFoundException {
        PackageManager pm = this.getPackageManager();
        PackageInfo info = pm.getPackageInfo(this.getPackageName(), 0);
        return info.versionCode;
    }
}

