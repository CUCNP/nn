package edu.feicui.student.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import edu.feicui.student.R;
import edu.feicui.student.fragment.LoginFragment;
import edu.feicui.student.fragment.RegisterFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar main_toolbar;

    private LoginFragment loginFragment;
    private RegisterFragment registerFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private TextView tv_register,tv_login;

    private int position = -1;

    TextView[] btTextView = new TextView[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        main_toolbar.setTitle("学生管理");

        tv_login = (TextView) findViewById(R.id.tv_login);
        tv_register = (TextView) findViewById(R.id.tv_register);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        loginFragment = new LoginFragment();
        registerFragment = new RegisterFragment();
        fragmentTransaction.add(R.id.ll_fragment, loginFragment).add(R.id.ll_fragment, registerFragment);
        fragmentTransaction.hide(registerFragment).show(loginFragment);
        fragmentTransaction.commit();

        setBottom();
        changeBottom(position);
        tv_login.setOnClickListener(this);
        tv_register.setOnClickListener(this);

        Spinner spinner = (Spinner) findViewById(R.id.register_spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                String[] power = getResources().getStringArray(R.array.power);
                Toast.makeText(MainActivity.this, "你点击的是:"+power[pos], Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
    }

    @Override
    public void onClick(View view) {
        fragmentTransaction = fragmentManager.beginTransaction();
        switch (view.getId()) {
            case R.id.tv_login:
                position = 0;
                fragmentTransaction.hide(registerFragment).show(loginFragment);
                break;
            case R.id.tv_register:
                position = 1;
                fragmentTransaction.hide(loginFragment).show(registerFragment);
        }
        fragmentTransaction.commit();

    }

    public void changeBottom(int position) {
        for (int i = 0; i < btTextView.length; i++) {
            if (i == position) {
                btTextView[i].setTextColor(android.graphics.Color.parseColor("#3047ba"));
            } else {
                btTextView[i].setTextColor(android.graphics.Color.parseColor("#000000"));
            }
        }
    }

    public void setBottom() {
        btTextView[0] = tv_login;
        btTextView[1] = tv_register;
    }
}