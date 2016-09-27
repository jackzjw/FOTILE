package com.example.sg280.fotile.presents;

import android.content.Context;

import com.example.sg280.fotile.http.FotileRetrofit;
import com.example.sg280.fotile.http.ProgressSubscriber;
import com.example.sg280.fotile.model.bean.UserInfoBean;
import com.example.sg280.fotile.model.source.HttpOnNextListener;
import com.example.sg280.fotile.model.source.UserInfoSubject;
import com.example.sg280.fotile.presents.Interface.UserPointContacts;
import com.example.sg280.fotile.utils.SharedPreferencesUtil;

/**
 * 用户积分的Present
 * Created by Tian on 2016/9/19.
 */
public class UserPointPresent implements UserPointContacts.Present {

    private Context context;
    private UserPointContacts.View view;

    public UserPointPresent(Context context, UserPointContacts.View view) {
        this.context = context;
        this.view = view;
    }

    //获取积分
    @Override
    public void getPoint() {

        HttpOnNextListener pointNextListener = new HttpOnNextListener<UserInfoBean>() {
            @Override
            public void onNext(UserInfoBean userInfoBean) {
                String pointAll = (null == userInfoBean.getIntegralTotal())?"0":userInfoBean.getIntegralTotal();
                String pointUsed = (null == userInfoBean.getIntegralUsed())?"0":userInfoBean.getIntegralUsed();
                view.setPoint(pointAll,pointUsed);//显示积分到界面上
            }
        };
        UserInfoSubject userInfoSubject = new UserInfoSubject(new ProgressSubscriber(pointNextListener,context),
                SharedPreferencesUtil.getId(context));
        FotileRetrofit.getInstance().doHttpDeal2(userInfoSubject);
    }

    @Override
    public void ondestory() {

    }
}
