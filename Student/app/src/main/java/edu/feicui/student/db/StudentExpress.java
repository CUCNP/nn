package edu.feicui.student.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2016/6/28.
 */
public class StudentExpress {

    private SQLiteDatabase db;
    private Context context;
//  构造函数，初始化studentExpress
    public StudentExpress(Context context){
//        赋值
        this.context = context;
//        对象实例化
        DBStudent dbStudent = new DBStudent(this.context);
//        读取数据
        db = dbStudent.getWritableDatabase();
    }
//  添加数据方法
    public void addStudent(StudentInfo studentInfo){
//        文本内容实例化
        ContentValues cv = new ContentValues();
//        获取添加数据
        cv.put("sex",studentInfo.getSex());
        cv.put("name",studentInfo.getName());
        cv.put("age",studentInfo.getAge());
//        插入获取的数据
        db.insert("student",null,cv);
    }
//  查询数据方法
    public boolean checkStudent(StudentInfo studentInfo){
//        获取id
        String id = studentInfo.getSex();
//        Cursor：游标，查询id
        Cursor cursor = db.rawQuery("select * from student where id=?",new String[]{id});
//        移动到下一位
        return cursor.moveToNext();
    }
//  修改数据方法
    public void updateStudent(StudentInfo studentInfo,String id){
        ContentValues cv = new ContentValues();
        cv.put("sex",studentInfo.getSex());
        cv.put("name",studentInfo.getName());
        cv.put("age",studentInfo.getAge());
        db.update("student",cv,"id=?",new String[]{id});
    }

    public void deleteStudent(StudentInfo studentInfo){
        String name = studentInfo.getName();
        db.delete("student","id=?",new String[]{name});
    }
//  List<Student>：返回值
    public List<StudentInfo> getAllStudent(){
//        实例化List<Student>
        List<StudentInfo> list = new ArrayList<StudentInfo>();

        Cursor cursor = db.rawQuery("select * from student",null);
//        判断游标是否为空
        if (cursor!=null){
            while (cursor.moveToNext()){
                String sex = cursor.getString(cursor.getColumnIndex("sex"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String age = cursor.getString(cursor.getColumnIndex("age"));
                StudentInfo studentInfo = new StudentInfo(sex,name,age);
                list.add(studentInfo);
            }
        }
        return list;
    }

    public StudentInfo getStudent(String num){
        StudentInfo studentInfo=null;
        Cursor c=db.rawQuery("select * from  student where name=?", new String[]{num});
        if(c!=null){
            if(c.moveToNext()){
                String name=c.getString(c.getColumnIndex("name"));
                String sex=c.getString(c.getColumnIndex("sex"));
                String age=c.getString(c.getColumnIndex("age"));
                studentInfo=new StudentInfo(name, age, sex);
            }
        }
        return studentInfo;
    }
}
