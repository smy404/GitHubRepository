package com.example.my_android.ui.main.数据存储;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.common.BaseActivity;
import com.example.common.utils.FileUtils;
import com.example.my_android.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import butterknife.BindView;

/**
 *
 */
public class LoginActivity extends BaseActivity {


    @BindView(R.id.email)
    AutoCompleteTextView email;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.email_sign_in_button)
    Button emailSignInButton;
    @BindView(R.id.tv_content)
    TextView tvContent;

    private String mEmail;
    private String mPassword;


    @Override
    protected void initData() {

        Button viewById = findViewById(R.id.email_sign_in_button);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writerData();
                readerData();
            }
        });

    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

    }


    private void writerData() {

        mEmail = email.getText().toString();
        mPassword = password.getText().toString();


        FileUtils.write(this, "File_data_Utils", mEmail);


        //        BufferedWriter bufferedWriter = null;
        //        FileOutputStream fileOutputStream = null;
        //
        //        try {
        //
        //            fileOutputStream = openFileOutput("File_data", Context.MODE_PRIVATE);
        //            bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
        //            bufferedWriter.write(mEmail);
        //
        //        } catch (IOException e) {
        //            e.printStackTrace();
        //        } finally {
        //            if (bufferedWriter != null) {
        //                try {
        //                    bufferedWriter.close();
        //                    fileOutputStream.close();
        //                } catch (IOException e) {
        //                    e.printStackTrace();
        //                }
        //            }
        //        }


        Intent intent = new Intent();
        intent.putExtra("key", mEmail);

        setResult(1, intent);

        finish();
    }

    private void readerData() {

        try {
            String file_data = FileUtils.read("File_data_Utils");
            tvContent.setText(file_data);


        } catch (IOException e) {
            e.printStackTrace();
        }

        //        StringBuilder sb = new StringBuilder();
        //
        //        BufferedReader bufferedReader = null;
        //        FileInputStream fileInputStream = null;
        //
        //        try {
        //
        //            fileInputStream = openFileInput("File_data");
        //            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        //
        //            String line;
        //
        //            while ((line = bufferedReader.readLine()) != null) {
        //                sb.append(line);
        //            }
        //
        //        } catch (IOException e) {
        //            e.printStackTrace();
        //        } finally {
        //
        //            try {
        //                if (bufferedReader != null) {
        //                    bufferedReader.close();
        //                    fileInputStream.close();
        //                }
        //            } catch (IOException e) {
        //                e.printStackTrace();
        //            }
        //
        //
        //        }
        //
        //        String data = sb.toString();
        //
        //        tvContent.setText(data);

    }


}

