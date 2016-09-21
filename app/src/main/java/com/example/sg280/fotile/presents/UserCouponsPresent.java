package com.example.sg280.fotile.presents;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.sg280.fotile.adapter.MyCouponsAdapter;
import com.example.sg280.fotile.http.FotileRetrofit;
import com.example.sg280.fotile.http.ProgressSubscriber;
import com.example.sg280.fotile.model.bean.CouponsBean;
import com.example.sg280.fotile.model.source.GetCouponsSubject;
import com.example.sg280.fotile.model.source.HttpOnNextListener;
import com.example.sg280.fotile.presents.Interface.UserCouponsContacts;
import com.example.sg280.fotile.utils.SharedPreferencesUtil;

import java.util.List;

/**
 * Created by Tian on 2016/9/18.
 */
public class UserCouponsPresent implements UserCouponsContacts.Present {

    private Context context;
    private RecyclerView recyclerView;
    private List<CouponsBean> list;
    private MyCouponsAdapter couponsAdapter;

    public UserCouponsPresent(Context context, RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    public void getCoupons(String action) {

        HttpOnNextListener getCouponsNextListener = new HttpOnNextListener<List<CouponsBean>>() {

            @Override
            public void onNext(List<CouponsBean> listCoupons) {
                list = listCoupons;
                couponsAdapter = new MyCouponsAdapter(list,context);
                recyclerView.setAdapter(couponsAdapter);
            }
        };
        GetCouponsSubject getCouponsSubject = new GetCouponsSubject(new ProgressSubscriber(getCouponsNextListener,context),
                SharedPreferencesUtil.getId(context),action);
        FotileRetrofit.getInstance().doHttpDeal(getCouponsSubject);
    }

    @Override
    public void ondestory() {

    }
}
