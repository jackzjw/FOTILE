package com.example.sg280.fotile.presents;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.sg280.fotile.adapter.ChoiceCouponAdapter;
import com.example.sg280.fotile.http.FotileRetrofit;
import com.example.sg280.fotile.http.ProgressSubscriber;
import com.example.sg280.fotile.model.bean.CouponsBean;
import com.example.sg280.fotile.model.source.GetNowUsableCouponsSubject;
import com.example.sg280.fotile.model.source.HttpOnNextListener;
import com.example.sg280.fotile.presents.Interface.ChoiceCouponContacts;
import com.example.sg280.fotile.utils.SharedPreferencesUtil;

import java.util.List;

/**
 * Created by Tian on 2016/9/6.
 */
public class ChoiceCouponPresent implements ChoiceCouponContacts.Present {

    private Activity activity;
    private final String ACTION = "GetCurrentUseCoupon";
    private RecyclerView recyclerView;
    private ChoiceCouponAdapter myAdapter;
    private List<CouponsBean> list;
    private ChoiceCouponContacts.View view;

    public ChoiceCouponPresent(Activity activity, ChoiceCouponContacts.View view,RecyclerView recyclerView) {
        this.activity = activity;
        this.recyclerView = recyclerView;
        this.view = view;
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
    }

    @Override
    public void getNowUsableCoupons(String shopCarId) {

        HttpOnNextListener getNowNextListener = new HttpOnNextListener<List<CouponsBean>>() {
            @Override
            public void onNext(List<CouponsBean> couponsList) {
                list = couponsList;
                if(null == list || list.size() == 0){
                    view.noData();//没有数据
                }else{
                    view.hadData();
                    myAdapter = new ChoiceCouponAdapter(activity,list);
                    recyclerView.setAdapter(myAdapter);
                }
            }
        };

        GetNowUsableCouponsSubject getNowUsableCouponsSubject = new GetNowUsableCouponsSubject(
                new ProgressSubscriber(getNowNextListener,activity), SharedPreferencesUtil.getId(activity),
                ACTION,shopCarId);
        FotileRetrofit.getInstance().doHttpDeal(getNowUsableCouponsSubject);
    }

    @Override
    public void getNowUsableCouponsAtOnce(String productid, String skuid, String productnum) {
        HttpOnNextListener getNowNextListener = new HttpOnNextListener<List<CouponsBean>>() {
            @Override
            public void onNext(List<CouponsBean> couponsList) {
                list = couponsList;
                if(null == list || list.size() == 0){
                    view.noData();//没有数据
                }else{
                    view.hadData();
                    myAdapter = new ChoiceCouponAdapter(activity,list);
                    recyclerView.setAdapter(myAdapter);
                }
            }
        };

        GetNowUsableCouponsSubject getNowUsableCouponsSubject = new GetNowUsableCouponsSubject(
                new ProgressSubscriber(getNowNextListener,activity), SharedPreferencesUtil.getId(activity),
                ACTION,productid,skuid,productnum);
        FotileRetrofit.getInstance().doHttpDeal(getNowUsableCouponsSubject);
    }

    @Override
    public void ondestory() {

    }
}
