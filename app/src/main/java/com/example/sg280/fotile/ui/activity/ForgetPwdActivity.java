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
import com.example.sg280.fotile.utils.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by sg280 on 2016-07-28.
 */
public class ForgetPwdActivity extends BaseActivity implements IRegisterContracts.View{
    @Bind(R.id.et_phonenum_findpwd)
    EditText et_phone;
    @Bind(R.id.et_yanzm_findpwd)
    EditText et_verifycode;
    @Bind(R.id.btn_send_findpwd)
    Button verifycode;
    @Bind(R.id.tv_title)
    TextView tv_title;
    private RegisterPresent present;
    private Handler handler;
    private int time=60;
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_findpassword_send;
    }

    @Override
    protected void onInitView() {
        tv_title.setText("找回密码");
        present=new RegisterPresent(this,this);
        handler=new Handler();
    }
    //返回
    @OnClick(R.id.icon_back)
    void back(){
        finish();
    }
    //发送验证码
    @OnClick(R.id.btn_send_findpwd)
    void sendVerifycode(){
        if(et_phone.getText().toString().equals("")){
            ToastUtil.showLong(this, "手机号不能为空");
            return;
        }
        if (!isMobileNO(et_phone.getText().toString())){
            ToastUtil.showLong(this,"手机号码格式不正确");
            return;
        }
        handler.removeCallbacks(run);
        present.getDycode(et_phone.getText().toString());
    }
    //下一步
    @OnClick(R.id.btn_next_findpwd)
    void next(){
       present.userValid(et_phone.getText().toString(),et_verifycode.getText().toString());

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
    public void RegisterSucc() {

    }


    @Override
    public void refreshDycode() {
        handler.postDelayed(run, 1000);
    }

    @Override
    public void verifySucc() {
        startActivity(new Intent(this,FindPwdActivity.class));
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
