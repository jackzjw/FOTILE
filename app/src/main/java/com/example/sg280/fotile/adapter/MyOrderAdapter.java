package com.example.sg280.fotile.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.sg280.fotile.model.bean.OrderBean;

import java.util.List;


/**
 * 我的订单的Adapter
 * Created by Tian on 2016/8/8.
 */
public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderViewHolder> {

    private Context context;
    private Activity activity;

    private List<OrderBean> list;

    public MyOrderAdapter(Context context) {
        this.context = context;
    }

    public MyOrderAdapter(Context context, List<OrderBean> list,Activity activity) {
        this.context = context;
        this.list = list;
        this.activity = activity;

    }

    public void setList(List<OrderBean> list) {
        this.list = list;
    }

    @Override
    public MyOrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new MyOrderViewHolder(parent,context,activity);

    }

    @Override
    public void onBindViewHolder(MyOrderViewHolder holder, int position) {
        holder.setData(list.get(position));
    }

    @Override
    public int getItemCount() {

        if(null != list){
            return list.size();
        }
        return 0;
    }
}
