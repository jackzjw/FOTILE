package com.example.sg280.fotile.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.sg280.fotile.model.bean.CouponsBean;

import java.util.List;


/**
 * 我的优惠券的Adapter
 * Created by Tian on 2016/8/11.
 */
public class MyCouponsAdapter extends RecyclerView.Adapter<MyCouponsViewHolder> {

    private List<CouponsBean> list;
    private Context context;

    public MyCouponsAdapter(List<CouponsBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setList(List<CouponsBean> list) {
        this.list = list;
    }

    @Override
    public MyCouponsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyCouponsViewHolder(parent,context);
    }

    @Override
    public void onBindViewHolder(MyCouponsViewHolder holder, int position) {

        holder.setData(list.get(position));

    }

    @Override
    public int getItemCount() {
        return null == list?0:list.size();
    }

}
