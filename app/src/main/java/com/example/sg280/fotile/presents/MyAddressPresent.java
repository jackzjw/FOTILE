package com.example.sg280.fotile.presents;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.sg280.fotile.adapter.AddressAdapter;
import com.example.sg280.fotile.app.Constants;
import com.example.sg280.fotile.http.FotileRetrofit;
import com.example.sg280.fotile.http.ProgressSubscriber;
import com.example.sg280.fotile.model.bean.NoResponseResult;
import com.example.sg280.fotile.model.bean.ShippingAddressBean;
import com.example.sg280.fotile.model.source.HttpOnNextListener;
import com.example.sg280.fotile.model.source.HttpService;
import com.example.sg280.fotile.model.source.MyAddressSubject;
import com.example.sg280.fotile.presents.Interface.MyAddressContacts;
import com.example.sg280.fotile.ui.activity.AddNewAddressActivity;
import com.example.sg280.fotile.utils.LogUtil;
import com.example.sg280.fotile.utils.SharedPreferencesUtil;
import com.example.sg280.fotile.utils.ToastUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Tian on 2016/8/23.
 */
public class MyAddressPresent implements MyAddressContacts.Present {

    private Context context;
    private MyAddressContacts.View view;
    private AddressAdapter adapter;

    public MyAddressPresent(Context context, MyAddressContacts.View view, AddressAdapter adapter) {
        this.context = context;
        this.view = view;
        this.adapter = adapter;
    }

    //获取收货地址列表
    @Override
    public void getAddress() {

        MyAddressSubject subject = new MyAddressSubject(new ProgressSubscriber(getAddressOnNextListener,context), SharedPreferencesUtil.getId(context),"10",1);
        FotileRetrofit.getInstance().doHttpDeal(subject);
    }

    HttpOnNextListener getAddressOnNextListener = new HttpOnNextListener<List<ShippingAddressBean>>() {
        @Override
        public void onNext(List<ShippingAddressBean> shippingAddressList) {
//            LogUtil.e("返回数据",shippingAddressList.toString());
            adapter.setList(shippingAddressList);
            adapter.notifyDataSetChanged();
        }
    };

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
                    ToastUtil.showShort(context,"设置成功");
                    getAddress();
                } else {
                    ToastUtil.showLong(context, noResponseResult.getErrorMessage());
                }
            }
        });

    }

    @Override
    public void ondestory() {

    }

    /**
     * 返回到确定订单的地址
     * @param shippingAddressBean
     */
    public void getClick(ShippingAddressBean shippingAddressBean){
        Intent intent = new Intent();
        intent.putExtra("address",shippingAddressBean);
        view.getActivity().setResult(view.getActivity().RESULT_OK,intent);
        view.getActivity().finish();
    }

    /**
     * 跳转到修改地址界面
     * @param shippingAddressBean
     */
    public void goEdit(ShippingAddressBean shippingAddressBean){
        Intent intent = new Intent(context, AddNewAddressActivity.class);
        intent.putExtra("which", Constants.MODIFY);
        intent.putExtra("address",shippingAddressBean);
        view.getActivity().startActivity(intent);

    }
}
