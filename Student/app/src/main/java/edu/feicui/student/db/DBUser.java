package edu.feicui.student.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by DELL on 2016/6/28.
 */
public class DBUser extends SQLiteOpenHelper {

    private final static String DBNAME_USER = "user.db";
    private final static int DBVERSION_USER = 1;

    public DBUser(Context context) {
        super(context, DBNAME_USER, null, DBVERSION_USER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user(username text,pwd text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
