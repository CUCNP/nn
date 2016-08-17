package edu.feicui.student.db;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by DELL on 2016/8/17.
 */
public class SharprefaceManager {

    Context context;

    public SharprefaceManager(Context context) {
        super();
        this.context = context;
    }
    public  void SaveLogin(UserInfo userInfo){
        SharedPreferences spf=context.getSharedPreferences("loginmsg.txt", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed=spf.edit();
        ed.putString("name", userInfo.getUsername());
        ed.putString("password", userInfo.getPassword());
        ed.putString("power", userInfo.getPower());
        ed.commit();

    }
    public UserInfo getLogin(){

        SharedPreferences spf=context.getSharedPreferences("loginmsg.txt", Context.MODE_PRIVATE);
        String name=spf.getString("name", "no");
        String password=spf.getString("password", "no");
        String power=spf.getString("power", "no");
        UserInfo userInfo=new UserInfo(name, password, power);
        return userInfo;
    }
}
