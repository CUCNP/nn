package edu.feicui.student.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import edu.feicui.student.R;
import edu.feicui.student.db.SharprefaceManager;
import edu.feicui.student.db.UserExpress;
import edu.feicui.student.db.UserInfo;
import edu.feicui.student.ui.UserActivity;

/**
 * Created by DELL on 2016/8/17.
 */
public class RegisterFragment extends Fragment{

    private EditText et_register_userName,et_register_passWord;
    private RadioButton rb_register_teacher,rb_register_student;
    private Button btn_register_confirm;

    private Spinner register_spinner;
    private String powername;

    private SharprefaceManager spfmaManager;
    private UserExpress userexpress;
    private boolean isfirst;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container,false);

        et_register_userName = (EditText) view.findViewById(R.id.et_register_userName);
        et_register_passWord = (EditText) view.findViewById(R.id.et_register_passWord);
        rb_register_teacher = (RadioButton) view.findViewById(R.id.rb_register_teacher);
        rb_register_student = (RadioButton) view.findViewById(R.id.rb_register_student);
        btn_register_confirm = (Button) view.findViewById(R.id.btn_register_confirm);
        register_spinner = (Spinner)view.findViewById(R.id.register_spinner);

        btn_register_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
                Intent intent = new Intent(getActivity(),UserActivity.class);
                startActivity(intent);
            }
        });

        spfmaManager=new SharprefaceManager(getActivity());
        userexpress=new UserExpress(getActivity());
        if(spfmaManager.getLogin().getUsername().equals("no")){
            isfirst=true;
        }else{
            isfirst=false;
        }
        return view;
    }

    private void register() {
        String name=et_register_userName.getText().toString();
        String password=et_register_passWord.getText().toString();
        powername=register_spinner.getSelectedItem().toString();
        UserInfo userInfo=new UserInfo(name, password, powername);
        if(!userexpress.checkUser(userInfo)){
            userexpress.addUser(userInfo);
            spfmaManager.SaveLogin(userInfo);
            Intent intent = new Intent(getActivity(),UserActivity.class);
            startActivity(intent);
        }
    }
}
