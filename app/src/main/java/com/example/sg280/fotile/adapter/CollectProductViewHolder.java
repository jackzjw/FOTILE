package com.example.sg280.fotile.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.sg280.fotile.R;
import com.example.sg280.fotile.model.bean.CollectProductBean;
import com.example.sg280.fotile.utils.Glides;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * 我的收藏中的商品的ViewHolder
 * Created by Tian on 2016/8/10.
 */
public class CollectProductViewHolder extends BaseViewHolder<CollectProductBean> {

    private ImageView iv_goods_my_order;
    private TextView tv_goods_name;
    private TextView tv_now_money;
    private TextView tv_market_money;
    private Button btn_goto_info;
    private Context context;
    private CollectProductBean data;

    public CollectProductViewHolder(ViewGroup parent,Context context) {
        super(parent, R.layout.item_goods_my_collect);
        this.context = context;
        iv_goods_my_order = $(R.id.iv_goods_my_order);
        tv_goods_name = $(R.id.tv_goods_name);
        tv_now_money = $(R.id.tv_now_money);
        tv_market_money = $(R.id.tv_market_money);
        btn_goto_info = $(R.id.btn_goto_info);

    }

    @Override
    public void setData(CollectProductBean data) {
        super.setData(data);
        this.data = data;
        tv_goods_name.setText(data.getProductName());
        Glides.getInstance().load(context,data.getProImgPix(),iv_goods_my_order);
        tv_now_money.setText("￥"+data.getPricePromo());
        tv_market_money.setText("￥"+data.getPriceCommon());
        tv_market_money.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        //点击查看详情跳转商品详情页面
        btn_goto_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context, MyCollectActivity.class);
//                intent.putExtra("啦啦啦",data.getProductName());
//                context.startActivity(intent);
            }
        });

    }


}
