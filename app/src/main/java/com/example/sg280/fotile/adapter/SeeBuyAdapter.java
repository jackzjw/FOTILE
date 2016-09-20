package com.example.sg280.fotile.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.example.sg280.fotile.model.bean.LiveProductBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by sg280 on 2016-08-11.
 */
public class SeeBuyAdapter extends RecyclerArrayAdapter<LiveProductBean> {
    public SeeBuyAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new SeeBuyViewHolder(parent);
    }
}
