package com.example.sg280.fotile.presents;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.sg280.fotile.http.FotileRetrofit;
import com.example.sg280.fotile.model.bean.NoResponseResult;
import com.example.sg280.fotile.model.source.HttpService;
import com.example.sg280.fotile.presents.Interface.ModifyUserInfoContacts;
import com.example.sg280.fotile.utils.MD5Util;
import com.example.sg280.fotile.utils.SharedPreferencesUtil;
import com.example.sg280.fotile.utils.ToastUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by Tian on 2016/8/4.
 */
public class ModifyUserInfoPresent implements ModifyUserInfoContacts.Present {

    private ModifyUserInfoContacts.View myView;
    private Context context;
    String md5pwdNew = null;
    String md5pwdOld = null;


    public ModifyUserInfoPresent(ModifyUserInfoContacts.View myView, Context context) {
        this.myView = myView;
        this.context = context;
    }

    @Override
    public void ondestory() {


    }

    //完成修改
    @Override
    public void overModify(String userId, String userName, String userTel, String oldPwd, String pwdNew,String againNewPwd) {

        if (isOk(userName,oldPwd,pwdNew,againNewPwd)) {
            FotileRetrofit.getInstance().getRetrofit().create(HttpService.class).modifyUserInfo(userId, userName,
                    userTel, md5pwdOld, md5pwdNew).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<NoResponseResult>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            if (e instanceof SocketTimeoutException) {
                                Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
                            } else if (e instanceof ConnectException) {
                                Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                                Log.e("tag", "error----------->" + e.toString());
                            }
                        }

                        @Override
                        public void onNext(NoResponseResult noResponseResult) {
                            Log.i("返回数据", noResponseResult.toString());
                            if (noResponseResult.getSuccess().equals("1")) {

                                myView.modifySuc();
                            } else {
                                ToastUtil.showLong(context, noResponseResult.getErrorMessage());
                            }
                        }
                    });
        }

    }

    /**
     * 判断是否可进行修改资料请求
     * @param userName
     * @param oldPwd
     * @param pwdNew
     * @param againNewPwd
     * @return
     */
    private boolean isOk(String userName, String oldPwd, String pwdNew,String againNewPwd){
        if(TextUtils.isEmpty(userName)){
            ToastUtil.showShort(context,"请输入用户名");
            return false;
        }
        //全为空，只修改用户名
        if(TextUtils.isEmpty(oldPwd) && TextUtils.isEmpty(pwdNew) && TextUtils.isEmpty(againNewPwd)){
            return true;
        }

        if(TextUtils.isEmpty(oldPwd) && (!TextUtils.isEmpty(pwdNew) || !TextUtils.isEmpty(againNewPwd))){
            ToastUtil.showShort(context,"请输入旧密码");
            return false;
        }

        if(!TextUtils.isEmpty(oldPwd) && (TextUtils.isEmpty(pwdNew) || TextUtils.isEmpty(againNewPwd))){
            ToastUtil.showShort(context,"请输入新密码");
            return false;
        }

        if(!pwdNew.equals(againNewPwd)){
            ToastUtil.showShort(context,"两次新密码出入不一致");
            return false;
        }

        md5pwdNew = MD5Util.getStringMD5(pwdNew + "FOTILE");
        md5pwdOld = MD5Util.getStringMD5(oldPwd + "FOTILE");
        return true;
    }
}
