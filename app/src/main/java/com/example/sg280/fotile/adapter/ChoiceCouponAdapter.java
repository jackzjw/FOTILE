package com.example.sg280.fotile.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.sg280.fotile.model.bean.CouponsBean;

import java.util.List;

/**
 * 选择优惠券的Adapter
 * Created by Tian on 2016/9/6.
 */
public class ChoiceCouponAdapter extends RecyclerView.Adapter<MyCouponsViewHolder> {

    private Activity activity;
    private List<CouponsBean> list;

    public ChoiceCouponAdapter(Activity activity, List<CouponsBean> list) {
        this.activity = activity;
        this.list = list;
    }

    public List<CouponsBean> getList() {
        return list;
    }

    public void setList(List<CouponsBean> list) {
        this.list = list;
    }

    @Override
    public MyCouponsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyCouponsViewHolder(parent,activity);
    }

    @Override
    public void onBindViewHolder(MyCouponsViewHolder holder, int position) {
        holder.setData(list.get(position));

        //点击item事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("coupon",list.get(position));
                activity.setResult(Activity.RESULT_OK,intent);
                activity.finish();
            }
        });
    }

    @Override
    public int getItemCount() {

        return list==null?0:list.size();
    }
}
