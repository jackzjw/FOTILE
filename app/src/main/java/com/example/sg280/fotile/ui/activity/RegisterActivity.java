package com.example.sg280.fotile.ui.activity;

import android.widget.EditText;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.presents.RegisterContracts;
import com.example.sg280.fotile.utils.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by sg280 on 2016-07-26.
 */
public class RegisterActivity extends BaseActivity implements RegisterContracts.View{
    @Bind(R.id.et_phoneNum)
    EditText et_phone;
    @Bind(R.id.et_password)
    EditText et_pwd;
    @Bind(R.id.et_verification_code)
    EditText et_verify;


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_register;


    }

    @Override
    protected void onInitView() {


    }

    @OnClick(R.id.btn_send_verification_code)
    void send() {

    }

    @OnClick(R.id.btn_register)
    void register() {


    }

    @Override
    public void RegisterSucc() {

    }

    @Override
    public void failed(String msg) {
        ToastUtil.showLong(this,msg);
    }
}
