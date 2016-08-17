package edu.feicui.student.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by DELL on 2016/6/28.
 */
public class DBStudent extends SQLiteOpenHelper {
//  数据库名称
    private final static String DBNAME_STUDENT = "student.db";
//  数据库版本号
    private final static int DBVERSION_STUDENT = 1;
//  构造器
    public DBStudent(Context context) {
        super(context, DBNAME_STUDENT,null,DBVERSION_STUDENT);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table student(id text,name text,age text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
