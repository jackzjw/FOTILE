package com.example.sg280.fotile.ui.activity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.presents.ModifyUserInfoContacts;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Tian on 2016/8/3.
 * 修改用户信息界面
 */
public class ModifyUserInfoActivity extends BaseActivity implements ModifyUserInfoContacts.View{

    @Bind(R.id.iv_back_title)//标题的返回图标
    ImageView iv_back_title;
    @Bind(R.id.tv_title)//标题栏标题
    TextView tv_title;
    @Bind(R.id.et_username)
    EditText et_username;
    @Bind(R.id.et_phoneNum)
    EditText et_phoneNum;
    @Bind(R.id.et_password)
    EditText et_password;
    @Bind(R.id.et_password_again)
    EditText et_password_again;
    @Bind(R.id.btn_over)
    Button btn_over;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_modify_user_info;
    }

    @Override
    protected void onInitView() {

        tv_title.setText(R.string.modify_user_info);//修改标题栏的标题
    }

    //返回上一界面
    @OnClick(R.id.iv_back_title)
    void goBack(){
        onBackPressed();
    }

    //修改成功
    @Override
    public void modifySucc() {

    }
}
