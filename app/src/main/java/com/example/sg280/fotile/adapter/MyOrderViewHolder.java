package com.example.sg280.fotile.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.model.bean.OrderBean;
import com.example.sg280.fotile.ui.activity.OrderInformationActivity;
import com.example.sg280.fotile.utils.ConvertUtil;
import com.example.sg280.fotile.utils.Glides;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;


/**
 * 我的订单的ViewHolder
 * Created by Tian on 2016/8/8.
 */
public class MyOrderViewHolder extends BaseViewHolder<OrderBean> {

    private Context context;
    private Activity activity;
    private ImageView iv_product;//品牌logo图片
    private TextView tv_order_number_info;//订单号
    private TextView tv_order_state;//订单状态
    private TextView tv_order_money_info;//订单金额
    private RecyclerView erv_product;//产品的RecyclerView
    private MyProductAdapter productAdapter;
    private RelativeLayout rl_to_info;//进入订单详情的RelativeLayout
    private String orderId;

    public MyOrderViewHolder(ViewGroup parent, Context context, Activity activity) {
        super(parent, R.layout.item_my_order);
        this.activity = activity;
        this.context = context;
        iv_product = $(R.id.iv_product);
        tv_order_number_info = $(R.id.tv_order_number_info);
        tv_order_state = $(R.id.tv_order_state);
        tv_order_money_info = $(R.id.tv_order_money_info);
        erv_product = $(R.id.erv_product);
        rl_to_info = $(R.id.rl_to_info);
        erv_product.setLayoutManager(new LinearLayoutManager(context));


    }

    @Override
    public void setData(OrderBean data) {
        super.setData(data);
        orderId = data.getID();
        Glides.getInstance().load(context, data.getBrandLogo(), iv_product);
        tv_order_number_info.setText(data.getID());
        tv_order_state.setText(ConvertUtil.getOrderStatus(data.getOrderStatus()));
        tv_order_money_info.setText("￥" + data.getProductAmount());

        rl_to_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderInformationActivity.class);
                intent.putExtra("orderId", orderId);
                activity.startActivity(intent);
            }
        });
        productAdapter = new MyProductAdapter(context, data.getOrderDetails());
        erv_product.setAdapter(productAdapter);
    }

}
