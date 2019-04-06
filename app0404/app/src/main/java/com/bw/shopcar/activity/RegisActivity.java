package com.bw.shopcar.activity;

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
import com.bw.shopcar.bean.RegisBean;
import com.bw.shopcar.mvp.model.UserModelImp;
import com.bw.shopcar.mvp.presenter.UserPresenterImp;
import com.bw.shopcar.mvp.view.UserView;
import com.google.gson.Gson;

public class RegisActivity extends AppCompatActivity implements View.OnClickListener, UserView {

    private UserPresenterImp userPresenterImp;
    private TextView tv_regis_back;
    private EditText regis_phone;
    private EditText regis_pwd;
    private TextView tv_login;
    private Button btn_regis;
    private SharedPreferences sp;
    private String regisPhone;
    private String regisPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis);

        //初始化控件
        tv_regis_back = findViewById(R.id.tv_regis_back);
        regis_phone = findViewById(R.id.regis_phone);
        regis_pwd = findViewById(R.id.regis_pwd);
        tv_login = findViewById(R.id.tv_login);
        btn_regis = findViewById(R.id.btn_regis);

        //设置点击监听
        tv_regis_back.setOnClickListener(this);
        tv_login.setOnClickListener(this);
        btn_regis.setOnClickListener(this);

        userPresenterImp = new UserPresenterImp(new UserModelImp(),this);

        sp = getSharedPreferences("user", MODE_PRIVATE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_regis_back:
                finish();
                break;
            case R.id.tv_login:
                finish();
                break;
            case R.id.btn_regis:
                doRegis();
                break;
        }
    }

    private void doRegis() {
        //获取账号和密码
        regisPhone = regis_phone.getText().toString().trim();
        regisPwd = regis_pwd.getText().toString().trim();

        //判断
        if (TextUtils.isEmpty(regisPhone)){
            Toast.makeText(RegisActivity.this,"账号不能为空",Toast.LENGTH_LONG).show();
        }
        if (TextUtils.isEmpty(regisPwd)){
            Toast.makeText(RegisActivity.this,"密码不能为空",Toast.LENGTH_LONG).show();
        }

        userPresenterImp.doRegis(0, regisPhone, regisPwd);
    }

    @Override
    public void success(int type, String data) {
        Gson gson = new Gson();
        RegisBean regisBean = gson.fromJson(data, RegisBean.class);
        if (regisBean.getStatus().equals("0000")){
            Toast.makeText(RegisActivity.this,regisBean.getMessage(),Toast.LENGTH_LONG).show();
            sp.edit().putString("regisPhone",regisPhone).commit();
            sp.edit().clear();
            finish();
        }else{
            Toast.makeText(RegisActivity.this,"注册失败",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void fail(int type, String error) {

    }
}
