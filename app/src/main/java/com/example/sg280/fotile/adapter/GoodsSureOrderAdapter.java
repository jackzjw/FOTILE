package com.example.sg280.fotile.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.sg280.fotile.model.bean.ShoppingCartGoodsBean;
import com.example.sg280.fotile.utils.StringUtil;

import java.util.List;

/**
 * Created by Tian on 2016/8/18.
 */
public class GoodsSureOrderAdapter extends RecyclerView.Adapter<GoodsSureOrderViewHolder> {

    private Context context;
    private List<ShoppingCartGoodsBean> list;

    public GoodsSureOrderAdapter(Context context, List<ShoppingCartGoodsBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public GoodsSureOrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new GoodsSureOrderViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(GoodsSureOrderViewHolder holder, int position) {
        holder.setData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return StringUtil.listIsNull(list)?0:list.size();
    }
}
