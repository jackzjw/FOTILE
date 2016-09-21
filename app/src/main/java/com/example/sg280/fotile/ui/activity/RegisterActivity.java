package com.example.sg280.fotile.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.presents.Interface.IRegisterContracts;
import com.example.sg280.fotile.presents.RegisterPresent;
import com.example.sg280.fotile.utils.SharedPreferencesUtil;
import com.example.sg280.fotile.utils.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by sg280 on 2016-07-26.
 */
public class RegisterActivity extends BaseActivity implements IRegisterContracts.View {
    @Bind(R.id.et_phoneNum)
    EditText et_phone;
    @Bind(R.id.et_password)
    EditText et_pwd;
    @Bind(R.id.et_verification_code)
    EditText et_verify;
    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.btn_send_verification_code)
    Button verifycode;

    private RegisterPresent present;
   private int time=60;
    private Handler handler;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_register;


    }

    @Override
    protected void onInitView() {
        present = new RegisterPresent(this, this);
        tv_title.setText("注   册");
        handler=new Handler();
    }

    //验证动态验证码
    @OnClick(R.id.btn_send_verification_code)
    void send() {
        if(et_phone.getText().toString().equals("")){
            ToastUtil.showLong(this, "手机号不能为空");
            return;
        }
        if (!isMobileNO(et_phone.getText().toString())){
            ToastUtil.showLong(this,"手机号码格式不正确");
            return;
        }
        present.getDycode(et_phone.getText().toString());
    }

    //注册
    @OnClick(R.id.btn_register)
    void register() {
        if(et_phone.getText().toString().equals("")){
            ToastUtil.showLong(this, "手机号不能为空");
            return;
        }
        if (et_pwd.getText().toString().equals("")){
            ToastUtil.showLong(this,"密码不能不能为空");
            return;
        }
        if (!isMobileNO(et_phone.getText().toString())){
            ToastUtil.showLong(this,"手机号码格式不正确");
            return;
        }
        present.userValid(et_phone.getText().toString(),
                et_verify.getText().toString());

    }
    //返回上一界面
    @OnClick(R.id.iv_back_title)
    void back(){
        finish();
    }
    //登录
    @OnClick(R.id.tv_login)
    void login(){
        startActivity(new Intent(this, LoginActivity.class));
    }
    @Override
    public void RegisterSucc() {
        ToastUtil.showLong(this, "注册成功");
        SharedPreferencesUtil.setPwd(getApplication(),et_pwd.getText().toString().trim());//密码保存到本地
        startActivity(new Intent(this, MainActivity.class));
        finish();

    }


//刷新验证码
    @Override
    public void refreshDycode() {

       handler.postDelayed(run, 1000);
    }

    @Override
    public void verifySucc() {
        present.register(et_phone.getText().toString(),
                et_verify.getText().toString(), et_pwd.getText().toString());
    }

    Runnable run=new Runnable() {
        @Override
        public void run() {
            verifycode.setEnabled(false);
            verifycode.setBackgroundResource(R.drawable.shape_register_gray);
            verifycode.setText(time-- + "  s");
            if(time==0){
                verifycode.setBackgroundResource(R.drawable.shape_register_black);
                verifycode.setText("发送验证码");
                time=60;
                verifycode.setEnabled(true);
            }else {
                handler.postDelayed(this, 1000);
            }
        }
    };
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
        run=null;
        handler=null;
        present.ondestory();
    }
}
