package com.example.sg280.fotile.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.sg280.fotile.model.bean.CollectLiveBean;
import com.example.sg280.fotile.model.bean.CollectProductBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * 我的收藏中的商品的Adapter
 * Created by Tian on 2016/8/10.
 */
public class CollectionProductAdapter extends RecyclerView.Adapter<CollectProductViewHolder> {

    private Context context;
    private List<CollectProductBean> productList;//产品list

    public CollectionProductAdapter(Context context) {
        this.context = context;
    }

    public CollectionProductAdapter(Context context, List<CollectProductBean> productList) {
        this.context = context;
        this.productList = productList;
    }

    public void setProductList(List<CollectProductBean> productList) {
        this.productList = productList;
    }

    @Override
    public CollectProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CollectProductViewHolder(parent,context);
    }

    @Override
    public void onBindViewHolder(CollectProductViewHolder holder, int position) {
        holder.setData(productList.get(position));


    }

    @Override
    public int getItemCount() {

        return null == productList?0:productList.size();
    }
}
