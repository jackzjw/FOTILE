package com.example.sg280.fotile.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.sg280.fotile.model.bean.ProductBean;
import com.example.sg280.fotile.ui.activity.ProductsActivity;

import java.util.List;

/**
 * Created by Tian on 2016/8/8.
 */
public class MyProductAdapter extends RecyclerView.Adapter<MyProductViewHolder> {

    private Context context;
    private List<ProductBean> list;

    public MyProductAdapter(Context context,List<ProductBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyProductViewHolder(parent,context);
    }

    @Override
    public void onBindViewHolder(MyProductViewHolder holder, int position) {
        holder.setData(list.get(position));

        //点击商品item跳转到商品详情
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(ProductsActivity.newIntent(context,list.get(position).getProductID()));
            }
        });
    }

    @Override
    public int getItemCount() {
        if(null != list){
            return list.size();
        }
        return 0;
    }
}
