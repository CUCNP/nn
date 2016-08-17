package edu.feicui.student.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by DELL on 2016/6/29.
 */
public class UserExpress {

    private Context context;
    private SQLiteDatabase db;


    public UserExpress(Context context){
        this.context = context;
        DBUser dbUser = new DBUser(this.context);
        db = dbUser.getReadableDatabase();
    }

    public boolean checkUser(UserInfo userInfo){
        String username = userInfo.getUsername();
        Cursor cursor = db.rawQuery("select * from user where username=?",new String[]{username});
        return cursor.moveToNext();
    }

    public void addUser(UserInfo userInfo){
        ContentValues cv = new ContentValues();
        cv.put("username",userInfo.getUsername());
        cv.put("password",userInfo.getPassword());
        cv.put("power",userInfo.getPower());
        db.insert("user",null,cv);
    }

    public UserInfo PpUser(String name,String password){
        UserInfo userInfo=null;
        Cursor c=db.rawQuery("select * from user where username=?", new String[]{name});
        if(c!=null){
            if(c.moveToNext()){
                String newPassword=c.getString(c.getColumnIndex("password"));
                String power=c.getString(c.getColumnIndex("power"));
                if(newPassword.equals(password)){
                    userInfo=new UserInfo(name, newPassword, power);
                }
            }
        }
        return userInfo;
    }
}
