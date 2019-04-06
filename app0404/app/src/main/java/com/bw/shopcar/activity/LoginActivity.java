package com.bw.shopcar.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.shopcar.R;
import com.bw.shopcar.bean.LoginBean;
import com.bw.shopcar.mvp.model.UserModelImp;
import com.bw.shopcar.mvp.presenter.UserPresenterImp;
import com.bw.shopcar.mvp.view.UserView;
import com.bw.shopcar.view.PagerView;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, UserView {

    private TextView tv_back;
    private EditText edit_phone;
    private EditText edit_pwd;
    private TextView tv_regis;
    private Button btn_login;
    private SharedPreferences sp;
    private UserPresenterImp userPresenterImp;
    private String loginName;
    private String loginPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //初始化控件
        tv_back = findViewById(R.id.tv_back);
        edit_phone = findViewById(R.id.edit_phone);
        edit_pwd = findViewById(R.id.edit_pwd);
        tv_regis = findViewById(R.id.tv_regis);
        btn_login = findViewById(R.id.btn_login);

        //监听
        tv_back.setOnClickListener(this);
        tv_regis.setOnClickListener(this);
        btn_login.setOnClickListener(this);

        sp = getSharedPreferences("user", MODE_PRIVATE);
        String phone = sp.getString("regisPhone", null);
        edit_phone.setText(phone);

        userPresenterImp = new UserPresenterImp(new UserModelImp(),this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_regis:
                //跳转到注册页面
                Intent intent = new Intent(LoginActivity.this, RegisActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_login:
                doLogin();
                break;
        }
    }

    private void doLogin() {
        //获取用户名和密码
        loginName = edit_phone.getText().toString().trim();
        loginPwd = edit_pwd.getText().toString().trim();

        //判断
        if (TextUtils.isEmpty(loginName)){
            Toast.makeText(LoginActivity.this,"账号不能为空",Toast.LENGTH_LONG).show();
        }
        if (TextUtils.isEmpty(loginPwd)){
            Toast.makeText(LoginActivity.this,"密码不能为空",Toast.LENGTH_LONG).show();
        }

        userPresenterImp.doLogin(1, loginName, loginPwd);
    }

    @Override
    public void success(int type, String data) {
        Gson gson = new Gson();
        LoginBean loginBean = gson.fromJson(data, LoginBean.class);
        LoginBean.ResultBean result = loginBean.getResult();

        if (loginBean.getStatus().equals("0000")){
            Toast.makeText(LoginActivity.this,loginBean.getMessage(),Toast.LENGTH_LONG).show();
            String pic = result.getHeadPic();
            String name = result.getNickName();
            sp.edit().putString("pic",pic).putString("name",name).commit();
            finish();
        }else{
            Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_LONG).show();
    }

}

    @Override
    public void fail(int type, String error) {

    }
}
