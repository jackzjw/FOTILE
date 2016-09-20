package com.example.sg280.fotile.presents;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.sg280.fotile.http.FotileRetrofit;
import com.example.sg280.fotile.model.bean.NoResponseResult;
import com.example.sg280.fotile.model.source.HttpService;
import com.example.sg280.fotile.presents.Interface.AddNewAddressContacts;
import com.example.sg280.fotile.presents.Interface.MyAddressContacts;
import com.example.sg280.fotile.utils.ToastUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Tian on 2016/8/22.
 */
public class AddNewAddressPresent implements AddNewAddressContacts.Present {

    private AddNewAddressContacts.View view;
    private Context context;

    public AddNewAddressPresent(AddNewAddressContacts.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void addAddress(String type, String userid, String pcr, String address, String tel, String postcode, String recipients, String isdefault) {

        FotileRetrofit.getInstance().getRetrofit().create(HttpService.class).addAddress(type,userid,pcr,address,tel,postcode,recipients,isdefault).
                subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<NoResponseResult>() {
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
                Log.i("返回数据",noResponseResult.toString());
                if (noResponseResult.getSuccess().equals("1")) {
                    //添加成功
                    view.addSuc();
                } else {
                    ToastUtil.showLong(context, noResponseResult.getErrorMessage());
                }
            }
        });

    }

    //修改收货地址
    @Override
    public void modifyAddress(String action, String userId, String pcr, String address, String tel, String postcode, String recipients, String isDefault, String addressId) {

        FotileRetrofit.getInstance().getRetrofit().create(HttpService.class).modifyAddress(action,userId,pcr,address,tel,postcode,recipients,isDefault,addressId).
                subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<NoResponseResult>() {
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
                Log.i("返回数据",noResponseResult.toString());
                if (noResponseResult.getSuccess().equals("1")) {
                    view.modifySuc();
                } else {
                    ToastUtil.showLong(context, noResponseResult.getErrorMessage());
                }
            }
        });

    }

    @Override
    public void ondestory() {

    }
}
