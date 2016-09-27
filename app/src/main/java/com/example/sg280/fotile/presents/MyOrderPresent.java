package com.example.sg280.fotile.presents;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.adapter.MyOrderAdapter;
import com.example.sg280.fotile.http.FotileRetrofit;
import com.example.sg280.fotile.http.ProgressSubscriber;
import com.example.sg280.fotile.model.bean.OrderBean;
import com.example.sg280.fotile.model.source.HttpOnNextListener;
import com.example.sg280.fotile.model.source.MyOrderSubject;
import com.example.sg280.fotile.presents.Interface.MyOrderContacts;
import com.example.sg280.fotile.utils.DensityUtil;
import com.example.sg280.fotile.utils.SharedPreferencesUtil;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

import java.util.List;

import retrofit2.http.HEAD;

/**
 * Created by sg027 on 2016/8/30.
 */
public class MyOrderPresent implements MyOrderContacts.Present {

    private Context context;
    private MyOrderContacts.View view;
    private RecyclerView recyclerView;
    private List<OrderBean> allOrderList = null;//所有订单的列表
    private List<OrderBean> waitPayList = null;//待付款订单列表
    private List<OrderBean> waitShipmentsList = null;//待发货订单列表
    private List<OrderBean> waitReceiptList = null;//待收货订单列表
    private List<OrderBean> orderIsDoneList = null;//已完成订单列表
    private MyOrderAdapter adapter;

    public MyOrderPresent(Context context, MyOrderContacts.View view, RecyclerView recyclerView) {
        this.context = context;
        this.view = view;
        this.recyclerView = recyclerView;

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        DividerDecoration divider = new DividerDecoration(context.getResources().getColor(R.color.home_divide_gray), DensityUtil.dp2px(context, 20), 0, 0);
        recyclerView.addItemDecoration(divider);
    }

    @Override
    public void getMyOrder(String orderStatus) {

        HttpOnNextListener getOrderOnNextListener = new HttpOnNextListener<List<OrderBean>>() {

            @Override
            public void onNext(List<OrderBean> orderList) {

                switch (orderStatus) {
                    case "":
                        allOrderList = orderList;
                        getAll();
                        break;
                    case "1":
                        waitPayList = orderList;
                        getWaitPay();
                        break;
                    case "2":
                        waitShipmentsList = orderList;
                        getWaitShipments();
                        break;
                    case "3":
                        waitReceiptList = orderList;
                        getWaitReceipt();
                        break;
                    case "4":
                        orderIsDoneList = orderList;
                        getOrderIsDone();
                        break;
                    default:
                        break;
                }
            }
        };

        MyOrderSubject myOrderSubject = new MyOrderSubject(new ProgressSubscriber(getOrderOnNextListener, context),
                SharedPreferencesUtil.getId(context), "10", 1, orderStatus);

        switch (orderStatus) {
            case "":
                if (null == allOrderList) {
                    FotileRetrofit.getInstance().doHttpDeal(myOrderSubject);
                } else {
                    getAll();
                }
                break;
            case "1":
                if (null == waitPayList) {
                    FotileRetrofit.getInstance().doHttpDeal(myOrderSubject);
                } else {
                    getWaitPay();
                }
                break;
            case "2":
                if (null == waitShipmentsList) {
                    FotileRetrofit.getInstance().doHttpDeal(myOrderSubject);
                } else {
                    getWaitShipments();
                }
                break;
            case "3":
                if (null == waitReceiptList) {
                    FotileRetrofit.getInstance().doHttpDeal(myOrderSubject);
                } else {
                    getWaitReceipt();
                }
                break;
            case "4":
                if (null == orderIsDoneList) {
                    FotileRetrofit.getInstance().doHttpDeal(myOrderSubject);
                } else {
                    getOrderIsDone();
                }
                break;
            default:
                break;
        }


    }

    public void getAll() {
        adapter = new MyOrderAdapter(context, allOrderList, view.getActivity());

        recyclerView.setAdapter(adapter);
    }

    public void getWaitPay() {
        adapter = new MyOrderAdapter(context, waitPayList, view.getActivity());

        recyclerView.setAdapter(adapter);
    }

    public void getWaitShipments() {
        adapter = new MyOrderAdapter(context, waitShipmentsList, view.getActivity());

        recyclerView.setAdapter(adapter);
    }

    public void getWaitReceipt() {

        adapter = new MyOrderAdapter(context, waitReceiptList, view.getActivity());

        recyclerView.setAdapter(adapter);
    }

    public void getOrderIsDone() {

        adapter = new MyOrderAdapter(context, orderIsDoneList, view.getActivity());

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void ondestory() {

    }
}
