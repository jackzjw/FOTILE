package com.example.sg280.fotile.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.model.bean.MySelfInfo;
import com.example.sg280.fotile.presents.LoginContacts;
import com.example.sg280.fotile.presents.LoginPresent;
import com.example.sg280.fotile.utils.ToastUtil;
import com.example.sg280.fotile.widget.TitleBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends Activity implements LoginContacts.View {
    @Bind(R.id.title_bar)
    TitleBar toolBar;
    @Bind(R.id.et_phone_number)
    EditText et_phone;
    @Bind(R.id.et_pwd_number)
    EditText et_pwd;

    @Bind(R.id.tv_forget_pwd)
    TextView tv_forget_pwd;
    private LoginPresent present;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //获取本地缓存
        MySelfInfo.getInstance().getCache(getApplicationContext());
        present = new LoginPresent(this, this);
        if (needLogin()) {//没有账号，需要登录
            setContentView(R.layout.activity_login);
            ButterKnife.bind(this);
            initView();
        } else {
            //直接登录
            present.imLogin(MySelfInfo.getInstance().getId(), MySelfInfo.getInstance().getUserSig());
        }


    }

    private void initView() {
        toolBar.setTitle("登   录");
        TextView register = new TextView(this);
        register.setTextColor(getResources().getColor(R.color.theme_red));
        register.setText("注册");
        toolBar.addRightView(register);
        toolBar.backIcon();
        register.setOnClickListener((view) -> {
            //跳转到注册界面
        });

    }

    @OnClick(R.id.bt_login)
    void login() {
        if ("".equals(et_phone.getText().toString())) {
            ToastUtil.showLong(this, "手机号不能为空");
            return;
        }
        if ("".equals(et_pwd.getText().toString())) {
            ToastUtil.showLong(this, "密码不能为空");
            return;
        }
        present.ftLogin(et_phone.getText().toString(), et_pwd.getText().toString());
    }


    /**
     * 判断是否需要登录
     *
     * @return true 代表需要重新登录
     */
    public boolean needLogin() {
        if (MySelfInfo.getInstance().getId() != null) {
            return false;//有账号不需要登录
        } else {
            return true;//需要登录
        }

    }


    /**
     * 直接跳转主界面
     */
    private void jumpIntoHomeActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void loginSucc() {
        ToastUtil.showLong(this, "登录成功");
    }

    @Override
    public void loginFailed() {

    }
}