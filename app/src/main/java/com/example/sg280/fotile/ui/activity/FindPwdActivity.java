package com.example.sg280.fotile.ui.activity;

import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.model.bean.MySelfInfo;
import com.example.sg280.fotile.presents.Interface.IFindPwdContacts;
import com.example.sg280.fotile.presents.FindPwdPresent;
import com.example.sg280.fotile.utils.LogUtil;
import com.example.sg280.fotile.utils.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by sg280 on 2016-07-28.
 */
public class FindPwdActivity extends BaseActivity implements IFindPwdContacts.View{
    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.et_setNewPwd)
    EditText et_set;
    @Bind(R.id.et_sureNewPwd)
    EditText et_sure;
    private FindPwdPresent present;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_findpassword_set;
    }

    @Override
    protected void onInitView() {
        tv_title.setText("找回密码");
     present=new FindPwdPresent(this,this);

    }
    @OnClick(R.id.btn_submit)
    void submit(){
           if(et_set.getText().toString().equals("")||et_sure.getText().toString().equals("")){
               ToastUtil.showLong(this,"密码不能为空");
               return;
           }
        if(!et_sure.getText().toString().equals(et_set.getText().toString())){
          ToastUtil.showLong(this,"两次输入密码不一致，请重新输入");
            return;
        }
        String userid= MySelfInfo.getInstance().getId();
        LogUtil.e(userid);
        present.findPwd(userid,et_sure.getText().toString());
    }

    @Override
    public void findPwdSucc() {
       ToastUtil.showLong(this,"重置密码成功");
        startActivity(new Intent(this,LoginActivity.class));
    }
}
