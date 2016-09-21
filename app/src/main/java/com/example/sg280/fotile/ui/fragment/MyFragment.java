package com.example.sg280.fotile.ui.fragment;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.utils.LoginUtil;

import butterknife.OnClick;

/**
 * Created by sg280 on 2016-07-19.
 */
public class MyFragment extends BaseFragment {
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_register;
    }

    @Override
    protected void onInitView() {

    }
    @OnClick(R.id.btn_register)
    void register(){

        LoginUtil.checkLogin(getActivity(), new LoginUtil.LoginCallback() {
            @Override
            public void onLogin() {
              //  LogUtil.e("登录");
            }
        });
    }
}
