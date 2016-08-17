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
import android.widget.Spinner;
import android.widget.Toast;

import edu.feicui.student.R;
import edu.feicui.student.db.SharprefaceManager;
import edu.feicui.student.db.UserExpress;
import edu.feicui.student.db.UserInfo;
import edu.feicui.student.ui.UserActivity;

/**
 * Created by DELL on 2016/8/17.
 */
public class LoginFragment extends Fragment{

    private EditText et_login_userName,et_login_passWord;
    private Button btn_login_confirm;

    private SharprefaceManager spfmaManager;
    private UserExpress userexpress;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container,false);

        et_login_userName = (EditText) view.findViewById(R.id.et_login_userName);
        et_login_passWord = (EditText) view.findViewById(R.id.et_login_passWord);
        btn_login_confirm = (Button) view.findViewById(R.id.btn_login_confirm);

        btn_login_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
                Intent intent = new Intent(getActivity(),UserActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void login() {
        String name=et_login_userName.getText().toString();
        String password=et_login_passWord.getText().toString();
        UserInfo userInfo=userexpress.PpUser(name, password);
        if(userexpress.checkUser(userInfo)){
            spfmaManager.SaveLogin(userInfo);
            Intent intent = new Intent(getActivity(), UserActivity.class);
            startActivity(intent);
        }
    }
}
