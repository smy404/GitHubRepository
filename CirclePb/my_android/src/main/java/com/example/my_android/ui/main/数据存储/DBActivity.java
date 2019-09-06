package com.example.my_android.ui.main.数据存储;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.my_android.R;

public class DBActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);


    }

    // 点击"Create DB"按键时,创建test.db
    public void onClickCreateDB(View v) {
        MyDBHelper dbHelper = new MyDBHelper(this);
        // 尝试去连接数据库文件, 如果此文件不存在, 自动创建, 并调用 MyDBHelper的onCreate()
        dbHelper.getReadableDatabase();
    }

    private long insertid = 0;

    // 点击"Inert"按键时
    public void onClickInsert(View v) {
        // 准备数据
        String name = "Tom3";
        int age = 15;

        // 创建一个数据库操作的帮助类对象
        MyDBHelper dbHelper = new MyDBHelper(this);
        // 得到一个数据库文件的连接对象
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        // 执行一个插入的sql
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("age", age);

        insertid = database.insert("person", null, values);
    }

    public void onClickUpdate(View v) {
        MyDBHelper dbHelper = new MyDBHelper(this);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", "JACK");
        values.put("age", 23);
        int updateCount = database.update(
                "person",
                values,
                "_id=?",
                new String[]{insertid + ""});
    }

    public void onClickDelete(View v) {
        MyDBHelper dbHelper = new MyDBHelper(this);
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        int deleteCount = database.delete(      //删除_id=3的记录
                "person",
                "_id=?",
                new String[]{insertid + ""});
    }

    public void onClickQuery(View v) {
        MyDBHelper dbHelper = new MyDBHelper(this);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        // 查询所有的记录
        Cursor cursor = database.query(
                "person",
                null,
                null,
                null,
                null,
                null,
                null);

        while (cursor.moveToNext()) {
            int _id = cursor.getInt(cursor.getColumnIndex("_id"));          // _id
            String name = cursor.getString(cursor.getColumnIndex("name"));  // name
            int age = cursor.getInt(cursor.getColumnIndex("age"));          // age
            Log.e("TAG", _id + "_" + name + "_" + age);
        }
        cursor.close();
    }

    public void onClickUpdateDB(View v) {
        MyDBHelper dbHelper = new MyDBHelper(this, 5);
        // 尝试去连接数据库文件,如果数据库文件存在,如果传入的数据库版本大于数据库文件的版本, 就会去调用onUpgrade
        dbHelper.getReadableDatabase();
    }

    @Override
    public void onClick(View v) {

    }
}
