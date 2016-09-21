package com.example.sg280.fotile.ui.activity;

import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.presents.Interface.ModifyUserInfoContacts;
import com.example.sg280.fotile.presents.ModifyUserInfoPresent;
import com.example.sg280.fotile.utils.MD5Util;
import com.example.sg280.fotile.utils.SharedPreferencesUtil;
import com.example.sg280.fotile.utils.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Tian on 2016/8/3.
 * 修改用户信息界面
 */
public class ModifyUserInfoActivity extends BaseActivity implements ModifyUserInfoContacts.View {

    @Bind(R.id.iv_back_title)
            ImageView iv_back_title;//标题的返回图标
    @Bind(R.id.tv_title)
            TextView tv_title;//标题栏标题
    @Bind(R.id.et_username)
            EditText et_username;// 用户昵称
    @Bind(R.id.tv_phoneNum)
            TextView tv_phoneNum;//用户电话
    @Bind(R.id.et_old_password)
            EditText et_old_password;//旧密码
    @Bind(R.id.et_password)
            EditText et_password;//新密码
    @Bind(R.id.et_password_again)
            EditText et_password_again;//再次确认新密码
    @Bind(R.id.btn_over)
            Button btn_over;//完成修改

    private ModifyUserInfoPresent myPresent;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_modify_user_info;
    }

    @Override
    protected void onInitView() {
        myPresent = new ModifyUserInfoPresent(this, this);
        tv_title.setText(R.string.modify_user_info);//修改标题栏的标题
        et_username.setText(SharedPreferencesUtil.getNickName(getApplication()));
        tv_phoneNum.setText(SharedPreferencesUtil.getPhone(getApplication()));
    }

    //返回上一界面
    @OnClick(R.id.iv_back_title)
    void goBack() {
        onBackPressed();
    }

    //修改完成
    @OnClick(R.id.btn_over)
    void modifyOver() {
        myPresent.overModify(SharedPreferencesUtil.getId(getApplication()), et_username.getText().toString().trim(), tv_phoneNum.getText().toString().trim(),
                et_old_password.getText().toString().trim(),et_password.getText().toString().trim(),et_password_again.getText().toString().trim());

    }

    //修改成功并存储新的用户信息
    @Override
    public void modifySuc() {
        SharedPreferencesUtil.setPhone(getApplication(), tv_phoneNum.getText().toString().trim());
        SharedPreferencesUtil.setNickName(getApplication(), et_username.getText().toString().trim());
        if (null != et_password_again.getText().toString() && !"".equals(et_password_again.getText().toString().trim())) {
            SharedPreferencesUtil.setPwd(getApplication(), MD5Util.getStringMD5(et_password_again.getText().toString().trim() + "FOTILE"));
        }
        ToastUtil.showShort(this, "修改成功");
        finish();
    }
}
