package com.example.sg280.fotile.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.model.bean.MySelfInfo;
import com.example.sg280.fotile.presents.Interface.ILoginContacts;
import com.example.sg280.fotile.presents.LoginPresent;
import com.example.sg280.fotile.utils.MD5Util;
import com.example.sg280.fotile.utils.SharedPreferencesUtil;
import com.example.sg280.fotile.utils.ToastUtil;
import com.example.sg280.fotile.widget.TitleBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends Activity implements ILoginContacts.View {
    @Bind(R.id.title_bar)
    TitleBar toolBar;
    @Bind(R.id.et_phone_number)
    EditText et_phone;
    @Bind(R.id.et_pwd_number)
    EditText et_pwd;
    private LoginPresent present;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //获取本地缓存
        MySelfInfo.getInstance().getCache(getApplicationContext());
        present = new LoginPresent(this, this);
   ///     if (needLogin()) {//没有账号，需要登录
            setContentView(R.layout.activity_login);
            ButterKnife.bind(this);
            initView();
      //  } else {
      //      jumpIntoHomeActivity();
            //直接登录
   //         present.imLogin(MySelfInfo.getInstance().getId(), MySelfInfo.getInstance().getUserSig());
   //    }
//

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
            startActivity(new Intent(LoginActivity.this,RegisterActivity.class
            ));

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
        if (!isMobileNO(et_phone.getText().toString())){
            ToastUtil.showLong(this,"手机号码格式不正确");
            return;
        }
        present.ftLogin(et_phone.getText().toString(), et_pwd.getText().toString());
    }
    //忘记密码
     @OnClick(R.id.tv_forget_pwd)
     void forget(){
         startActivity(new Intent(this, ForgetPwdActivity.class));
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
        SharedPreferencesUtil.setPwd(this, MD5Util.getStringMD5(et_pwd.getText().toString().trim() + "FOTILE"));
        setResult(Activity.RESULT_OK);
        finish();
    }

    @Override
    public void loginSucc() {
        ToastUtil.showLong(this, "登录成功");
       jumpIntoHomeActivity();

    }

    @Override
    public void loginFailed() {
     //   ToastUtil.showLong(this, "IM登陆失败");
        jumpIntoHomeActivity();
    }
    /**
     * 验证手机格式
     */
    private boolean isMobileNO(String mobiles) {
        String telRegex = "[1][2345789]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        present.ondestory();
    }
}