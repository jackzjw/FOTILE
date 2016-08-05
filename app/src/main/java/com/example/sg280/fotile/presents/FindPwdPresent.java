package com.example.sg280.fotile.presents;

import android.content.Context;

import com.example.sg280.fotile.http.FotileRetrofit;
import com.example.sg280.fotile.http.ProgressSubscriber;
import com.example.sg280.fotile.model.bean.NoResponseResult;
import com.example.sg280.fotile.model.source.HttpOnNextListener;
import com.example.sg280.fotile.model.source.HttpService;
import com.example.sg280.fotile.presents.Interface.IFindPwdContacts;
import com.example.sg280.fotile.utils.MD5Util;
import com.example.sg280.fotile.utils.ToastUtil;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by sg280 on 2016-07-28.
 */
public class FindPwdPresent implements IFindPwdContacts.Present {
    private IFindPwdContacts.View mview;
    private Context context;
    public FindPwdPresent(IFindPwdContacts.View view,Context act){
        this.mview=view;
        this.context=act;
    }
    @Override
    public void findPwd(String userid, String newpwd) {
        String MD5pwd= MD5Util.getStringMD5(newpwd+"FOTILE");
        FotileRetrofit.getInstance().getRetrofit().create(HttpService.class).findPwd(userid,MD5pwd)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new ProgressSubscriber<NoResponseResult>(findOnNextListener,context));

    }
    HttpOnNextListener findOnNextListener = new HttpOnNextListener<NoResponseResult>() {
        @Override
        public void onNext(NoResponseResult subjects) {
            if (subjects.getSuccess().equals("1")){
                mview.findPwdSucc();
            }else {
                ToastUtil.showLong(context,subjects.getErrorMessage());
            }

        }
    };

}
