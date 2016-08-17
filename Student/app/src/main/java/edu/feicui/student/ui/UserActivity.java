package edu.feicui.student.ui;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.feicui.student.R;
import edu.feicui.student.adapter.StudentAdapter;

public class UserActivity extends AppCompatActivity {


    private Toolbar user_toolbar;

    private RecyclerView rv_student;

    private DrawerLayout user_dl;
    private ActionBarDrawerToggle drawerToggle;

    private StudentAdapter studentAdapter;

    private List<String> ageList;
    private List<String> nameList;
    private List<String> idList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        user_toolbar = (Toolbar) findViewById(R.id.user_toolbar);
        user_toolbar.setTitle("学生管理");
        setSupportActionBar(user_toolbar);
        user_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        nameList = new ArrayList<String>() {};
        idList = new ArrayList<String>() {};
        ageList = new ArrayList<String>() {};

        user_dl = (DrawerLayout) findViewById(R.id.user_dl);
        drawerToggle = new ActionBarDrawerToggle(this, user_dl, user_toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerToggle.syncState();
        user_dl.addDrawerListener(drawerToggle);

        rv_student = (RecyclerView) findViewById(R.id.rv_student);
        rv_student.setLayoutManager(new LinearLayoutManager(this));
        //设置Item增加、移除动画
        rv_student.setItemAnimator(new DefaultItemAnimator());

        studentAdapter = new StudentAdapter(ageList, nameList, idList, this);
        rv_student.setAdapter(studentAdapter);
        studentAdapter.setRecyclerViewClickListener(new StudentAdapter.RecyclerViewClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Toast.makeText(UserActivity.this, "短按", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnItemLongClick(View view, int position) {
                Toast.makeText(UserActivity.this, "长按", Toast.LENGTH_SHORT).show();
                studentAdapter.remove(position);
            }
        });
    }
}
