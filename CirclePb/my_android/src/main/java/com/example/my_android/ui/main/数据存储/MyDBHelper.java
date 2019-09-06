package com.example.my_android.ui.main.数据存储;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDBHelper extends SQLiteOpenHelper {

    private static int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "test.db";

    public MyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public MyDBHelper(Context context, int version) {
        super(context, DATABASE_NAME, null, version);
        DATABASE_VERSION = version;
    }

    //这个方法在创建数据库文件时会调用, 只执行一次
    @Override
    public void onCreate(SQLiteDatabase db) {
        //执行创建表的sql语句
        db.execSQL("CREATE TABLE person(_id INTEGER PRIMARY KEY autoincrement not null, name varchar, age Integer)");
    }

    //在更新版本时会调用
    //完成添加表或修改表结构的操作
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


}
