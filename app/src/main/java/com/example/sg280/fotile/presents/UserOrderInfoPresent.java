package com.example.sg280.fotile.presents;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.sg280.fotile.adapter.MyProductAdapter;
import com.example.sg280.fotile.http.FotileRetrofit;
import com.example.sg280.fotile.http.ProgressSubscriber;
import com.example.sg280.fotile.model.bean.UserOrderInfoBean;
import com.example.sg280.fotile.model.source.HttpOnNextListener;
import com.example.sg280.fotile.model.source.UserOrderInfoSubject;
import com.example.sg280.fotile.presents.Interface.UserOrderInfoContacts;
import com.example.sg280.fotile.utils.SharedPreferencesUtil;

/**
 * Created by Tian on 2016/9/20.
 */
public class UserOrderInfoPresent implements UserOrderInfoContacts.Present {

    private Context context;
    private UserOrderInfoContacts.View view;
    private MyProductAdapter myProductAdapter;
    private RecyclerView recyclerView;


    public UserOrderInfoPresent(Context context, UserOrderInfoContacts.View view,
                                RecyclerView recyclerView) {
        this.context = context;
        this.view = view;
        this.recyclerView = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    //获取订单详情
    @Override
    public void getUserOrderInfo(String orderId) {

        HttpOnNextListener getOrderInfoListener = new HttpOnNextListener<UserOrderInfoBean>() {

            @Override
            public void onNext(UserOrderInfoBean userOrderInfoBean) {
                view.setInfo(userOrderInfoBean);//将订单详情显示到界面上
                myProductAdapter = new MyProductAdapter(context,userOrderInfoBean.getOrderDetails());
                recyclerView.setAdapter(myProductAdapter);
            }
        };

        UserOrderInfoSubject userOrderInfoSubject = new UserOrderInfoSubject(new ProgressSubscriber
                (getOrderInfoListener,context), SharedPreferencesUtil.getId(context),orderId);
        FotileRetrofit.getInstance().doHttpDeal2(userOrderInfoSubject);
    }

    @Override
    public void ondestory() {

    }
}
