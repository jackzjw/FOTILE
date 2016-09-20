package com.example.sg280.fotile.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.example.sg280.fotile.model.bean.CouponsBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * 我的优惠券的Adapter
 * Created by Tian on 2016/8/11.
 */
public class MyCouponsAdapter extends RecyclerArrayAdapter<CouponsBean> {

    private Context context;
    public MyCouponsAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyCouponsViewHolder(parent,context);
    }
}
